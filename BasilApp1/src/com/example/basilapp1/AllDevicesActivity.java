package com.example.basilapp1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class AllDevicesActivity extends Activity implements OnSeekBarChangeListener {
	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.all_devices_layout);
	    
	    SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar);
	    seekBar.setOnSeekBarChangeListener(this);
	    
	  }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if(progress > 93){
			seekBar.setProgress(93);
		}else if(progress < 7){
			seekBar.setProgress(7);
		}	
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
