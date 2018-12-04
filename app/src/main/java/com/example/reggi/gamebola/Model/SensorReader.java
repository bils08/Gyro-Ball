package com.example.reggi.gamebola.Model;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.reggi.gamebola.View.MainActivity;

public class SensorReader implements SensorEventListener {
    protected Bola bola;
    protected SensorManager manager;
    protected Sensor accelerometer;
    boolean startGame;

    public SensorReader(MainActivity mainActivity, Bola bola){
        this.bola = bola;
        this.manager = (SensorManager) mainActivity.getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(startGame) {
            this.bola.updatePosition(event.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void start(){
        this.manager.registerListener(this, this.accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop(){
        this.manager.unregisterListener(this, this.accelerometer);
    }
}
