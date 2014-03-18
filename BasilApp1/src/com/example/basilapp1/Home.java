package com.example.basilapp1;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.basilsystems.util.DataProvider;
import com.basilsystems.util.DeviceAdapter;
import com.basilsystems.util.DeviceModel;

public class Home extends ListFragment implements LoaderManager.LoaderCallbacks<List<DeviceModel>> {

	DeviceAdapter deviceAdapter;
	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    deviceAdapter = new DeviceAdapter(this.getActivity());
	    setListAdapter(deviceAdapter);
	 // Start out with a progress indicator.
        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
	    
	  }
	
	 @Override
     public void onListItemClick(ListView l, View v, int position, long id) {
         // Insert desired behavior here.
         Log.i("DataListFragment", "Item clicked: " + id);
         Intent ApplianceIntent = new Intent(Home.this.getActivity(), ApplianceActivity.class);
	        startActivity(ApplianceIntent);
     }

     @Override
     public Loader<List<DeviceModel>> onCreateLoader(int arg0, Bundle arg1) {
         System.out.println("DataListFragment.onCreateLoader");
         return new DataListLoader(getActivity());
     }

     @Override
     public void onLoadFinished(Loader<List<DeviceModel>> arg0, List<DeviceModel> data) {
         deviceAdapter.setData(data);
         System.out.println("DataListFragment.onLoadFinished");
         // The list should now be shown.
         if (isResumed()) {
             setListShown(true);
         } else {
             setListShownNoAnimation(true);
         }
     }

     @Override
     public void onLoaderReset(Loader<List<DeviceModel>> arg0) {
         deviceAdapter.setData(null);
     }
     
     public static class DataListLoader extends AsyncTaskLoader<List<DeviceModel>> {
         
         List<DeviceModel> mModels;
  
         public DataListLoader(Context context) {
             super(context);
         }
  
         @Override
         public List<DeviceModel> loadInBackground() {
             System.out.println("DataListLoader.loadInBackground");
              
//              // You should perform the heavy task of getting data from 
//              // Internet or database or other source 
//              // Here, we are generating some Sample data
//  
//             // Create corresponding array of entries and load with data.
//             List<DeviceModel> entries = new ArrayList<DeviceModel>(5);
//             entries.add(new DeviceModel("Java", "2"));
//             entries.add(new DeviceModel("C++", "9"));
//             entries.add(new DeviceModel("Python", "6"));
//             entries.add(new DeviceModel("JavaScript", "10"));
             AssetManager assetManager = this.getContext().getAssets();
             InputStream deviceFile = null;
			try {
				deviceFile = assetManager.open("home_devices_list.xml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             if(DataProvider.devices.get("home") == null){
            	return DataProvider.getDeviceFromFile(deviceFile, "home");
             }
             return DataProvider.devices.get("home");
         }
          
         /**
          * Called when there is new data to deliver to the client.  The
          * super class will take care of delivering it; the implementation
          * here just adds a little more logic.
          */
         @Override public void deliverResult(List<DeviceModel> listOfData) {
             if (isReset()) {
                 // An async query came in while the loader is stopped.  We
                 // don't need the result.
                 if (listOfData != null) {
                     onReleaseResources(listOfData);
                 }
             }
             List<DeviceModel> oldApps = listOfData;
             mModels = listOfData;
  
             if (isStarted()) {
                 // If the Loader is currently started, we can immediately
                 // deliver its results.
                 super.deliverResult(listOfData);
             }
  
             // At this point we can release the resources associated with
             // 'oldApps' if needed; now that the new result is delivered we
             // know that it is no longer in use.
             if (oldApps != null) {
                 onReleaseResources(oldApps);
             }
         }
  
         /**
          * Handles a request to start the Loader.
          */
         @Override protected void onStartLoading() {
             if (mModels != null) {
                 // If we currently have a result available, deliver it
                 // immediately.
                 deliverResult(mModels);
             }
  
  
             if (takeContentChanged() || mModels == null) {
                 // If the data has changed since the last time it was loaded
                 // or is not currently available, start a load.
                 forceLoad();
             }
         }
  
         /**
          * Handles a request to stop the Loader.
          */
         @Override protected void onStopLoading() {
             // Attempt to cancel the current load task if possible.
             cancelLoad();
         }
  
         /**
          * Handles a request to cancel a load.
          */
         @Override public void onCanceled(List<DeviceModel> apps) {
             super.onCanceled(apps);
  
             // At this point we can release the resources associated with 'apps'
             // if needed.
             onReleaseResources(apps);
         }
  
         /**
          * Handles a request to completely reset the Loader.
          */
         @Override protected void onReset() {
             super.onReset();
  
             // Ensure the loader is stopped
             onStopLoading();
  
             // At this point we can release the resources associated with 'apps'
             // if needed.
             if (mModels != null) {
                 onReleaseResources(mModels);
                 mModels = null;
             }
         }
  
         /**
          * Helper function to take care of releasing resources associated
          * with an actively loaded data set.
          */
         protected void onReleaseResources(List<DeviceModel> apps) {}
          
     }

	} 

