package com.basilsystems.util;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.basilapp1.R;

public class ApplianceAdapter extends ArrayAdapter<ApplianceModel> implements OnSeekBarChangeListener {
	private final LayoutInflater mInflater;

	private static final int MAX_VALUE_SEEKBAR = 90;
	private static final int MIN_VALUE_SEEKBAR = 10;

	private static  CheckBox autoButton ;
	private static SeekBar seekBar;

	private static Handler handler;

	public ApplianceAdapter(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String messageNumber = (String)msg.getData().get("message");
				
				if(messageNumber.equals("1")){
					setSeekBarThumb(seekBar, false);
				}else if(messageNumber.equals("0")){
					setSeekBarThumb(seekBar, true);
				}
			}

		};
	}

	public void setData(List<ApplianceModel> data) {
		clear();
		if (data != null) {
			for (ApplianceModel appEntry : data) {
				add(appEntry);
			}
		}
	}

	/**
	 * Populate new items in the list.
	 */
	@Override public View getView(int position, View convertView, ViewGroup parent) {
		View view;

		if (convertView == null) {
			view = mInflater.inflate(R.layout.appliance_layout_list, parent, false);
		} else {
			view = convertView;
		}

		ApplianceModel item = getItem(position);

		((TextView)view.findViewById(R.id.appliance_name)).setText(item.getApplianceName());
		seekBar = (SeekBar)view.findViewById(R.id.statusBar);
		seekBar.setProgress(item.getStatus());
		seekBar.setOnSeekBarChangeListener(this);
		autoButton = (CheckBox)view.findViewById(R.id.autoButton);
		autoButton.setChecked(item.getIsAuto());

		autoButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(arg1){
					sendMsgToHandler("0");
				}else{
					sendMsgToHandler("1");
				}

			}


		});

		//        ((TextView)view.findViewById(R.id.list_item)).setText(item.getName());

        setSeekBarThumb(seekBar, autoButton.isChecked());
		return view;
	}

	private void setSeekBarThumb(SeekBar seekBar, Boolean autoChecked){
		if(seekBar.getProgress() < MAX_VALUE_SEEKBAR && seekBar.getProgress() > MIN_VALUE_SEEKBAR){
			if(autoChecked){
				seekBar.setThumb(getContext().getResources().getDrawable(R.drawable.custom_thumb_normal));
			}else{
				seekBar.setThumb(getContext().getResources().getDrawable(R.drawable.custom_thumb_auto_off));
			}
		}
	}

	private void sendMsgToHandler(String msg) {
		Message msgObj = handler.obtainMessage();
		Bundle b = new Bundle();
		b.putString("message", msg);
		msgObj.setData(b);
		handler.sendMessage(msgObj);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean arg2) {
		if(progress > MAX_VALUE_SEEKBAR){
			seekBar.setProgress(MAX_VALUE_SEEKBAR);
			seekBar.setThumb(getContext().getResources().getDrawable(R.drawable.custom_thumb_max_value));
		}else if(progress < MIN_VALUE_SEEKBAR){
			seekBar.setProgress(MIN_VALUE_SEEKBAR);
			seekBar.setThumb(getContext().getResources().getDrawable(R.drawable.custom_thumb_off_value));
		}	

	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}
} 
