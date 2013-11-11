package com.apkez.easylife.utils;

import java.io.File;

import android.os.Environment;

public class SDCard {

	/** SDCard是否可用 **/
	public static boolean IS_MOUNTED = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	
	public static String SDPATH = getSDCardPath();
	/**
	 * 取得SD卡路径，以/结尾
	 * @return SD卡路径
	 */
	public static String getSDCardPath(){
		if(!IS_MOUNTED) return null;
		File path = Environment.getExternalStorageDirectory(); 
		String SDCardPath = path.getAbsolutePath();
		SDCardPath += SDCardPath.endsWith(File.separator) ? "" : File.separator;
		return SDCardPath;
	}
}
