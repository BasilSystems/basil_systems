package com.example.basilapp1;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment1 extends ListFragment {

	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2" };
//	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//	        android.R.layout.simple_list_item_1, values);
	    this.setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.home_list_layout, R.id.label, values));
//	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
	  }
	} 

