package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class light extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor light;
    private TextView lightvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        lightvalue = (TextView)findViewById(R.id.lightvalue);


        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light !=null){
            sensorManager.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            lightvalue.setText("LIGHTMETER NOT SUPPORTED");

        }

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        lightvalue.setText(""+(sensorEvent.values[0]));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
