package com.example.anvita.lab2;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.service.wallpaper.WallpaperService;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.widget.*;
import android.content.ComponentName;
import android.location.Location;
import android.location.*;
import android.widget.Toast;

import com.example.anvita.lab2.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
//import com.google.android.gms.location.LocationListener;
import android.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.BitmapDescriptor;

import org.w3c.dom.Text;

import static com.google.android.gms.analytics.internal.zzy.i;


public class MyLocation extends FragmentActivity implements Result,LocationListener {

    protected LocationManager locationmanager;
    Location location = null;

    String building;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchResultsActivity.class)));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        building = getIntent().getStringExtra("building");

        Log.d("building","calling building is "+building);

        locationmanager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        String provider = LocationManager.GPS_PROVIDER;


        if(provider!=null & !provider.equals(""))
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.INTERNET},10);
                location=locationmanager.getLastKnownLocation(provider);
                locationmanager.requestLocationUpdates(provider,0,0, this);
                return;
            }else{
                location=locationmanager.getLastKnownLocation(provider);
                locationmanager.requestLocationUpdates(provider,0,0, this);
                if(location!=null)
                {
                    onLocationChanged(location);
                }
                else{
                    Toast.makeText(getApplicationContext(),"location not found",Toast.LENGTH_LONG ).show();
                }
            }

        }
        else {
            Toast.makeText(getApplicationContext(), "Provider is null", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        if(location!=null)
        {
            onLocationChanged(location);
        }
        else{
            Toast.makeText(getApplicationContext(),"location not found",Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Latitude here",""+location.getLatitude());
        Log.d("Longitude",""+location.getLongitude());

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
        }else if(building.equals("main")){
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }else if(building.equals("SPG")){
            intent = new Intent(getApplicationContext(), ParkingGarage.class);
        }else{
            Log.d("case","unknown");
        }

        intent.putExtra("lat", String.valueOf(location.getLatitude()));
        intent.putExtra("lon", String.valueOf(location.getLongitude()));
        startActivity(intent);

    }
    public void onConnectionSuspended(int i) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    public void onProviderEnabled(String provider) {

    }


    public void onProviderDisabled(String provider) {

    }

    @Override
    public Status getStatus() {
        return null;
    }



}
