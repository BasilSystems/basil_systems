package com.basilsystems.util;

import android.content.Context;
import android.widget.Toast;

public class Util {

	public static void showToast(Context context, String text){
		if(context != null && text != null && !text.isEmpty()){
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
	}

}
