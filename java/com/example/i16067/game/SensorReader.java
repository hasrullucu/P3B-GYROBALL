package com.example.i16067.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorReader implements SensorEventListener{
    protected SensorManager sensorManager;
    protected Sensor accelerometer,magnetometer;
    protected MainActivity mainActivity;
    protected FragmentGame fragmentGame;

    public SensorReader(MainActivity mainActivity , FragmentGame fragmentGame){
        this.fragmentGame = fragmentGame;
        this.sensorManager=(SensorManager) mainActivity.getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer =this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.magnetometer=this.sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        this.mainActivity=mainActivity;
    }



    public void start(){
        this.sensorManager.registerListener( this,this.accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
        this.sensorManager.registerListener(this,this.magnetometer,SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stop(){
        this.sensorManager.unregisterListener( this,this.accelerometer);
        this.sensorManager.unregisterListener(this,this.magnetometer);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor==this.accelerometer){
            this.fragmentGame.updateAccXYZ(event.values);
            //this.mainActivity.setStatus(event.values);

//            this.mainActivity.arrayList.add((int)event.values[0]);
//            this.mainActivity.arrayList.add((int)event.values[1]);

            this.fragmentGame.redraw(event.values);

//            this.mainActivity.arrayList.remove(0);
//            this.mainActivity.arrayList.remove(0);
        }else if(event.sensor==this.magnetometer){
            //this.mainActivity.updateMagXAY(event.values);
        }
    }

    public void onAccuracyChanged(Sensor s,int i){

    }
}
