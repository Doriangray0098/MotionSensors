package com.example.motionsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope {
    public interface Listener
    {
        void onRotation(float rx, float ry, float rz);
    }

    private Listener Listener;
    public void setListener(Listener l)
    {
        Listener = l;
    }




    private SensorManager SensorManager;
    private Sensor Sensor;
    private SensorEventListener SensorEventListener;

    Gyroscope(Context context)
    {
        SensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor = SensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
            if (Listener != null)
            {
             Listener.onRotation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
public void register()
{
    SensorManager.registerListener(SensorEventListener, Sensor,SensorManager.SENSOR_DELAY_NORMAL);
}
public void unregister()
{
    SensorManager.unregisterListener(SensorEventListener);
}


}
