package com.example.tanon.mybru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanon on 19/1/2561.
 */

public class Report extends Activity {
    RequestQueue requestQueue;
    Url url = new Url();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        final EditText editText = (EditText) findViewById(R.id.report_name);
        ImageView btn_sent = (ImageView) findViewById(R.id.report_sent);
        ImageView btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url.report, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AbstractMethodError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("report_name", editText.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(stringRequest);
                Toast.makeText(Report.this, "ขอบคุณที่รายงานปัญหาเราจะทำไปแก้ไข",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Report.this, MarkerOff.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Report.this, MarkerOff.class);
                startActivity(intent);
            }
        });

    }
}




