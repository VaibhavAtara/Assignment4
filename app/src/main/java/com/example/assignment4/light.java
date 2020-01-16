package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class light extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor light;
    private TextView lightvalue;
    LinearLayout light_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        lightvalue = (TextView)findViewById(R.id.lightvalue);
        light_layout = (LinearLayout)findViewById(R.id.light_layout);


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

        if(sensorEvent.values[0]>0 && sensorEvent.values[0]<50)
            light_layout.setBackgroundColor(Color.parseColor("#7955d4"));

        if(sensorEvent.values[0]>49 && sensorEvent.values[0]<100)
            light_layout.setBackgroundColor(Color.parseColor("#552fb5"));

        if(sensorEvent.values[0]>99 && sensorEvent.values[0]<150)
            light_layout.setBackgroundColor(Color.parseColor("#411aa3"));

        if(sensorEvent.values[0]>149 && sensorEvent.values[0]<200)
            light_layout.setBackgroundColor(Color.parseColor("#3108dc"));

        if(sensorEvent.values[0]>199)
            light_layout.setBackgroundResource(R.drawable.custom_background);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
