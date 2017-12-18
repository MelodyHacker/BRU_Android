package com.example.tanon.mybru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by MelodyHacker on 10/16/2017.
 */


public class MainEvent extends AppCompatActivity {

    ArrayList<GetSetEvent> arrayList;
    ListView lv;
    ProgressDialog mProgressDialog;
    JSONArray array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_all);
        new DownloadJSON().execute();
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.json_Listview);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //avd  10.0.2.2.json.php
                new ReadJSON().execute("https://tanonexecutioner.000webhostapp.com/JsonEvents.php");
            }
        });

    }


    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("events");
                if (jsonArray==null){
                    Intent intent = new Intent(MainEvent.this, NotInterNet.class);
                    startActivity(intent);
                }
                array = jsonArray;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new GetSetEvent(
                            productObject.getString("event_image1"),
                            productObject.getString("event_name"),
                            productObject.getString("start_event"),
                            productObject.getString("end_event")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            final EventAdapter adapter = new EventAdapter(
                    getApplicationContext(), R.layout.listview_events, arrayList
            );


            lv.setAdapter(adapter);
            //stopprogress
            mProgressDialog.dismiss();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


/////////////////////////////////////////////////////////

                    Intent intent = new Intent(MainEvent.this, ItemEvent.class);
                    intent.putExtra("Position", position);
                    startActivity(intent);
//////////////////////////////////////////////////////////


                }
            });


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
            mProgressDialog = new ProgressDialog(MainEvent.this);
            // Set progressdialog message
            mProgressDialog.setMessage("กำลังโหลดงานที่จัดขึ้น.......");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }
    }


}





