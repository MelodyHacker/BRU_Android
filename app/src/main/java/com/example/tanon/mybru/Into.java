package com.example.tanon.mybru;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import me.toptas.fancyshowcase.FancyShowCaseView;

public class Into extends AppCompatActivity implements OnMapReadyCallback {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapmain);
        mapFragment.getMapAsync(this);
        Toast.makeText(Into.this, "โปรดระวังเส้นทางดังต่อไปนี้",
                Toast.LENGTH_LONG).show();
        final ImageView imgskip = (ImageView) findViewById(R.id.into);
        final ImageView imgmap_show = (ImageView) findViewById(R.id.hide_map);
        imgmap_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim(imgmap_show, "เห็นแผนที่นี้ไหมละวังเส้นทางที่ถูกปิดกันด้วยนะ");
            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                anim(imgskip, "หากเข้าใจแล้วกดปุ่มนี้เพื่อกลับไปยังหน้าหลัก");
            }
        }, 1000);
        imgskip.setImageResource(R.drawable.arrow);
        imgskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Into.this, MarkerOff.class);
                startActivity(intent);
            }
        });
        final ImageView imgreport = (ImageView) findViewById(R.id.report);
        imgreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(imgreport, "คุณสามารถรายงานข้อผิดพลาดของระบบหรือข้อมูลไปยังผู้ดูแลระบบได้จากปุ้มนี้");
            }
        });
        final ImageView imgevent = (ImageView) findViewById(R.id.event);
        imgevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(imgevent, "คุณสามารถค้นหางานหรือสถานที่จัดงานขึ้นได้จากตรงนี้..และหากต้องการให้แสดงรายระเอียด หรือให้เรานำทางโปรดกดเลือกที่รายการและกดปุ่มมุมล่างขวา ");
            }
        });
        final ImageView imgmap = (ImageView) findViewById(R.id.mapimg);
        imgmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(imgmap, "สามารถหาสถานที่ต่าง ๆ ได้จากตรงนี้นะจ้ะ..และอย่าลืมหากต้องการให้เรานำทางเลือกสถานทีและ กดเลือกที่ Markerก่อนแล้วกดปุ่มมุมล่างขวาเพื่อนำทาง");
            }
        });
        final ImageView imgtoilet = (ImageView) findViewById(R.id.toilet);
        imgtoilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(imgtoilet, "ปวดเบาใช่ไหม หรือปวดหนักปุ่มนี้ซิ..และอย่าลืมหากต้องการให้เรานำทางเลือกสถานทีและกดเลือกที่ Marker่ก่อนแล้วกดปุ่มมุมล่างขวาเพื่อนำทาง");
            }
        });
        final ImageView imgphone = (ImageView) findViewById(R.id.callphone);
        imgphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim(imgphone, "คุณสามารถหาเบอร์โทรติดต่อได้จาก List ของปุ้มนี้และกดเลือกเพื่อโทรออก");
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Double lat = 14.990395303361007, lng = 103.10022532939911;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 18));
    }

    public void anim(final View v, String s) {
        Animation exitAnimation = AnimationUtils.loadAnimation(this, R.anim.side_top);
        FancyShowCaseView fancy;
        fancy = new FancyShowCaseView.Builder(this)
                .focusOn(v)
                .title(s).enterAnimation(exitAnimation)
                .enterAnimation(exitAnimation)
                .build();
        fancy.show();
//        .enterAnimation(exitAnimation)
    }

}
