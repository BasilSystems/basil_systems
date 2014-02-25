package com.example.basilapp1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddPlaceLogoutButtonFragment extends Fragment {

	 
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.add_places_layout_fragment,
	        container, false);
	    
	    return view;
	  }
}
