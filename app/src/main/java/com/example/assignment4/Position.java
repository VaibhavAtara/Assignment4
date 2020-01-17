package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Position extends AppCompatActivity {

    private Button location_button,map_button,share_button;
    private TextView coordinates;
    private LocationManager locationManager;
    private LocationListener locationListener;
    double lat,log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);


        location_button = (Button) findViewById(R.id.location_button);
        coordinates = (TextView) findViewById(R.id.coordinates);
        map_button = (Button)findViewById(R.id.map_button);
        share_button = (Button)findViewById(R.id.share);
        share_button.setVisibility(View.GONE);
        map_button.setVisibility(View.GONE);
        ActivityCompat.requestPermissions(Position.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPS gps = new GPS(getApplicationContext());
                Location location = gps.getLocation();

                if(location!=null) {
                    lat = location.getLatitude();
                    log = location.getLongitude();
                    coordinates.setText("Co-ordinates:" + lat + "," + log);
                    map_button.setVisibility(View.VISIBLE);
                    share_button.setVisibility(View.VISIBLE);

                }


            }
        });


        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String geo ="geo:"+lat+","+log;
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(geo));
                startActivity(intent);
                //map_button.setVisibility(View.GONE);
            }
        });


        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent sendIntent = new Intent();
                //sendIntent.setAction(Intent.ACTION_SEND);
                //sendIntent.putExtra(Intent.EXTRA_TEXT, "Location:"+lat+","+log);
                //sendIntent.setType("text/plain");
                //***********************************

                //sendIntent.putExtra(Intent.CATEGORY_APP_MAPS,"geo:"+lat+","+log);
                //sendIntent.setType("text/plain");

                String uri = "http://maps.google.com/maps?z=24&q=" +lat+","+log;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String ShareSub = "Here is my location";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, ShareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, uri);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

                //**********************************
                //startActivity(sendIntent);
                //share_button.setVisibility(View.GONE);
            }
        });



    }
}
