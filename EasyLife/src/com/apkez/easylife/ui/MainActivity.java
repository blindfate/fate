package com.apkez.easylife.ui;

import com.apkez.easylife.BasicActivity;
import com.apkez.easylife.R;
import com.apkez.easylife.R.layout;
import com.apkez.easylife.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends BasicActivity {


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
		setContentView(R.layout.activity_main);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}
