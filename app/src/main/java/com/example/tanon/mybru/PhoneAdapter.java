package com.example.tanon.mybru;

/**
 * Created by MelodyHacker on 11/1/2017.
 */

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

public class PhoneAdapter extends ArrayAdapter<GetSetPhone> {


    ArrayList<GetSetPhone> getSetPhones;
    Context context;
    int resource;
    public int ck;


    public PhoneAdapter(Context context, int resource, ArrayList<GetSetPhone> getSetPhones) {
        super(context, resource, getSetPhones);

        this.getSetPhones = getSetPhones;
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
            convertView = layoutInflater.inflate(R.layout.item_phone, null, true);
        }
        GetSetPhone getset = getItem(position);
        String imgurl = "https://tanonexecutioner.000webhostapp.com/upload/phones/" + getset.getImagePhone();


        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.img_phone);
        Picasso.with(context).load(imgurl.toString()).into(imageView1);



        TextView txtName = (TextView) convertView.findViewById(R.id.namephone_item);
        txtName.setText(getset.getNamePhone());



        TextView txtNumber = (TextView) convertView.findViewById(R.id.number_item);
        txtNumber.setText(getset.getPhone());



        return convertView;
    }

}

