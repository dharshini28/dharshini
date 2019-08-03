package com.codinginflow.smartalarm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    Controllerdb controldb;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> alarmname = new ArrayList<String>();
    private ArrayList<String> time = new ArrayList<String>();
    public CustomAdapter(Context  context,ArrayList<String> Id,ArrayList<String> alarmname, ArrayList<String> time)
    {
        this.mContext = context;
        this.Id = Id;
        this.alarmname = alarmname;
        this.time = time;
    }
    @Override
    public int getCount() {
        return Id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        controldb =new Controllerdb(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout, null);
            holder = new viewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.id);
            holder.alarmname = (TextView) convertView.findViewById(R.id.alarmname);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.id.setText(Id.get(position));
        holder.alarmname.setText(alarmname.get(position));
        holder.time.setText(time.get(position));
        return convertView;
    }
    public class viewHolder {
        TextView id;
        TextView alarmname;
        TextView time;
    }
}