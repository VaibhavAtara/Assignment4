package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accelerometer;
    private TextView xvalue,yvalue,zvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        xvalue = (TextView)findViewById(R.id.xvalue);
        yvalue = (TextView)findViewById(R.id.yvalue);
        zvalue = (TextView)findViewById(R.id.zvalue);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer !=null){
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            xvalue.setText("ACCELEROMETER NOT SUPPORTED");
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
