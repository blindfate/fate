package com.apkez.easylife.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName: IOHelper
 * @Description: IO helper
 * @author Adam
 * @date 2013-11-10 上午7:47:01
 *
 */
public class IOHelper {

	public static void release(InputStream in) throws IOException {
		release(in, null);
	}

	public static void release(OutputStream out) throws IOException {
		release(null, out);
	}

	public static void release(InputStream in, OutputStream out)
			throws IOException {
		if (out != null) {
			out.close();
		}
		if (in != null) {
			in.close();
		}
	}
}
