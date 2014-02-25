package com.example.basilapp1;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AddPlaceActivity extends Activity {

	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.places_layout);
	    
	    ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle(R.string.add_place_button);
//	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayUseLogoEnabled(true);
	    
	    
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
