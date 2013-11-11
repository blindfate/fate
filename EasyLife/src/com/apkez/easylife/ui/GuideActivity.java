package com.apkez.easylife.ui;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.apkez.easylife.BasicActivity;
import com.apkez.easylife.R;
import com.apkez.easylife.adapter.GuidePagerAdapter;
import com.apkez.easylife.utils.Constant;
import com.apkez.easylife.utils.PreferencesUtil;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class GuideActivity extends BasicActivity {

	private ViewPager viewPager;
	private ViewGroup dotGroups, pageGroups;
	private ImageView[] dots;
	private ImageView dotImgView;
	private List<View> views;
	private GuidePagerAdapter adapter;
	private int currentIndex;
	private LayoutInflater inflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*1. 检查更新*/
		/*2. 检查首次运行*/
		firstRun();
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void initLayout() {
		
		inflater = getLayoutInflater();
//		pageGroups = (ViewGroup)inflater.inflate(R.layout.guide_page, null);
        
        setContentView(R.layout.guide_page);
	}
	
	private void initDots() {

    	//小点图片初始化
        dots = new ImageView[views.size()];  
  
        //构建对应数量的小点  
        for (int i = 0; i < views.size(); i++) {
        	dotImgView = new ImageView(GuideActivity.this);
        	dotImgView.setLayoutParams(new LayoutParams(20,20));  
        	dotImgView.setPadding(20, 0, 20, 0);
        	dots[i] = dotImgView;  
        	dotImgView.setBackgroundResource(R.drawable.dot);
            dotGroups.addView(dots[i]);  
        }  
        
        currentIndex = 0;  
        dots[currentIndex].setEnabled(false);// 选中状态  
    } 
  
	/* 当前小点位置 */
	private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}

		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = position;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findViewById() {
		viewPager = (ViewPager) findViewById(R.id.guide_viewpager);
		// dotGroups是负责包裹小圆点的LinearLayout.
		dotGroups = (ViewGroup) findViewById(R.id.dotGroups);
	}

	@Override
	public void bindListener() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			   // 当滑动状态改变时调用  
		    @Override  
		    public void onPageScrollStateChanged(int arg0) {  
		    }  
		  
		    // 当当前页面被滑动时调用  
		    @Override  
		    public void onPageScrolled(int arg0, float arg1, int arg2) {  
		    }  
		  
		    // 当新的页面被选中时调用  
		    @Override  
		    public void onPageSelected(int arg0) {  
		        // 设置底部小点选中状态  
		        setCurrentDot(arg0);  
		    }
		});
	}

	@Override
	public void process() {
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.guide_page_one, null));
		views.add(inflater.inflate(R.layout.guide_page_two, null));
		views.add(inflater.inflate(R.layout.guide_page_three, null));
		views.add(inflater.inflate(R.layout.guide_page_four, null));
  
        // 初始化底部小点  
        initDots();
        
		adapter = new GuidePagerAdapter(this, views);
		viewPager.setAdapter(adapter);		
	}
	
	/*首次运行*/
	private void firstRun(){
//		boolean isFirst = PreferencesUtil.getBoolean(this, Constant.FIRST_RUN, true);
		boolean isFirst = true;
		if(isFirst){
			PreferencesUtil.putBoolean(this, Constant.FIRST_RUN, false);
		}else{
			startHome();
		}
	}
	
	/*进入首页*/
	private void startHome(){
		Intent intent = new Intent(GuideActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	/*对比版本号更新*/
	private void update(){
		//TODO
	}
	/*下载新的APK*/
	private void download(){
		//TODO
	};
	/*安装更新*/
	private void install(){
		//TODO
	};
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
