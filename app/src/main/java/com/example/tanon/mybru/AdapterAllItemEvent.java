package com.example.tanon.mybru;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MelodyHacker on 10/16/2017.
 */

public class AdapterAllItemEvent extends ArrayAdapter<GetSetItem> {

    Url url=new Url();
    ArrayList<GetSetItem> getSetItems;
    Context context;
    int resource;
    public String lat, lng;

    public AdapterAllItemEvent(Context context, int resource, ArrayList<GetSetItem> getSetItems) {
        super(context, resource, getSetItems);
        this.getSetItems = getSetItems;
        this.context = context;
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_event, null, true);
        }
        GetSetItem getset = getItem(position);
        String imgurl1 = url.imgevent + getset.getImageEvent1Item();
        String imgurl2 = url.imgevent + getset.getImageEvent2Item();
        String imgurl3 = url.imgevent + getset.getImageEvent3Item();


        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.imgevent1_item);
        Picasso.with(context).load(imgurl1.toString()).into(imageView1);


        ImageView imageView2 = (ImageView) convertView.findViewById(R.id.imgevent2_item);
        Picasso.with(context).load(imgurl2.toString()).into(imageView2);


        ImageView imageView3 = (ImageView) convertView.findViewById(R.id.imgevent3_item);
        Picasso.with(context).load(imgurl3.toString()).into(imageView3);


        TextView txtName = (TextView) convertView.findViewById(R.id.nameevent_item);
        txtName.setText(getset.getNameEventItem());


        TextView txtDescription = (TextView) convertView.findViewById(R.id.descriptionevent_item);
        txtDescription.setText(getset.getDescriptionEventItem());


        TextView txtStart = (TextView) convertView.findViewById(R.id.date_start_event_item);
        txtStart.setText(getset.getStartEventItem());


        TextView txtEnd = (TextView) convertView.findViewById(R.id.date_end_event_item);
        txtEnd.setText(getset.getEndEventItem());

        TextView txtLatEvent = (TextView) convertView.findViewById(R.id.lat_event_item);
        txtLatEvent.setText(getset.getLat_event());
        lat = getset.getLat_event();


        TextView txtLongEvent = (TextView) convertView.findViewById(R.id.long_event_item);
        txtLongEvent.setText(getset.getLong_event());
        lng = getset.getLong_event();

        return convertView;


    }


}

