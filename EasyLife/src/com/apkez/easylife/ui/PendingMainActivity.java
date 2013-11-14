package com.apkez.easylife.ui;

import com.apkez.easylife.BasicActivity;
import com.apkez.easylife.R;
import com.apkez.easylife.R.layout;
import com.apkez.easylife.R.menu;
import com.apkez.easylife.utils.ViewUtil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class PendingMainActivity extends BasicActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtil.setFullScreen(this, false);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findViewById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initLayout() {
		setContentView(R.layout.fragment_home);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}
