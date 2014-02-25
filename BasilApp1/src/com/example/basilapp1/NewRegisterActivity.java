package com.example.basilapp1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NewRegisterActivity extends Activity {
	
	// constant for identifying the dialog
	private static final int DIALOG_ALERT = 10;
	
	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.i_am_new_here);
	    
	    ActionBar actionBar = getActionBar();
	    actionBar.hide();
	    
	    Button register_button = (Button)findViewById(R.id.register);
	    register_button.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_ALERT);
				
			}
		});
	  }
	 
	 @SuppressWarnings("deprecation")
	@Override
	 protected Dialog onCreateDialog(int id) {
	   switch (id) {
	     case DIALOG_ALERT:
	    	 ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.MyTheme );
	 		 Builder builder= new AlertDialog.Builder( ctw );
	     LayoutInflater inflater = getLayoutInflater();
	     builder.setView(inflater.inflate(R.layout.password_sent_alert_layout, null)).setPositiveButton(R.string.ok, new  DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					NewRegisterActivity.this.finish();
					
				}
			});
	     AlertDialog dialog = builder.create();
	     dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	     dialog.show();
	   }
	   return super.onCreateDialog(id);
	 }



}
