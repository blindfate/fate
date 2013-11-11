package com.apkez.easylife.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.apkez.easylife.ui.MainActivity;

public class GuidePagerAdapter extends PagerAdapter {
	
	private final static String TAG = "GuidePagerAdapter";
	private List<View> views;
	private Activity activity;
	public GuidePagerAdapter(Activity activity, List<View> views){
		this.views = views;
		this.activity = activity;
	}
	
	
	@Override
	public int getCount() {
		if(views!=null){
			return views.size();
		}
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView(views.get(position));
		
		Log.i(TAG, "图片数量：" + views.size() + "----当前位置：" + position);
		
		/*进入首页*/
		if(position == views.size()-1){
			Intent intent = new Intent(activity, MainActivity.class);  
	        activity.startActivity(intent);  
	        activity.finish();
		}
		return views.get(position);
	}
	
	
}
