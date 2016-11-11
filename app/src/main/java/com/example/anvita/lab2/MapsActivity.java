package com.example.anvita.lab2;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.anvita.lab2.R;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

public class MapsActivity extends AppCompatActivity {

    String building;
    LatLng select;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        building = getIntent().getStringExtra("building");
        Log.d("building","is "+building);
        Intent i = getIntent();
        String lat = i.getStringExtra("lat");
        String lon = i.getStringExtra("lon");

        select = new LatLng(Double.valueOf(lat), Double.valueOf(lon));

        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment)
                        getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        // Only set the panorama to SYDNEY on startup (when no panoramas have been
                        // loaded which is when the savedInstanceState is null).
                        if (savedInstanceState == null) {
                            panorama.setPosition(select);
                            //panorama.setPosition("San José State University Charles W. Davidson College of Engineering, San Jose, CA 95112");
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), BBC.class);

        if(building.equals("BBC")){
            intent = new Intent(getApplicationContext(), BBC.class);
        }else if(building.equals("EB")){
            intent = new Intent(getApplicationContext(), Engineering.class);
        }else if(building.equals("KL")){
            intent  = new Intent(getApplicationContext(), Library.class);
        }else if(building.equals("SU")){
            intent = new Intent(getApplicationContext(), StudentUnion.class);
        }else if(building.equals("YUH")){
            intent = new Intent(getApplicationContext(), yoshihiro.class);
        }else if(building.equals("SPG")){
            intent = new Intent(getApplicationContext(), ParkingGarage.class);
        }else{
            Log.d("case","unknown");
        }
        startActivity(intent);

        return true;
    }
}