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
 * Created by MelodyHacker on 10/30/2017.
 */

public class MarkerToilets extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String[] ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admod);


        new MarkerToilets.DownloadJSON().execute();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //avd  10.0.2.2.json.php
                new MarkerToilets.ReadJSON().execute("https://tanonexecutioner.000webhostapp.com/JsonToilets.php");
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
                JSONArray jsonArray = jsonObject.getJSONArray("toilets");
                int x=0;
                ar=new String[jsonArray.length()*3];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject MarkObject = jsonArray.getJSONObject(i);
                    ar[x] = MarkObject.getString("toilet_name");
                    ar[x+1] = MarkObject.getString("toilet_lat");
                    ar[x+2] = MarkObject.getString("toilet_long");
                    x=x+3;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MarkerToilets.this, "โหลดแผนที่เรียบร้อย",
                    Toast.LENGTH_LONG).show();
            mProgressDialog.dismiss();
            ///////////////////////////////////////////////////////////////
            Intent intent = new Intent(MarkerToilets.this, MapsToilet.class);
            intent.putExtra("arrayMarkerToilet",ar);
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
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MarkerToilets.this);
            // Set progressdialog message
            mProgressDialog.setMessage("โปรดรอกำลังโหลดแผนที่.......");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }
    }


}


