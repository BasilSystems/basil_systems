package com.example.basilapp1;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ModeActivity extends FragmentActivity implements
ActionBar.OnNavigationListener {

	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bottom_tabs);
		setTabs();
		
		ActionBar actionBar = getActionBar();
		
		String[] dropdownValues = {"Refresh", "Add Device", "Device Delete"};
		
		// Specify a SpinnerAdapter to populate the dropdown list.
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
	        android.R.layout.simple_spinner_item, android.R.id.text1,
	        dropdownValues);

	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    // Set up the dropdown list navigation in the action bar.
	    actionBar.setListNavigationCallbacks(adapter, this);
		
		

	}

	private void setTabs()
	{
		addTab("Home", R.drawable.tab_home, Home.class);
		addTab("Modes", R.drawable.tab_modes, Home.class);

		addTab("Settings", R.drawable.tab_settings, Home.class);

		mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_indicator);
		mTabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_indicator);
		mTabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_indicator);
		mTabHost.getTabWidget().setStripEnabled(false);
		mTabHost.getTabWidget().setDividerDrawable(null);	
	}

	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, mTabHost, false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		mTabHost.addTab(mTabHost.newTabSpec(labelId).setIndicator("", getResources().getDrawable(drawableId)),
				Home.class, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
