package com.example.tanon.mybru;

/**
 * Created by MelodyHacker on 11/1/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

public class MarkerOff extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String[] ar;
    Url url = new Url();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admod);
        if (isNetworkConnected() == true) {

            new MarkerOff.DownloadJSON().execute();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //avd  10.0.2.2.json.php
                    new MarkerOff.ReadJSON().execute(url.jsonporiline);
                }
            });
        } else {
            Intent intent = new Intent(MarkerOff.this, NotInterNet.class);
            startActivity(intent);
        }


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
                JSONArray jsonArray = jsonObject.getJSONArray("poriline");
                int x = 0;
                ar = new String[jsonArray.length() * 3];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject MarkObject = jsonArray.getJSONObject(i);
                    ar[x] = MarkObject.getString("poriline_name");
                    ar[x + 1] = MarkObject.getString("poriline_lat");
                    ar[x + 2] = MarkObject.getString("poriline_long");
                    x = x + 3;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mProgressDialog.dismiss();
            ///////////////////////////////////////////////////////////////
            Intent intent = new Intent(MarkerOff.this, MainActivity.class);
            intent.putExtra("arrayMarkerPoriline", ar);
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
            mProgressDialog = new ProgressDialog(MarkerOff.this);
            // Set progressdialog title
            mProgressDialog.setMessage("ยินดีต้อนรับกำลังโหลดข้อมูล......");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


}

