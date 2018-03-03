package com.example.tanon.mybru;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MelodyHacker on 10/18/2017.
 */

public class EventAllData extends AppCompatActivity {
    ListView lv;
    String[] arrayeq = new String[9];
    Url url = new Url();
    ArrayList<GetSetItem> getSetItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_all);
        lv = (ListView) findViewById(R.id.json_Listview);
        getSetItems = new ArrayList<>();
        ImageView seach = (ImageView) findViewById(R.id.btn_search);
        EditText auto = (EditText) findViewById(R.id.edit_text_event);
        seach.setVisibility(View.GONE);
        auto.setVisibility(View.GONE);
        ////////////////////////////////

        Bundle bundle = getIntent().getExtras();
        arrayeq = bundle.getStringArray("ArrayEventEdit");
        //////////////////////////////////
        getSetItems.add(new GetSetItem(
                arrayeq[0],
                arrayeq[1],
                arrayeq[2],
                arrayeq[3],
                arrayeq[4],
                arrayeq[5],
                arrayeq[6],
                arrayeq[7],
                arrayeq[8]));
        AdapterAllItemEvent adapter = new AdapterAllItemEvent(
                getApplicationContext(), R.layout.item_event, getSetItems
        );

        lv.setAdapter(adapter);
        ImageView imageView = (ImageView) findViewById(R.id.btn_dir);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView lat = (TextView) findViewById(R.id.lat_event_item);
                String latst = lat.getText().toString();
                TextView lng = (TextView) findViewById(R.id.long_event_item);
                String lngst = lng.getText().toString();
                String urldir = "https://www.google.com/maps/search/?api=1&query=" + latst + "," + lngst + "";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urldir));
                startActivity(intent);
            }
        });

        Toast.makeText(EventAllData.this, "เลือกที่ปุ่มเพื่อนำทางที่อยู่ทางด้านล่างขวา",
                Toast.LENGTH_LONG).show();
    }
}


