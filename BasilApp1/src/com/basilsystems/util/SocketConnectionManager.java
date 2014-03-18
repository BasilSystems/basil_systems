package com.basilsystems.util;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.koushikdutta.async.http.socketio.Acknowledge;
import com.koushikdutta.async.http.socketio.ConnectCallback;
import com.koushikdutta.async.http.socketio.DisconnectCallback;
import com.koushikdutta.async.http.socketio.ErrorCallback;
import com.koushikdutta.async.http.socketio.EventCallback;
import com.koushikdutta.async.http.socketio.JSONCallback;
import com.koushikdutta.async.http.socketio.SocketIOClient;
import com.koushikdutta.async.http.socketio.StringCallback;

public class SocketConnectionManager implements DisconnectCallback, ErrorCallback, JSONCallback, StringCallback, EventCallback  {
	 
	public  static SocketIOClient client = null;
	static final String TAG = SocketConnectionManager.class.getSimpleName();
	
	private Context activity = null;
	
	String SENDER_ID = "263514921368";

    GoogleCloudMessaging gcm;

    String regid;
	
	@SuppressLint("HandlerLeak")
	private Handler sHandler = new Handler(){
		 @Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
	                case SocketConnectionManager.SOCKETIO_DISCONNECT:
	                    Log.i(TAG, "SOCKETIO_DISCONNECT");
	                    Toast.makeText(activity, "Disconnect", Toast.LENGTH_SHORT)
	                            .show();
	                    break;
	                case SocketConnectionManager.SOCKETIO_CONNECT:
	                    Log.i(TAG, "SOCKETIO_CONNECT");
	                    Toast.makeText(activity, "Connect", Toast.LENGTH_SHORT)
	                            .show();
	                    break;
	                case SocketConnectionManager.SOCKETIO_HERTBEAT:
	                    Log.i(TAG, "SOCKETIO_HERTBEAT");
	                    break;
	                case SocketConnectionManager.SOCKETIO_MESSAGE:
	                    Log.i(TAG, "SOCKETIO_MESSAGE");
	                    break;
	                case SocketConnectionManager.SOCKETIO_JSON_MESSAGE:
	                    Log.i(TAG, "SOCKETIO_JSON_MESSAGE");
//	                    Msg message = JSON.decode((String) (msg.obj), Msg.class);
//	                    TextView text = (TextView) findViewById(R.id.get_message);
//	                    text.setText(message.getValue());
	                    break;
	                case SocketConnectionManager.SOCKETIO_EVENT:
	                    Log.i(TAG, "SOCKETIO_EVENT");
	                    break;
	                case SocketConnectionManager.SOCKETIO_ERROR:
	                    Log.i(TAG, "SOCKETIO_ERROR");
	                    break;
	                case SocketConnectionManager.SOCKETIO_ACK:
	                    Log.i(TAG, "SOCKETIO_ACK");
	                    break;
	            }
	        }
	};	
	 private boolean isHandler() {
	        return sHandler != null ? true : false;
	 }

	public SocketConnectionManager(Context currentActivity){
		activity = currentActivity;

	}
	
	
	public void connect(String url){
		
		SocketIOClient.connect(url, new ConnectCallback() {
		//SocketIOClient.connect("https://basilsystems-c9-ritikadhyawala.c9.io/", new ConnectCallback() {

		    @Override
		    public void onConnectCompleted(Exception ex, SocketIOClient client) {
		    	
		    	

//		        if (ex != null) {
//		            return;
//		        }

		        Log.i(TAG, "Connection established");
		        if (isHandler()) {
		            Message msg = sHandler.obtainMessage(SOCKETIO_CONNECT);
		            sHandler.sendMessage(msg);
		        }
		        
		        //Save the returned SocketIOClient instance into a variable so you can disconnect it later
		        client.setDisconnectCallback(SocketConnectionManager.this);
		        client.setErrorCallback(SocketConnectionManager.this);
		        client.setJSONCallback(SocketConnectionManager.this);
		        client.setStringCallback(SocketConnectionManager.this);

		        //You need to explicitly specify which events you are interested in receiving
		        client.addListener("news", SocketConnectionManager.this);

		        client.of("/chat", new ConnectCallback() {

		            @Override
		            public void onConnectCompleted(Exception ex, SocketIOClient client) {

		                if (ex != null) {
		                    ex.printStackTrace();
		                    return;
		                }

		                //This client instance will be using the same websocket as the original client, 
		                //but will point to the indicated endpoint
		                client.setDisconnectCallback(SocketConnectionManager.this);
		                client.setErrorCallback(SocketConnectionManager.this);
		                client.setJSONCallback(SocketConnectionManager.this);
		                client.setStringCallback(SocketConnectionManager.this);
		                client.addListener("a message", SocketConnectionManager.this);
		                
		                
		            }
		        });
//		        gcm = GoogleCloudMessaging.getInstance(activity);
//
//    			new RegisterBackground().execute();	
		        
		        SocketConnectionManager.client = client;

		    }
		}, new Handler());
		
		
		
	}
	
	
	class RegisterBackground extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String msg = "";
			try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(activity);
                }
                regid = gcm.register(SENDER_ID);
                msg = "Dvice registered, registration ID=" + regid;
                Log.d("111", msg);
                sendRegistrationIdToBackend(regid);

            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }

		@Override
        protected void onPostExecute(String msg) {
//            mDisplay.append(msg + "\n");

        }
		private void sendRegistrationIdToBackend(String regid) {
                  

			JSONArray json_array = new JSONArray();
			
			
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("reg_id", regid);
				jsonObject.put("email_id", "ritikadhyawala@gmail.com");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			json_array.put(jsonObject);
			SocketConnectionManager.client.emit("reg_id", json_array);
			SocketConnectionManager.client.addListener("reg_id_ack", new EventCallback() {
				
				@Override
				public void onEvent(String event, JSONArray argument,
						Acknowledge acknowledge) {
					Toast.makeText(activity, "Reply from the server", Toast.LENGTH_SHORT)
                    .show();
				}
			});
			
		
}}
	
	public void disconnect(){
		if(client != null){
			client.disconnect();
		}
	}
	
	@Override
	public void onEvent(String event, JSONArray argument, Acknowledge acknowledge) {
	    try {
	        Log.d("MainActivity", "Event:" + event + "Arguments:"
	                + argument.toString(2));
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    Log.i(TAG, "Server triggered event '" + event + "'");
        if (event.equals("message")) {
        	try {
				onJSON((JSONObject) argument.getJSONObject(0), null);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

	}

	@Override
	public void onString(String string, Acknowledge acknowledge) {
	    Log.d("MainActivity", string);
	    Log.i(TAG, "Server said: " + string);
        if (isHandler()) {
            Message msg = sHandler.obtainMessage(SOCKETIO_MESSAGE, string);
            sHandler.sendMessage(msg);
        }

	}

	@Override
	public void onJSON(JSONObject json, Acknowledge acknowledge) {
		Log.i(TAG, "Server said:" + json.toString());
        if (isHandler()) {
            Message msg = sHandler.obtainMessage(SOCKETIO_JSON_MESSAGE, json.toString());
            sHandler.sendMessage(msg);
        }
		try {
	        Log.d("MainActivity", "json:" + json.toString(2));
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }

	}

	@Override
	public void onError(String error) {
	    Log.d("MainActivity", error);
	    Log.i(TAG, "an Error occured");
        if (isHandler()) {
            Message msg = sHandler.obtainMessage(SOCKETIO_ERROR);
            sHandler.sendMessage(msg);
        }

	}

	@Override
	public void onDisconnect(Exception e) {
		Log.i(TAG, "Connection terminated.");
        if (isHandler()) {
            Message msg = sHandler.obtainMessage(SOCKETIO_DISCONNECT);
            sHandler.sendMessage(msg);
        }
	}
	
	
	
	public static final int SOCKETIO_DISCONNECT = 0;
    public static final int SOCKETIO_CONNECT = 1;
    public static final int SOCKETIO_HERTBEAT = 2;
    public static final int SOCKETIO_MESSAGE = 3;
    public static final int SOCKETIO_JSON_MESSAGE = 4;
    public static final int SOCKETIO_EVENT = 5;
    public static final int SOCKETIO_ACK = 6;
    public static final int SOCKETIO_ERROR = 7;
    public static final int SOCKETIO_NOOP = 8;
    
}
