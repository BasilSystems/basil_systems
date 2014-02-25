package com.example.basilapp1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ModeActivity extends FragmentActivity {

	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bottom_tabs);
		setTabs();

	}

	private void setTabs()
	{
		addTab("Home", R.drawable.tab_home, Fragment1.class);
		addTab("Modes", R.drawable.tab_search, Fragment1.class);

		addTab("Settings", R.drawable.tab_home, Fragment1.class);
		addTab("Sort By", R.drawable.tab_search, Fragment1.class);

		mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_indicator);
		mTabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_indicator);
		mTabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab_indicator);
		mTabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab_indicator);
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
				Fragment1.class, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
