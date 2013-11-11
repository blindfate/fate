package com.apkez.easylife.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * @ClassName: ViewUtil
 * @Description: Activity UI, Window, View工具类
 * @author Adam
 * @date 2013-11-10 上午1:47:51
 *
 */
public class ViewUtil {

	private final static String TAG = "ViewUtil";

	/**
	 * @Title: setFullScreen
	 * @Description: 是否全屏
	 * @param @param activity
	 * @param @param isFull true全屏，false不全屏
	 * @return void
	 * @throws
	 */
	public static void setFullScreen(Activity activity, boolean isFull){
		Window window = activity.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		if(isFull){
			params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
			window.setAttributes(params);
			window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}else{
			params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			window.setAttributes(params);
			window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}
	
	/**
	 * @Title: hideTitleBar
	 * @Description: 隐藏Activity的系统默认标题栏
	 * @param activity
	 * @return void
	 * @throws
	 */
	public static void hideTitleBar(Activity activity){
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/**
	 * @Description: 获取系统状态栏高度
	 * @param activity Activity
	 * @return int 状态栏高度
	 *
	 */
	public static int getStatusBarHeight(Activity activity){
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			Field field = clazz.getField("status_bar_height");
		    int dpHeight = Integer.parseInt(field.get(object).toString());
		    return activity.getResources().getDimensionPixelSize(dpHeight);
		} catch (Exception e1) {
		    e1.printStackTrace();
		    return 0;
		} 
	}

	/**
	 * 获取屏幕大小，单位px
	 * @param activity Activity
	 * @return 屏幕大小对象
	 */
	public static Point getScreenSize(Activity activity){
		Point size = new Point();
		
		// 获取屏幕密度  
		DisplayMetrics dm = new DisplayMetrics();  
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);  
		
		float density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）  
		int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）  
		float xdpi = dm.xdpi;             
		float ydpi = dm.ydpi;  
		  
		Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI=" + densityDPI);  
		  
//		int screenWidthDip = dm.widthPixels;        // 屏幕宽（dip，如：320dip）  
//		int screenHeightDip = dm.heightPixels;      // 屏幕宽（dip，如：533dip）  
//		  
//		Log.e(TAG + "  DisplayMetrics(222)", "screenWidthDip=" + screenWidthDip + "; screenHeightDip=" + screenHeightDip);
		  
		int screenWidth  = (int)(dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px）  
		int screenHeight = (int)(dm.heightPixels * density + 0.5f);     // 屏幕高（px，如：800px）  
		  
		Log.e(TAG + "  DisplayMetrics(222)", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);  
		
		size.set(screenWidth, screenHeight);
		return size;
	}
	
	/**
	 * @Title: getScreenWidth
	 * @Description: 获取屏幕宽(px，如：480px)
	 * @param @param activity
	 * @return int	屏幕宽
	 * @throws
	 */
	public static int getScreenWidth(Activity activity){
		Point size = getScreenSize(activity);
		return size.x;
	}
	
	/**
	 * @Title: getScreenHeight
	 * @Description: 获取屏幕高(px，如：800px)
	 * @param @param activity
	 * @return int	屏幕高
	 * @throws
	 */
	public static int getScreenHeight(Activity activity){
		Point size = getScreenSize(activity);
		return size.y;
	}
	
	/**
	 * 强制设置Actiity的显示方向为垂直方向。
	 * @param activity Activity
	 */
	public static void setScreenVertical(Activity activity){
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	/**
	 * 强制设置Activity的显示方向为横向。
	 * @param activity Activity
	 */
	public static void setScreenHorizontal(Activity activity){
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}
	
	/**
	 * 隐藏软件输入法
	 * @param activity Activity
	 */
	public static void hideSoftInput(Activity activity){
	    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	
	/**
	 * 关闭已经显示的输入法窗口。
	 * @param context 上下文对象，一般为Activity
	 * @param focusingView 输入法所在焦点的View
	 *
	 */
	public static void closeSoftInput(Context context,View focusingView){
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(focusingView.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
	}
	
	/**
	 * 使UI适配输入法
	 * @param activity Activity
	 */
	public static void adjustSoftInput(Activity activity) {
		activity.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	}
}
