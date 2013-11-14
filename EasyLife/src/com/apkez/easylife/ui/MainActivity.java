package com.apkez.easylife.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.apkez.easylife.R;
import com.apkez.easylife.utils.ViewUtil;

public class MainActivity extends FragmentActivity {

	// 定义FragmentTabHost对象
	private FragmentTabHost tabHost;

	//定义底部Item自定义View
	private TextView itemTextView;
	
	// 定义数组来存放Fragment界面
	private Class<?> contentFragments[] = 
		{  
			HomeFragment.class,
			CategoryFragment.class,
			CartFragment.class,
			MineFragment.class
		};

	// 定义数组来存放按钮图片
	private static int tabIcons[] = 
		{ 
			R.drawable.selector_main_tab_home,
			R.drawable.selector_main_tab_search,
			R.drawable.selector_main_tab_cart,
			R.drawable.selector_main_tab_mine,
		};

	// Tab选项卡的文字
	private static int tabTexts[] = 
		{
			R.string.main_home,
			R.string.main_category,
			R.string.main_cart,
			R.string.main_mine
		};

	private static String itemTags[] = {"home", "category", "cart", "mine"};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*全屏*/
		ViewUtil.hideTitleBar(this);
		ViewUtil.setFullScreen(this, true);
		
		loadLayout();
		findViewById();
		initView();
	}

	private void loadLayout(){
		setContentView(R.layout.main_tabhost_layout);
	}
	
	private void findViewById(){
		// 实例化TabHost对象，得到TabHost
		tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
	}
	
	/**
	 * 初始化选项卡
	 */
	private void initView() {
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// 得到fragment的个数
		int fragmentCount = contentFragments.length;

		for (int i = 0; i < fragmentCount; i++) {
			//构建选项卡Item，为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = tabHost.newTabSpec(itemTags[i]).setIndicator(getTabItemView(i));
			
			// 将Tab按钮添加进Tab选项卡中
			tabHost.addTab(tabSpec, contentFragments[i], null);
			
			// 设置Tab按钮的背景
//			tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.bottom_background);
		}
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		itemTextView = new TextView(this);
		itemTextView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[index], 0, 0);
		itemTextView.setText(tabTexts[index]);
		itemTextView.setTextAppearance(this, R.style.tab_item_style);
		itemTextView.setGravity(Gravity.CENTER);
		return itemTextView;
	}
}
