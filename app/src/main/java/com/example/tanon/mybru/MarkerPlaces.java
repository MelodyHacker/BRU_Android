package com.example.tanon.mybru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by MelodyHacker on 10/26/2017.
 */

public class MarkerPlaces extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    String[] ar;
    Url url=new Url();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admod);
        new MarkerPlaces.DownloadJSON().execute();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //avd  10.0.2.2.json.php
                new MarkerPlaces.ReadJSON().execute(url.jsonplace);
            }
        });
        Toast.makeText(MarkerPlaces.this, "สถานที่",
                Toast.LENGTH_LONG).show();
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
                JSONArray jsonArray = jsonObject.getJSONArray("places");
                int x = 0;
                ar = new String[jsonArray.length() * 3];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject MarkObject = jsonArray.getJSONObject(i);
                    ar[x] = MarkObject.getString("place_name");
                    ar[x + 1] = MarkObject.getString("lat_location");
                    ar[x + 2] = MarkObject.getString("long_location");
                    x = x + 3;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MarkerPlaces.this, "โหลดข้อมูลเรียบร้อย",
                    Toast.LENGTH_LONG).show();
            mProgressDialog.dismiss();
            ///////////////////////////////////////////////////////////////
            Intent intent = new Intent(MarkerPlaces.this, MainMap.class);
            intent.putExtra("arrayMarker", ar);
            startActivity(intent);
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
            mProgressDialog = new ProgressDialog(MarkerPlaces.this);
            mProgressDialog.setMessage("โปรดรอกำลังโหลดแผนที่...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
    }


}


