package com.apkez.easylife.entity;

import java.util.Map;

import android.content.Context;

import com.apkez.easylife.parser.BasicParser;

/**
 * 
 * @ClassName: Requester
 * @Description: 请求服务器对象，传递参数
 * @author Adam
 * @date 2013-11-9 下午11:24:57
 *
 */
public class Requester {

	public int requestUrl;
	public Context context;
	public BasicParser<?> jsonParser;
	public Map<String, String> parameters;
	
	
	/**
	 * 
	 * @param requestUrl    请求地址
	 * @param context		Context
	 * @param parser		Json解析器
	 * @param parameters	请求参数
	 */
	public Requester(int requestUrl, Context context, BasicParser<?> jsonParser,
			Map<String, String> parameters) {
		super();
		this.requestUrl = requestUrl;
		this.context = context;
		this.jsonParser = jsonParser;
		this.parameters = parameters;
	}
	
}
