package com.example.basilapp1;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.basilsystems.util.ApplianceAdapter;
import com.basilsystems.util.ApplianceModel;
import com.basilsystems.util.DataProvider;

public class ApplianceActivity extends ListActivity  {

	private ApplianceAdapter applianceAdapter;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    applianceAdapter = new ApplianceAdapter(this);
	    applianceAdapter.setData(getApplianceData());
	    setListAdapter(applianceAdapter);
	    
	    
//	    this.runOnUiThread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for(int i=0; i< getListAdapter().getCount() ; i++){
//					View view = getListAdapter().getView(i, getListView(), getListView());
//					SeekBar seekBar;
//					CheckBox autoButton;
//					
//					autoButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//						@Override
//						public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
//							if(arg1){
//								setSeekBarThumb(seekBar, true);
//							}else{
//								setSeekBarThumb(seekBar, true);
//							}
//
//						}
//
//
//					});
//					
//				}
//				
//				
//				
//			}
//			
//			private void setSeekBarThumb(SeekBar seekBar, Boolean autoChecked){
//				if(seekBar.getProgress() < MAX_VALUE_SEEKBAR && seekBar.getProgress() > MIN_VALUE_SEEKBAR){
//					if(autoChecked){
//						seekBar.setThumb(getContext().getResources().getDrawable(R.drawable.custom_thumb_normal));
//					}else{
//						seekBar.setThumb(getContext().getResources().getDrawable(R.drawable.custom_thumb_auto_off));
//					}
//				}
//			}
//		});
	 // Start out with a progress indicator.
//        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
	   
//        getLoaderManager().initLoader(1, null, this);
	    
	  }
	
	private List<ApplianceModel> getApplianceData() {
		AssetManager assetManager = this.getAssets();
         InputStream applianceFile = null;
		try {
			applianceFile = assetManager.open("home_bedroom.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         if(!DataProvider.appliances.containsKey("home_bedroom")){
        	return DataProvider.getAppliancesFromFile("home_bedroom", applianceFile);
         }
         return DataProvider.appliances.get("home_bedroom");
	}
	
	 @Override
     public void onListItemClick(ListView l, View v, int position, long id) {
         // Insert desired behavior here.
         Log.i("DataListFragment", "Item clicked: " + id);
     }

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.appliances_menu, menu);
			
			ActionBar actionBar = getActionBar();
			
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setTitle("BEDROOM");
			return true;
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
		        case R.id.rename:
		            // Red item was selected
		            return true;
		        case R.id.miscellaneous:
		            // Green item was selected
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
			}
		}

	} 

