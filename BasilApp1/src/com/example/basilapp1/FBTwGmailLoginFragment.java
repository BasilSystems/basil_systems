package com.example.basilapp1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FBTwGmailLoginFragment extends Fragment {

	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fbtwgmail_login_option,
	        container, false);
	    return view;
	  }
}
