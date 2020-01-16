package com.example.assignment4;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class GPS implements LocationListener {
    Context context;

    public GPS(Context c) {
        context = c;
    }


    public Location getLocation() {

        if (ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context,"NOT GRANTED",Toast.LENGTH_SHORT).show();
            return null;
         }
        LocationManager lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        boolean isGPSenabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSenabled) {

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return  l;
        }else{
            Toast.makeText(context,"ENABLE GPS",Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
