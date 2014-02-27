package com.basilsystems.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.basilapp1.R;

public class ApplianceAdapter extends ArrayAdapter<ApplianceModel> {
    private final LayoutInflater mInflater;
 
    public ApplianceAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_2);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//        ((TextView)view.findViewById(R.id.list_item)).setText(item.getName());
 
        return view;
    }
} 
