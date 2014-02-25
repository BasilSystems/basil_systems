package com.example.basilapp1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PlacesActivity extends Activity {
	
	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.places_layout);
	    
	    ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle("Places");
//	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayUseLogoEnabled(true);
	    
        Button addPlaceButton = (Button)findViewById(R.id.add_a_place);	 
        addPlaceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 Intent modeSettingsIntent = new Intent(PlacesActivity.this, ModeActivity.class);
			        startActivity(modeSettingsIntent);
			}
		});
	  }

	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.places_action_bar, menu);
	    return true;
	  }
	
	 //NEW
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_refresh:
	      Toast.makeText(this, "Action refresh selected", Toast.LENGTH_SHORT)
	          .show();
	      break;
	    case R.id.action_settings:
	      Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT)
	          .show();
	      break;

	    default:
	      break;
	    }

	    return true;
	  }
}
