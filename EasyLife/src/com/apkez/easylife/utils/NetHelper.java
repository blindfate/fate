package com.apkez.easylife.utils;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.apkez.easylife.R;
import com.apkez.easylife.entity.Requester;

/**
 * @ClassName: NetHelper
 * @Description: 网络相关：请求服务器数据，网络状态
 * @author Adam
 * @date 2013-11-10 上午7:48:36
 *
 */
public class NetHelper {

	private static final String TAG = "NetUtil";
	/*HTTP请求头*/
	private static Header[] headers = new BasicHeader[11];
	static {
		headers[0] = new BasicHeader("Appkey", "");
		headers[1] = new BasicHeader("Udid", "");
		headers[2] = new BasicHeader("Os", "");
		headers[3] = new BasicHeader("Osversion", "");
		headers[4] = new BasicHeader("Appversion", "");
		headers[5] = new BasicHeader("Sourceid", "");
		headers[6] = new BasicHeader("Ver", "");
		headers[7] = new BasicHeader("Userid", "");
		headers[8] = new BasicHeader("Usersession", "");
		headers[9] = new BasicHeader("Unique", "");
		headers[10] = new BasicHeader("Cookie", "");
		
	}
	
	/**
	 * 返回网络是否可用。需要权限：
	 * <p><b> < uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> </b></p>
	 * @param context 上下文
	 * @return 网络可用则返回true，否则返回false
	 */
	public static boolean isAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable();
	}
	
	/**
	 * 返回Wifi是否启用
	 * @param context 上下文
	 * @return Wifi网络可用则返回true，否则返回false
	 */
	public static boolean isWIFIActivate(Context context) {
		return ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).isWifiEnabled();
	}
	
	/**
	 * 修改Wifi状态
	 * @param context 上下文
	 * @param status true为开启Wifi，false为关闭Wifi
	 */
	public static void changeWIFIStatus(Context context, boolean status) {
		((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).setWifiEnabled(status);
	}
	
	public static Object post(Requester requester){
		Object data;
		DefaultHttpClient client = new DefaultHttpClient();
		String url = requester.context.getString(R.string.app_host).concat(requester.context.getString(requester.requestUrl));
		HttpGet get = new HttpGet(url);
		get.setHeaders(headers);
		Object obj = null;
		try {

			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				setCookie(response);
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				try {
//					if (invilidateLogin(result)) {
//						return Status.Login;
//					}
					obj = requester.jsonParser.parseJSON(result);
				} catch (JSONException e) {
					Log.e(TAG, e.getLocalizedMessage(), e);
				}
				return obj;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object get(){
		
		return null;
	}
	
	/**
	 * 添加Cookie
	 * @param response
	 */
	private static void setCookie(HttpResponse response) {
		if (response.getHeaders("Set-Cookie").length > 0) {
			headers[10] = new BasicHeader("Cookie", response.getHeaders("Set-Cookie")[0].getValue());
		}
	}
}
