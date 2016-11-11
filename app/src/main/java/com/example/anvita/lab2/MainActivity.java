package com.example.anvita.lab2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.renderscript.Double2;
import android.renderscript.Float2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.Display;
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
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.BitmapDescriptor;


public class MainActivity extends AppCompatActivity {

    CustomDrawableView mCustomDrawableView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchResultsActivity.class)));

        return true;
    }

    Double lat = 0.0, lon = 0.0;

    String str_lat = "", str_lon = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomDrawableView = new CustomDrawableView(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("SJSU");

        Log.d("data","is "+getIntent().getExtras());

        if (getIntent().getExtras() == null) {
            Intent i = new Intent(getApplicationContext(), MyLocation.class);
            i.putExtra("building","main");
            startActivity(i);
        }else{
            lat = Double.valueOf(getIntent().getStringExtra("lat"));
            lon = Double.valueOf(getIntent().getStringExtra("lon"));

            str_lat = String.valueOf(lat);
            str_lon = String.valueOf(lon);

       //     Log.d("lat","lat is "+str_lat);
        //    Log.d("lon","lon is "+str_lon);
        }
    }

    public void findMyLocation(View v)
    {

        Log.d("click", "click my location");

   //     double xmap_min=37.334579;
    //    double xmap_max=37.337796;
     //   double ymap_min=-121.885898;
      //  double ymap_max=-121.876582;


        double xmap_min=37.331978;
        double xmap_max=37.338263;
        double ymap_min=-121.885898;
        double ymap_max=-121.879477;



        double xscreen_min=0;
        double xscreen_max=0;
        double yscreen_min=0;
        double yscreen_max=0;
       
        //To find user location
        double myloc_x=Double.valueOf(str_lat);
        double myloc_y= Double.valueOf(str_lon);

        //Check for some location within campus
      //  double myloc_x = 37.335786;
       // double myloc_y = -121.880150;


        boolean check = myloc_x < xmap_min;

        boolean check1 = myloc_x > xmap_min;

        if (myloc_x < xmap_min || myloc_x > xmap_max)
        {

            Toast.makeText(this, "You are outside campus", Toast.LENGTH_SHORT).show();
        }
        else if (myloc_y < ymap_min || myloc_y > ymap_max)
        {

            Toast.makeText(this, "You are outside campus", Toast.LENGTH_SHORT).show();
        }
        else {

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            xscreen_max = Double.parseDouble("" + size.x);
            yscreen_max = Double.parseDouble("" + size.y);
            double scale_x = (xscreen_max - xscreen_min) / (xmap_max - xmap_min); //screen:map x
            double scale_y = (yscreen_max - yscreen_min) / (ymap_max - ymap_min);

            double plot_x = (myloc_x-xmap_min)*scale_x; //calculates distance to be drawn on the screen
            double plot_y = (myloc_y-ymap_min)*scale_y;
            if (plot_x < 0)
                plot_x = plot_x * -1;
            if (plot_y < 0)
                plot_y = plot_y * -1;


            CustomDrawableUser user = (CustomDrawableUser) findViewById(R.id.user);
         //   user.setBackgroundColor(0xff74AC23);

            user.setBackgroundColor(Color.RED);

            //user.setX(Float.parseFloat(""+plot_x));
            //user.setY(Float.parseFloat(""+plot_y));



            int x_co = (int) plot_x;
            int y_co = (int) plot_y;
            Log.d("x co"," "+x_co);
            Log.d("y co"," "+y_co);

        //    user.setX(x_co - 400);
         //   user.setY(y_co + 400);


            if(y_co  > 1850){
                user.setY(y_co - 400);
            }else{
                user.setY(y_co - 600);
            }



            if(x_co < 1000 && x_co > 500){
                user.setX(x_co - 300);
            }else{
                user.setX(x_co);
            }



            Log.d("x","is "+user.getX());
            Log.d("y","is "+user.getY());


        }
    }
}

