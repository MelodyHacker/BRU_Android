package com.example.tanon.mybru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by MelodyHacker on 11/2/2017.
 */

public class NotInterNet extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_internet);
        final ImageView imageView = (ImageView) findViewById(R.id.again);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotInterNet.this, MarkerOff.class);
                startActivity(intent);
            }
        });
        final ImageView img_report_internet = (ImageView) findViewById(R.id.report_notinternet);
        img_report_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotInterNet.this, Report.class);
                Toast.makeText(NotInterNet.this, "ขอบคุณที่รายงานปัญหา", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        Toast.makeText(NotInterNet.this, "เชื่อมต่ออินเตอร์เน็ตมีปัญหา ลองอีกครั้ง", Toast.LENGTH_LONG).show();
        Toast.makeText(NotInterNet.this, "หรือระบบมีปัญหาโปรดรายงานปัญหาจากปุ่ม Report", Toast.LENGTH_LONG).show();
    }
}
