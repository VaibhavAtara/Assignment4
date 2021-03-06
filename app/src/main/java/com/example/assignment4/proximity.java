package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class proximity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor Proximity;
    private TextView proximityvalue;
    LinearLayout proximity_layout;
    private Ringtone r;
    private Uri notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        proximityvalue = (TextView)findViewById(R.id.proximityvalue);
        proximity_layout = (LinearLayout)findViewById(R.id.proximity_layout);
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(Proximity !=null){
            sensorManager.registerListener(this,Proximity,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            proximityvalue.setText("PROXIMITY SENSOR NOT SUPPORTED");

        }

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        proximityvalue.setText(""+(sensorEvent.values[0]));
        if(sensorEvent.values[0] == 0)
        {
            proximity_layout.setBackgroundResource(R.drawable.custom_background2);
            r.play();

        }
        else
        {
            proximity_layout.setBackgroundResource(R.drawable.custom_background);
            r.stop();
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
