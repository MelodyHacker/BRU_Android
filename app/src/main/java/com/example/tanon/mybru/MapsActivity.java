package com.example.tanon.mybru;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    GoogleMap mMap;
    float zoom = 17;
    String[] arMapak;
    AutoCompleteTextView autoCompleteTextView = null;
    private ArrayAdapter<String> adapter;
    String item[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the maps is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Marker marker;
        Bundle bundle = getIntent().getExtras();
        String[] arMap = bundle.getStringArray("arrayMarker");
        arMapak = arMap;
        if (arMap == null) {
            Intent intent = new Intent(MapsActivity.this, NotInterNet.class);
            startActivity(intent);
        }
        item = new String[arMap.length / 3];
        int i = 0;
        for (int x = 0; x < arMap.length; x++) {
            String name = arMap[x];
            item[i] = arMap[x].toString();
            Double lat = Double.parseDouble(arMap[x + 1]);
            Double lng = Double.parseDouble(arMap[x + 2]);
            x = x + 2;
            LatLng position = new LatLng(lat, lng);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));
            marker = mMap.addMarker(new MarkerOptions().position(position).title(name));
            i++;
        }
        LatLng position = new LatLng(14.990395303361007, 103.10022532939911);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.editTextmap);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemSelectedListener(this);
        autoCompleteTextView.setOnItemClickListener(this);
        autoCompleteTextView.setImeOptions(EditorInfo.IME_ACTION_DONE);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ImageView imageView = (ImageView) findViewById(R.id.btn_search);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                EditText editText = (EditText) findViewById(R.id.editTextmap);
                String sc = editText.getText().toString();
                String ck;
                Marker marker;
                Double lat;
                Double lng;
                String latst = "", lngst = "";
                for (int c = 0; c < arMapak.length; c = c + 3) {
                    ck = arMapak[c];
                    ck.toString();
                    //String.equals() notequals  ==
                    if (ck.equals(sc)) {

                        lat = Double.parseDouble(arMapak[c + 1]);
                        lng = Double.parseDouble(arMapak[c + 2]);
                        latst = lat.toString();
                        lngst = lng.toString();
                        LatLng position = new LatLng(lat, lng);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));
                        marker = mMap.addMarker(new MarkerOptions().position(position).title(arMapak[c]));
                        marker.showInfoWindow();

                    }
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getBaseContext(), "คุณได้กดเลือก : " + parent.getItemAtPosition(position),
                Toast.LENGTH_LONG).show();

    }
}






