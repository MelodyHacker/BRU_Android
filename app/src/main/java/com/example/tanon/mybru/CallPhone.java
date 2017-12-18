package com.example.tanon.mybru;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
 * Created by MelodyHacker on 10/18/2017.
 */

public class CallPhone extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    String call;
    int po;

    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        po = bundle.getInt("Position");

        ///////////////////////////////////
        new DownloadJSON().execute();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://tanonexecutioner.000webhostapp.com/JsonPhones.php");
            }
        });
        Toast.makeText(CallPhone.this, "กดเลือกรายการที่ต้องการเพื่อโทรออก",
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

                JSONObject jsonObjectEvent = new JSONObject(content);
                JSONArray jsonArrayEvent = jsonObjectEvent.getJSONArray("phones");
                JSONObject dbObject = jsonArrayEvent.getJSONObject(po);
                call = dbObject.getString("phone_number");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            mProgressDialog.dismiss();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", call, null));
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
            mProgressDialog = new ProgressDialog(CallPhone.this);
            // Set progressdialog message
            mProgressDialog.setMessage("กำลังโหลดเบอร์โทรติดต่อ....");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }
    }


}