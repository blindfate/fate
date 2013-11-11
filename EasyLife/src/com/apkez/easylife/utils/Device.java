package com.apkez.easylife.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * @ClassName: Device
 * @Description: 设备相关属性信息
 * @author Adam
 * @date 2013-11-10 上午7:17:59
 *
 */
public class Device {

	private static Context context = new Activity().getApplicationContext();
	public final static int X;
	public final static int Y;
	public final static boolean IS_MOUNTED;  
	public final static String SDPATH;
	public final static String IMEI;
	public final static String PUID;
	public final static String MAC;
	public final static String BLUETOOTHMAC;
	public final static String APP_VERSION;
	public final static int OS_VERSION;

	static{
		X = ViewUtil.getScreenWidth(new Activity());
		Y = ViewUtil.getScreenHeight(new Activity());
		IS_MOUNTED = SDCard.IS_MOUNTED;
		SDPATH = SDCard.getSDCardPath();
		IMEI = IMEI(context);
		PUID = PUID();
		MAC = MAC(context);
		BLUETOOTHMAC = BlueToothMAC();
		APP_VERSION = "";
		OS_VERSION = android.os.Build.VERSION.SDK_INT;
	}
	
    /**
     * 获取IMEI码。平板设备没有这个码，需要 READ_PHONE_STATE 权限
     * @param context
     * @return IMEI码
     */
    public static String IMEI(Context context){
        try {
            TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            String IMEI = TelephonyMgr.getDeviceId();
            return null == IMEI ? "" : IMEI;
        }catch (Exception e){
            return "";
        }
    }

    /**
     * Pseudo-Unique ID
     * @return
     */
    public static String PUID(){
        String likeIMEI = "35" +
        Build.BOARD.length()%10
                + Build.BRAND.length()%10
                + Build.CPU_ABI.length()%10
                + Build.DEVICE.length()%10
                + Build.DISPLAY.length()%10
                + Build.HOST.length()%10
                + Build.ID.length()%10
                + Build.MANUFACTURER.length()%10
                + Build.MODEL.length()%10
                + Build.PRODUCT.length()%10
                + Build.TAGS.length()%10
                + Build.TYPE.length()%10
                + Build.USER.length()%10 ;
        return likeIMEI;
    }

    /**
     * MAC地址
     * @param context
     * @return
     */
    public static String MAC(Context context){
        try {
            WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            String MAC = wm.getConnectionInfo().getMacAddress();
            return null == MAC ? "" : MAC;
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 蓝牙地址
     * @return
     */
    public static String BlueToothMAC(){
        try {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            String BT_MAC = adapter.getAddress();
            return null == BT_MAC ? "" : BT_MAC;
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 组合以上各种信息的设备码
     * @param context
     * @return
     */
    public static String CombinedDeviceID(Context context){
        return new StringBuilder("PUID:").append(PUID()).append("  ")
                .append("IMEI:").append(IMEI(context)).append("  ")
                .append("MAC:").append(MAC(context)).append("  ")
                .append("BT-MAC:").append(BlueToothMAC()).toString();
    }
    
    /**
     * @Title: getVersionName
     * @Description: 获取当前应用的版本号
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     */
    public static String getVersionName() throws Exception
    {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return  packInfo.versionName;
    }
}
