package com.example.motionsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private Accelerometer Accelerometer;
    private Gyroscope Gyroscope;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Accelerometer = new Accelerometer(this);
        Gyroscope = new Gyroscope(this);

        Accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTransalation(float tx, float ty, float tz) {
             if (tx > 1.0f)
             {
                 getWindow().getDecorView().setBackgroundColor(Color.RED);
             }
             else if (tx < -1.0f)
             {
                 getWindow().getDecorView().setBackgroundColor(Color.BLUE);
             }

            }
        });

        Gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if (rz > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                else if (rz < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Gyroscope.register();
        Accelerometer.register();
    }

    @Override
    protected void onPause() {
    Accelerometer.unregister();
    Gyroscope.unregister();

    }
}