package com.example.basilapp1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.basilsystems.util.SocketConnectionManager;
import com.basilsystems.util.Util;
import com.koushikdutta.async.http.socketio.Acknowledge;
import com.koushikdutta.async.http.socketio.EventCallback;

public class LoginActivity extends Activity {
	

//	private static final String URL = "http://ritikadhyawala-nodejitsu1.jit.su/";
	private static final String URL = "https://testing-c9-ritikadhyawala.c9.io/";
	private SocketConnectionManager socketConnectionManager = null;
	
	private ProgressDialog connectToServerProgress;
	
	private EditText username;
	private EditText password;
	private boolean showProgressDialog;
	
	private ProgressDialog progressDialog;


	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    
	    username = (EditText)findViewById(R.id.username);
	    password = (EditText)findViewById(R.id.password);
	    		
	    ActionBar actionBar = getActionBar();
	    actionBar.hide();
	    setSignUpButton();
	    setSignInButton();
	    
	   
	    
	    socketConnectionManager = new SocketConnectionManager(getApplicationContext());
		socketConnectionManager.connect(URL);
		
		 connectToServerProgress = ProgressDialog.show(this, "", "Connecting to the server", true);
		    new ConnectToServerThread().start();
		
	  }

	 private class ConnectToServerThread extends Thread{
		 
		 public void run(){
			 while(true){
				 try {
					 if(SocketConnectionManager.client != null){
						 connectToServerProgress.cancel();
						 break;
					 }
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 }
		 }
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
				
//				 Intent placesIntent = new Intent(LoginActivity.this, PlacesActivity.class);
//			        startActivity(placesIntent);
				if(username.getText().toString() != null && !username.getText().toString().isEmpty()){
					if(password.getText().toString() != null && !password.getText().toString().isEmpty()){
						JSONArray jsonArray = new JSONArray();
						JSONObject jsonObject = new JSONObject();
						try {
							jsonObject.put("email_id", username.getText().toString());
							jsonObject.put("password", password.getText().toString());
							jsonArray.put(jsonObject);
							SocketConnectionManager.client.emit("checkSignIn", jsonArray);
							showProgressDialog = true;
							progressDialog = ProgressDialog.show(LoginActivity.this, "", "Authenticating", true);
							new ShowProgressDialogThread().start();
							SocketConnectionManager.client.on("AuthenticationReply", new EventCallback() {
								
								@Override
								public void onEvent(String event, JSONArray argument,
										Acknowledge acknowledge) {
                                      if(argument != null){
                                    	  if(argument.length() > 0){
                                    		  try {
												String serverReply = argument.getString(0);
												if("Unauthorized".equals(serverReply)){
													Util.showToast(getApplicationContext(), "User unauthorized");
												}else if("Authorized".equals(serverReply)){
													Util.showToast(getApplicationContext(), "User logged in successfully");
													
												}
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
                                    	  }
                                    	  showProgressDialog = false;
                                      }
								}
							});
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						Util.showToast(getApplicationContext(), "Password cannot be empty");
					}
				}else{
					Util.showToast(getApplicationContext(), "Username/Email cannot be empty");
				}
				
			}
		});
	}
	
	private void getPlacesForUSer(){
		
		Intent placesIntent = new Intent(LoginActivity.this, PlacesActivity.class);
        startActivity(placesIntent);
	}
	private class ShowProgressDialogThread extends Thread{
		public void run(){
			while(true){
				if(!showProgressDialog){
					progressDialog.cancel();
					break;
				} else
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	  
}
