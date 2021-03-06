package com.sense.tdevm;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public static final String TAG = "MainActivity";
    Button myButton;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }


    public void animateaccX(float x) {
        ImageView img_animation = (ImageView) findViewById(R.id.layout_image);
        if (x < -1) {
            TranslateAnimation animation = new TranslateAnimation(0.0f, 233.4f, 0.0f, 0.0f);
            animation.setDuration(1500);
            //animation.setFillAfter(true);
            img_animation.startAnimation(animation);
        } else if (x > 5) {
            TranslateAnimation animation = new TranslateAnimation(0.0f, -232.4f, 0.0f, 0.0f);
            animation.setDuration(1500);
            // animation.setFillAfter(true);
            img_animation.startAnimation(animation);
        }
    }

    public void animateaccY(float y) {
        ImageView img_animation = (ImageView) findViewById(R.id.layout_image);
        if (y > 5) {
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 295.7f);
            animation.setDuration(1500);
            // animation.setFillAfter(true);
            img_animation.startAnimation(animation);
        } else if (y < -1) {
            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -388.5f);
            animation.setDuration(1500);
            //animation.setFillAfter(true);
            img_animation.startAnimation(animation);
        }
    }
    @Override
    public void onSensorChanged(final SensorEvent sensorEvent) {
        myButton = (Button)findViewById(R.id.start);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float accX = sensorEvent.values[0];
                float accY = sensorEvent.values[1];
                if (accX < -1 || accX > 5) {
                    animateaccX(accX);
                } else if (accY < -1 || accY > 5) {
                    animateaccY(accY);
                }

            }
        });
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, "onAccuracyChanged");
    }


    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();

    }

}
