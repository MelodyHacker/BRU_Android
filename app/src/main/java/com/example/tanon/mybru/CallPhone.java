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
import java.util.ArrayList;

/**
 * Created by MelodyHacker on 10/18/2017.
 */

public class CallPhone extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    String call;
    int position;
    String[] ar;
    Url url = new Url();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admod);
        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("Position");
        ar = bundle.getStringArray("Contact");
        call = ar[position].toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", call, null));
        startActivity(intent);
    }
}


