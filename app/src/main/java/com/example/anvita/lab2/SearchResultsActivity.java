package com.example.anvita.lab2;

/**
 * Created by anvita on 10/25/16.
 */

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.example.anvita.lab2.R;
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


public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleIntent(getIntent());
        getSupportActionBar().setTitle("SJSU");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);

    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    @Override
    protected void onStop() {
        super.onStop();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void handleIntent(Intent intent) {

        int option;

        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            String searchText = query.toLowerCase();

            if(searchText.equals("engineering building") || searchText.equals("engineering") || searchText.equals("eb")){
                Log.d("EB", "I have to work on engineering building");
                option = 1;
                select(option);

            }else if(searchText.equals("student union") || searchText.equals("su")){
                Log.d("KL", "I have to search union");
                option = 2;
                select(option);
            }else if(searchText.equals("king library") || searchText.equals("library") || searchText.equals("kl")){
                Log.d("EB", "I have to work on library");
                option = 3;
                select(option);
            }else if(searchText.equals("yoshihiro uchida hall") || searchText.equals("yoshihiro") || searchText.equals("yuh")){
                Log.d("EB", "I have to work on YUH");
                option = 4;
                select(option);
            }else if(searchText.equals("bbc") || searchText.equals("boccardo business complex") || searchText.equals("boccardo")) {
                Log.d("EB", "I have to work on BBC");
                option = 5;
                select(option);
            }else if(searchText.equals("south parking garage") || searchText.equals("spg") || searchText.equals("parking") || searchText.equals("garage")) {
                Log.d("EB", "I have to work on garage");
                option = 6;
                select(option);
            }else{
                Log.d("EB", "I have to work on others");
                option = 7;
                select(option);
            }
        }
    }

    public void select(int n){

        if(n == 1){
            CustomDrawableView engineeringBuilding = (CustomDrawableView) findViewById(R.id.EngineeringH);

            engineeringBuilding.setBackgroundColor(0xff74AC23);
            engineeringBuilding.setAlpha(new Float(0.70));

            engineeringBuilding.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Log.d("click", "I clicked engg building");

                    Intent i = new Intent(getApplicationContext(), Engineering.class);
                    startActivity(i);
                }
            });
        }else if(n == 2){
            CustomDrawableView studentUnion = (CustomDrawableView) findViewById(R.id.SUH);

            studentUnion.setBackgroundColor(0xff74AC23);
            studentUnion.setAlpha(new Float(0.70));


            studentUnion.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Log.d("click", "I clicked SU building");

                    Intent i = new Intent(getApplicationContext(), StudentUnion.class);
                    startActivity(i);
                }
            });
        }else if(n == 3){
            CustomDrawableView library = (CustomDrawableView) findViewById(R.id.LibraryH);

            library.setBackgroundColor(0xff74AC23);
            library.setAlpha(new Float(0.70));


            library.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Log.d("click", "I clicked library building");

                    Intent i = new Intent(getApplicationContext(), Library.class);
                    startActivity(i);
                }
            });
        }else if(n == 4){
            CustomDrawableView yoshihiroBuilding = (CustomDrawableView) findViewById(R.id.YUH);

            yoshihiroBuilding.setBackgroundColor(0xff74AC23);
            yoshihiroBuilding.setAlpha(new Float(0.70));


            yoshihiroBuilding.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Log.d("click", "I clicked YU building");

                    Intent i = new Intent(getApplicationContext(), yoshihiro.class);
                    startActivity(i);
                }
            });
        }else if(n == 5){
            CustomDrawableView bbc = (CustomDrawableView) findViewById(R.id.BBCH);

            bbc.setBackgroundColor(0xff74AC23);
            bbc.setAlpha(new Float(0.70));


            bbc.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Log.d("click", "I clicked BBC building");

                    Intent i = new Intent(getApplicationContext(), BBC.class);
                    startActivity(i);
                }
            });
        }else if(n == 6){
            CustomDrawableView sParkingGarage = (CustomDrawableView) findViewById(R.id.SPGH);

            sParkingGarage.setBackgroundColor(0xff74AC23);
            sParkingGarage.setAlpha(new Float(0.70));


            sParkingGarage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Log.d("click", "I clicked Garage building");

                    Intent i = new Intent(getApplicationContext(), ParkingGarage.class);
                    startActivity(i);
                }
            });
        }else{
            Log.d("click", "Invalid option");
            Toast.makeText(this, "Building Not found", Toast.LENGTH_SHORT).show();
        }


    }
}