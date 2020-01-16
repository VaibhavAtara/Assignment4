package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class magnometer extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor magnometer;
    private TextView xvalue,yvalue,zvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnometer);

        xvalue = (TextView)findViewById(R.id.xvalue);
        yvalue = (TextView)findViewById(R.id.yvalue);
        zvalue = (TextView)findViewById(R.id.zvalue);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        magnometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(magnometer !=null){
            sensorManager.registerListener(this,magnometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            xvalue.setText("MAGNOMETER NOT SUPPORTED");
            yvalue.setText("");
            zvalue.setText("");
        }

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        xvalue.setText(""+(sensorEvent.values[0]));
        yvalue.setText(""+(sensorEvent.values[1]));
        zvalue.setText(""+(sensorEvent.values[2]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
