package com.example.anvita.lab2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BBC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Boccardo Business Complex");

        Double lat = 0.0, lon = 0.0;

        try {
            Intent currentIntent = getIntent();
            //  String data = getIntent().getExtras().toString();


            Log.d("check","data "+getIntent().getExtras());

            if (getIntent().getExtras() == null) {
                Log.d("check", "1st call so null");
                Intent i = new Intent(getApplicationContext(), MyLocation.class);
                i.putExtra("building","BBC");
                startActivity(i);
            } else {
                Log.d("check", "2nd call so get values");
                lat = Double.valueOf(currentIntent.getStringExtra("lat"));
                lon = Double.valueOf(currentIntent.getStringExtra("lon"));

                String start_lat = String.valueOf(lat);
                String start_lon = String.valueOf(lon);
                String end_lat = String.valueOf(37.336561);
                String end_lon = String.valueOf(-121.878723);

                final String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + start_lat + "," + start_lon + "&destination=" + end_lat + "," + end_lon + "&mode=walking&sensor=false";

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String strFileContents = "";
                        try {
                            URL url1 = new URL(url);
                            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
                            try {
                                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                                byte[] contents = new byte[1024];

                                int bytesRead = 0;

                                while ((bytesRead = stream.read(contents)) != -1) {
                                    strFileContents += new String(contents, 0, bytesRead);
                                }
                            } finally {
                                urlConnection.disconnect();
                            }
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(strFileContents);

                            JSONArray array = jsonObject.getJSONArray("routes");
                            JSONObject routes = array.getJSONObject(0);
                            JSONArray legs = routes.getJSONArray("legs");
                            JSONObject steps = legs.getJSONObject(0);
                            JSONObject dis = steps.getJSONObject("distance");
                            JSONObject dur = steps.getJSONObject("duration");
                            final String distance = dis.getString("text");
                            final String duration = dur.getString("text");

                            Log.i("Distance", distance);
                            Log.i("duration", duration);
                            if (distance != "") {
                                //operator.setText(distance);
                            } else {
                                //operator.setText("blah");
                            }

                            if (duration != "") {
                                //operator.setText(distance);
                            } else {
                                //operator.setText("blah");
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    TextView tv_time = (TextView) findViewById(R.id.tv_time);
                                    TextView tv_dist = (TextView) findViewById(R.id.tv_distance);

                                    tv_dist.setText(distance);
                                    tv_time.setText(duration);

                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("ok","home clicked");

        switch (item.getItemId()) {
            case android.R.id.home:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Quit");
                builder.setMessage("Do you want to go back?").setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                        Intent x = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(x);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        onResume();
                    }
                }).show().getWindow().setLayout(850, 800);


                //   NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void streetViewPress(View v)
    {
        Log.d("click", "click street view on BBC");

        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        i.putExtra("lat", String.valueOf(37.336668));
        i.putExtra("lon", String.valueOf(-21.878377));
        i.putExtra("building","BBC");
        startActivity(i);


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
}
