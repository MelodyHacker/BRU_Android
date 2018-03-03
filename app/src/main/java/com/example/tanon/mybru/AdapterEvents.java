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

public class AdapterEvents extends ArrayAdapter<GetSetEvent> {

    Url url = new Url();
    ArrayList<GetSetEvent> getSetEvents;
    Context context;
    int resource;
    public int ck;


    public AdapterEvents(Context context, int resource, ArrayList<GetSetEvent> getSetEvents) {
        super(context, resource, getSetEvents);
        this.getSetEvents = getSetEvents;
        this.context = context;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
///////////////////////////////
        ck = position;
//////////////////////////////

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_events_view_all, null, true);
        }
        GetSetEvent getset = getItem(position);
        String imgurl = url.imgevent + getset.getImageEvent1();


        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.imgevent1);
        Picasso.with(context).load(imgurl.toString()).into(imageView1);



        TextView txtName = (TextView) convertView.findViewById(R.id.nameevent);
        txtName.setText(getset.getNameEvent());



        TextView txtStart = (TextView) convertView.findViewById(R.id.date_start_event);
        txtStart.setText(getset.getStartEvent());


        TextView txtEnd = (TextView) convertView.findViewById(R.id.date_end_event);
        txtEnd.setText(getset.getEndEvent());


        return convertView;
    }

}

