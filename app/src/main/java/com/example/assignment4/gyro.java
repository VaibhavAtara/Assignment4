package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class gyro extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor gyrometer;
    private TextView xvalue,yvalue,zvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);

        xvalue = (TextView)findViewById(R.id.xvalue);
        yvalue = (TextView)findViewById(R.id.yvalue);
        zvalue = (TextView)findViewById(R.id.zvalue);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        gyrometer = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyrometer !=null){
            sensorManager.registerListener(this,gyrometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            xvalue.setText("GYROMETER NOT SUPPORTED");
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
