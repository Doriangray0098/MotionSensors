package com.example.motionsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {


    public interface Listener{
        void onTransalation(float tx, float ty, float tz);
    }
    private Listener Listener;

    public void setListener(Listener l)
    {
        Listener= l;
    }

    private SensorManager SensorManager;
    private Sensor Sensor;
    private SensorEventListener SensorEventListener;

    Accelerometer(Context context){
      SensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
      Sensor = SensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
      SensorEventListener = new SensorEventListener() {
          @Override
          public void onSensorChanged(SensorEvent sensorEvent) {
            if (Listener !=null)
            {
             Listener.onTransalation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            }


          }

          @Override
          public void onAccuracyChanged(Sensor sensor, int i) {

          }
      };
    }
    public void register()
    {
        SensorManager.registerListener(SensorEventListener, Sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void unregister()
    {
        SensorManager.unregisterListener(SensorEventListener);
    }
}
