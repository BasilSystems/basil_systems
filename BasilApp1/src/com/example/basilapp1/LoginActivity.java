package com.example.basilapp1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    
	    ActionBar actionBar = getActionBar();
	    actionBar.hide();
	    
	    setSignUpButton();
	    
	    setSignInButton();
	  }

	private void setSignUpButton() {
		Button sign_up = (Button)findViewById(R.id.sign_up_button);
	    sign_up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 Intent newRegisterIntent = new Intent(LoginActivity.this, NewRegisterActivity.class);
			        startActivity(newRegisterIntent);
				
			}
		});
	}

	private void setSignInButton() {
		Button sign_in = (Button)findViewById(R.id.sign_in_button);
	    sign_in.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 Intent placesIntent = new Intent(LoginActivity.this, PlacesActivity.class);
			        startActivity(placesIntent);
				
			}
		});
	}
	  
}
