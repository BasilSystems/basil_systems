package com.basilsystems.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.basilsystems.data.Devices;
import com.basilsystems.data.Places;

public class DatabaseHandler extends SQLiteOpenHelper {
	 
    private static final String NAME = "name";

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
 
    // places table name
    private static final String PLACES = "places";
    
 // devices table name
    private static final String DEVICES = "devices";
 
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLACES_TABLE = "CREATE TABLE " + PLACES + "("
                + "id" + " INTEGER PRIMARY KEY," + NAME + " TEXT,"+ ")";
        db.execSQL(CREATE_PLACES_TABLE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		  // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + PLACES);
 
        // Create tables again
        onCreate(db);
	}
    
    public void setPlaces(Places place){
    	
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	ContentValues cVal = new ContentValues();
    	
    	cVal.put(NAME, place.getName());
    	
    	db.insert(PLACES, null, cVal);
    	
    	

    	db.close();
     
    }
    
    public List<Places> getAllPlaces(){
    	
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.query(PLACES, new String[] { NAME }, null, null, null, null, null, null);
    	
    	List<Places> places = new ArrayList<Places>();
    	
    	if(cursor.moveToFirst()){
    		do{
    			places.add(new Places(cursor.getString(0)));	
    		}while(cursor.moveToNext());
    	}
    	return places;
    }
    
    public void deletePlace(Places place){
    	
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(PLACES, NAME + " = ?", new String[]{place.getName()});
    	
    	db.close();
    }
    
    public void renamePlace(String oldName, String newName){
                   
    	SQLiteDatabase db = this.getWritableDatabase();
    	if(oldName != null && newName != null && !oldName.isEmpty() && !newName.isEmpty()){
    		
    		ContentValues cVal = new ContentValues();
    		cVal.put(NAME, newName);
    		
    		db.update(PLACES, cVal, NAME + " = ?", new String[]{oldName});
    	}
    }
    
    public void setDevices(Devices device){
    	
    }
    
    public List<Devices> getAllDevices(Places place){
    	return null;
    }
    
    public void renameDevice(Devices device){
    	
    }
    
    public void deleteDevice(Devices device){
    	
    }
 
}
