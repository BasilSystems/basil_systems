package com.basilsystems.util;

import android.app.ProgressDialog;
import android.content.Context;



public class ProgressBarManager {

	private boolean showProgressDialog;
	private Context context;
	private ProgressDialog progressDialog;
	
	public ProgressBarManager(ProgressDialog progressDialog, Context context) {
          this.progressDialog = progressDialog;
          this.context = context;
	}
	
	public void startProgressBar(){
		showProgressDialog = true;
		progressDialog = ProgressDialog.show(context, "", "Getting reply from the server", true );
		new ProgressDialogThread().run();
	}
	
	public void stopProgressBar(){
		showProgressDialog = false;
	}
	
	private class ProgressDialogThread extends Thread{
		public void run(){
			while(true){
				if(!showProgressDialog){
					progressDialog.cancel();
				}else{
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

}
