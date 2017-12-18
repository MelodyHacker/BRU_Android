package com.example.tanon.mybru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;

import me.toptas.fancyshowcase.FancyShowCaseView;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<GetSetImageMain> arrayList;
    ProgressDialog mProgressDialog;
    JSONArray array;
    ViewFlipper viewFlipper;
    String url = "https://tanonexecutioner.000webhostapp.com/upload/news/";
    String img1, img2, img3;
    GoogleMap map;
    int zoom = 15;
    Double lat = 14.990395303361007, lng = 103.10022532939911;
    LatLng position = new LatLng(lat, lng);
    GoogleMap mMap;
    String[] arMapak;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.all_img_item);
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(5000);
        final ImageView imgmap_show = (ImageView) findViewById(R.id.hide_map);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                anim(imgmap_show, "โปรดหลีกเลี่ยงเส้นทางดังต่อไปนี้");
            }
        }, 1000);
        final ImageView into = (ImageView) findViewById(R.id.into);
        into.setImageResource(R.drawable.question);
        into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Into.class);
                startActivity(intent);
            }
        });

        ImageView imgreport = (ImageView) findViewById(R.id.report);
        imgreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urldir = "https://tanonexecutioner.000webhostapp.com/Report.php";
                //  Toast.makeText(context, urldir,20).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urldir));
                startActivity(intent);
            }
        });
        ImageView imgphone = (ImageView) findViewById(R.id.callphone);
        imgphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Phone.class);
                startActivity(intent);
            }
        });
        ImageView imgmap = (ImageView) findViewById(R.id.mapimg);
        imgmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MarkerPlaces.class);
                startActivity(intent);
            }
        });
        ImageView imgevent = (ImageView) findViewById(R.id.event);
        imgevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainEvent.class);
                startActivity(intent);
            }
        });
        ImageView imgtoilet = (ImageView) findViewById(R.id.toilet);
        imgtoilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MarkerToilets.class);
                startActivity(intent);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////
        new DownloadJSON().execute();
        arrayList = new ArrayList<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://tanonexecutioner.000webhostapp.com/JsonNews.php");
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapmain);
        mapFragment.getMapAsync(this);
        Toast.makeText(MainActivity.this, "โปรดระวังเส้นทางดังต่อไปนี้",
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Marker marker;
        Bundle bundle = getIntent().getExtras();
        String[] arMap = bundle.getStringArray("arrayMarkerPoriline");
        arMapak = arMap;
        if (arMap == null) {
            Intent intent = new Intent(MainActivity.this, NotInterNet.class);
            startActivity(intent);
        } else {
/////////////////////////////////////////////////////////////////////
            for (int x = 0; x < arMap.length; x++) {
                String name = arMap[x];
                Double lat = Double.parseDouble(arMap[x + 1]);
                Double lng = Double.parseDouble(arMap[x + 2]);
                x = x + 2;
                LatLng position = new LatLng(lat, lng);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));
                marker = mMap.addMarker(new MarkerOptions().position(position).icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).title(name));
            }
            LatLng position = new LatLng(14.990395303361007, 103.10022532939911);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
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
                JSONArray jsonArray = jsonObject.getJSONArray("news");
                array = jsonArray;
                Random rand = new Random();
                int n = rand.nextInt(jsonArray.length());
                JSONObject productObject = jsonArray.getJSONObject(n);
                img1 = productObject.getString("news_image1");
                img2 = productObject.getString("news_image2");
                img3 = productObject.getString("news_image3");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (img1 == null | img2 == null | img3 == null) {
                Intent intent = new Intent(MainActivity.this, NotInterNet.class);
                startActivity(intent);
            }
            ImageView imageView1 = (ImageView) findViewById(R.id.new11);
            Picasso.with(getApplicationContext()).load(url + img1).into(imageView1);
            ImageView imageView2 = (ImageView) findViewById(R.id.new12);
            Picasso.with(getApplicationContext()).load(url + img2).into(imageView2);
            ImageView imageView3 = (ImageView) findViewById(R.id.new13);
            Picasso.with(getApplicationContext()).load(url + img3).into(imageView3);

            //stopprogress
            mProgressDialog.dismiss();

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
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog message
            mProgressDialog.setMessage("Welcome To MyBRU Wait Pleas...........");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            // mProgressDialog.show();

        }
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
