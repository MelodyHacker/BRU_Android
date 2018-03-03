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
 * Created by MelodyHacker on 10/16/2017.
 */


public class EventItemLoader extends AppCompatActivity {
    Url url = new Url();
    ArrayList<GetSetEvent> arrayListset;
    ArrayList<GetSetEvent> arrayListseach;
    ArrayList<GetSetItem> arrayListsend;
    ListView lv;
    ProgressDialog mProgressDialog;
    String[] arraysend, arraysendedit;
    String[] arrayseach;
    String contenteq;
    AutoCompleteTextView autoCompleteTextView = null;
    ArrayAdapter<String> adapterseach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_all);
        new DownloadJSON().execute();
        arrayListset = new ArrayList<>();
        arrayListsend = new ArrayList<>();
        arrayListseach = new ArrayList<>();
        lv = (ListView) findViewById(R.id.json_Listview);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //avd  10.0.2.2.json.php
                new ReadJSON().execute(url.jsonevent);
            }
        });
    }


    class ReadJSON extends AsyncTask<String, Integer, String> implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                contenteq = content;
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("events");
                arrayseach = new String[jsonArray.length()];
                if (jsonArray == null) {
                    Intent intent = new Intent(EventItemLoader.this, NotInterNet.class);
                    startActivity(intent);
                }
                int s = 1;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayListset.add(new GetSetEvent(
                            productObject.getString("event_image1"),
                            productObject.getString("event_name"),
                            productObject.getString("start_event"),
                            productObject.getString("end_event")
                    ));

                    arrayseach[i] = productObject.getString("event_name");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final AdapterEvents adapter = new AdapterEvents(
                    getApplicationContext(), R.layout.item_events_view_all, arrayListset
            );
            lv.setAdapter(adapter);
            mProgressDialog.dismiss();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    getAllItemEvent(position);
                }
            });

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
                    String ck;
                    for (int c = 0; c < arrayListset.size(); c++) {
                        ck = arrayseach[c];
                        ck.toString();
                        if (ck.equals(sc)) {
                            getAllItemEvent(c);
                            break;
                        } else if (c == arrayListseach.size() - 1) {
                            Toast.makeText(EventItemLoader.this, "ไม่พบข้อมูลที่ค้นหา", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edit_text_event);
            adapterseach = new ArrayAdapter<String>(EventItemLoader.this, android.R.layout.simple_dropdown_item_1line, arrayseach);
            autoCompleteTextView.setThreshold(1);
            autoCompleteTextView.setAdapter(adapterseach);
            autoCompleteTextView.setOnItemClickListener(this);
            autoCompleteTextView.setOnItemSelectedListener(this);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    public void getAllItemEvent(int position) {
        try {
            JSONObject jsonObject = new JSONObject(contenteq);
            JSONArray jsonArray = jsonObject.getJSONArray("events");
            JSONObject productObject = jsonArray.getJSONObject(position);
            if (jsonArray == null) {
                Intent intent = new Intent(EventItemLoader.this, NotInterNet.class);
                startActivity(intent);
            }
            arraysend = new String[9];
            arraysend[0] = productObject.getString("event_image1");
            arraysend[1] = productObject.getString("event_image2");
            arraysend[2] = productObject.getString("event_image3");
            arraysend[3] = productObject.getString("event_name");
            arraysend[4] = productObject.getString("event_description");
            arraysend[5] = productObject.getString("start_event");
            arraysend[6] = productObject.getString("end_event");
            arraysend[7] = productObject.getString("lat_event");
            arraysend[8] = productObject.getString("long_event");
            Intent intent = new Intent(EventItemLoader.this, EventAllData.class);
            intent.putExtra("ArrayEventEdit", arraysend);
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
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
            mProgressDialog = new ProgressDialog(EventItemLoader.this);
            mProgressDialog.setMessage("กำลังโหลดงานที่จัดขึ้น.......");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();

        }
    }


}





