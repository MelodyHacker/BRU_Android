package com.example.tanon.mybru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Phone extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ProgressDialog mProgressDialog;
    String[] ar;
    ListView lv;
    Url url = new Url();
    String contentsend;
    String[] arrayseach;
    AutoCompleteTextView autoCompleteTextView = null;
    ArrayAdapter<String> adapterseach;
    String ck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_all);
        new Phone.DownloadJSON().execute();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //avd  10.0.2.2.json.php
                new Phone.ReadJSON().execute(url.jsonphone);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    private void getCall(int c) {
        try {
            JSONObject jsonObject = new JSONObject(contentsend);
            JSONArray jsonArray = jsonObject.getJSONArray("phones");
            if (jsonArray == null) {
                Intent intent = new Intent(Phone.this, NotInterNet.class);
                startActivity(intent);
            }

            JSONObject PhoneObject = jsonArray.getJSONObject(c);
            for (int i = 0; i < jsonArray.length(); i++) {
                ar[i] = PhoneObject.getString("phone_number");
            }
            Intent intent = new Intent(Phone.this, CallPhone.class);
            intent.putExtra("Position", c);
            intent.putExtra("Contact", ar);
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    class ReadJSON extends AsyncTask<String, Integer, String> implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
        ArrayList<GetSetPhone> arrayList;

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            contentsend = content;
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("phones");
                if (jsonArray == null) {
                    Intent intent = new Intent(Phone.this, NotInterNet.class);
                    startActivity(intent);
                }

                arrayList = new ArrayList<>();
                int x = 0;
                ar = new String[jsonArray.length() * 3];
                arrayseach = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject PhoneObject = jsonArray.getJSONObject(i);
                    ar[i] = PhoneObject.getString("phone_number");
                    arrayList.add(new GetSetPhone(
                            arrayseach[i] = PhoneObject.getString("phone_name"),
                            PhoneObject.getString("phone_number"),
                            PhoneObject.getString("image_phone")
                    ));
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////
                autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edit_text_event);
                adapterseach = new ArrayAdapter<String>(Phone.this, android.R.layout.simple_dropdown_item_1line, arrayseach);
                autoCompleteTextView.setThreshold(1);
                autoCompleteTextView.setAdapter(adapterseach);
                autoCompleteTextView.setOnItemClickListener(this);
                autoCompleteTextView.setOnItemSelectedListener(this);
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////}
                ImageView btn_search = (ImageView) findViewById(R.id.btn_search);
                btn_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        EditText editText = (EditText) findViewById(R.id.edit_text_event);
                        String sc = editText.getText().toString();

                        for (int c = 0; c <= arrayseach.length; c++) {
                            ck = arrayseach[c];
                            ck.toString();
                            if (ck.equals(sc)) {
                                getCall(c);
                                break;
                            } else if (c == ar.length - 1) {
                                Toast.makeText(Phone.this, "ไม่พบข้อมูลที่ค้นหา", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(Phone.this, "โหลดข้อมูลติดต่อเรียบร้อย",
                    Toast.LENGTH_LONG).show();

            final AdapterPhone adapter = new AdapterPhone(
                    getApplicationContext(), R.layout.item_events_view_all, arrayList
            );

            lv = (ListView) findViewById(R.id.json_Listview);
            lv.setAdapter(adapter);
            mProgressDialog.dismiss();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/////////////////////////////////////////////////////////
                    Intent intent = new Intent(Phone.this, CallPhone.class);
                    intent.putExtra("Position", position);
                    intent.putExtra("Contact", ar);
                    startActivity(intent);
//////////////////////////////////////////////////////////
                }
            });
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

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
            mProgressDialog = new ProgressDialog(Phone.this);
            mProgressDialog.setMessage("กำลังโหลดข้อมูลติดต่อ........");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();

        }
    }
}