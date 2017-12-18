package com.example.tanon.mybru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by MelodyHacker on 10/18/2017.
 */

public class ItemEvent extends AppCompatActivity {
    ArrayList<GetSetItem> arrayListitem;
    ListView lv;
    ProgressDialog mProgressDialog;
    JSONArray array;
    String ct;
    int po;

    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_all);
        Toast.makeText(ItemEvent.this, "เลือกที่ปุ่มเพื่อนำทาง",
                Toast.LENGTH_LONG).show();

        ////////////////////////////////
        Bundle bundle = getIntent().getExtras();
        po = bundle.getInt("Position");

        ///////////////////////////////////
        new DownloadJSON().execute();
        arrayListitem = new ArrayList<>();
        lv = (ListView) findViewById(R.id.json_Listview);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://tanonexecutioner.000webhostapp.com/JsonEvents.php");
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }


    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                ct = content;
                JSONObject jsonObjectEvent = new JSONObject(content);
                JSONArray jsonArrayEvent = jsonObjectEvent.getJSONArray("events");
                if (jsonArrayEvent == null) {
                    Intent intent = new Intent(ItemEvent.this, NotInterNet.class);
                    startActivity(intent);
                }

                JSONObject dbObject = jsonArrayEvent.getJSONObject(po);
                arrayListitem.add(new GetSetItem(
                        dbObject.getString("event_image1"),
                        dbObject.getString("event_image2"),
                        dbObject.getString("event_image3"),
                        dbObject.getString("event_name"),
                        dbObject.getString("event_description"),
                        dbObject.getString("start_event"),
                        dbObject.getString("end_event"),
                        dbObject.getString("lat_event"),
                        dbObject.getString("long_event")


                ));

            } catch (JSONException e) {
                e.printStackTrace();
            }


            ItemAdapter adapter = new ItemAdapter(
                    getApplicationContext(), R.layout.events_item, arrayListitem
            );
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
                    //  Toast.makeText(context, urldir,20).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urldir));
                    startActivity(intent);
//                    ImageView imageView=(ImageView)findViewById(R.id.btn_dir);
//                    imageView.setVisibility(View.VISIBLE);
                }
            });

            lv.setAdapter(adapter);
            //stopprogress
            mProgressDialog.dismiss();


        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {

            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ItemEvent.this);
            // Set progressdialog message
            mProgressDialog.setMessage("กำลังโหลดงานที่จัดขึ้น......");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }
    }


}