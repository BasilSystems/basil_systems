package com.basilsystems.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.basilapp1.R;

public class DeviceAdapter extends ArrayAdapter<DeviceModel> {
    private final LayoutInflater mInflater;
 
    public DeviceAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_2);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public void setData(List<DeviceModel> data) {
        clear();
        if (data != null) {
            for (DeviceModel appEntry : data) {
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
            view = mInflater.inflate(R.layout.single_item, parent, false);
        } else {
            view = convertView;
        }
 
        DeviceModel item = getItem(position);
        ((TextView)view.findViewById(R.id.tv_label)).setText(item.getName());
        ((TextView)view.findViewById(R.id.tv_id)).setText(item.getId());
 
        return view;
    }
} 
