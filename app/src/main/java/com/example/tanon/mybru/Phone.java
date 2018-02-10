package com.example.tanon.mybru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
 * Created by MelodyHacker on 11/1/2017.
 */

public class Phone extends AppCompatActivity {
    ArrayList arrayList;
    ProgressDialog mProgressDialog;
    String[] ar;
    ListView lv;
    Url url=new Url();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_all);
        arrayList = new ArrayList();
        new Phone.DownloadJSON().execute();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //avd  10.0.2.2.json.php
                new Phone.ReadJSON().execute(url.jsonphone);
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
                JSONArray jsonArray = jsonObject.getJSONArray("phones");
                if (jsonArray == null) {
                    Intent intent = new Intent(Phone.this, NotInterNet.class);
                    startActivity(intent);
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new GetSetPhone(
                            productObject.getString("phone_name"),
                            productObject.getString("phone_number"),
                            productObject.getString("image_phone")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(Phone.this, "โหลดข้อมูลติดต่อเรียบร้อย",
                    Toast.LENGTH_LONG).show();

            final PhoneAdapter adapter = new PhoneAdapter(
                    getApplicationContext(), R.layout.listview_events, arrayList
            );

            lv = (ListView) findViewById(R.id.json_Listview);
            lv.setAdapter(adapter);
            //stopprogress
            mProgressDialog.dismiss();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


/////////////////////////////////////////////////////////

                    Intent intent = new Intent(Phone.this, CallPhone.class);
                    intent.putExtra("Position", position);
                    startActivity(intent);
//////////////////////////////////////////////////////////


                }
            });
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
            mProgressDialog = new ProgressDialog(Phone.this);
            // Set progressdialog title
            mProgressDialog.setMessage("กำลังโหลดข้อมูลติดต่อ........");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }
    }
}