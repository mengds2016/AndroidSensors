package com.example.smyrno.androidsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.ros.internal.message.RawMessage;
import org.ros.message.Time;

import geometry_msgs.Quaternion;
import geometry_msgs.Vector3;
import std_msgs.Header;

public class ImuListener implements SensorEventListener, ImuGenerator {

    // variable to store the current value
    private float[] acceleration = new float[]{0f, 0f, 0f};
    Context context;

    public ImuListener(Context c) {
        this.context = c;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acceleration[0] = sensorEvent.values[0];
            acceleration[1] = sensorEvent.values[1];
            acceleration[2] = sensorEvent.values[2];
        }
    }

    /**
     * method to access the current value
     *
     * @return Acceleration value
     */
    public float[] getSensorValue() {
        return acceleration;
    }

    protected void onResume() {
        SensorManager sm = (SensorManager)
                context.getSystemService(Context.SENSOR_SERVICE);
        Sensor acceleration = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        sm.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onPause() {
        SensorManager sm = (SensorManager)
                context.getSystemService(Context.SENSOR_SERVICE);
        sm.unregisterListener(this);
    }

    @Override
    public void fillHeader(Header header) {
        header = new Header() {
            @Override
            public int getSeq() {
                return 0;
            }

            @Override
            public void setSeq(int i) {

            }

            @Override
            public Time getStamp() {
                return null;
            }

            @Override
            public void setStamp(Time time) {

            }

            @Override
            public String getFrameId() {
                return null;
            }

            @Override
            public void setFrameId(String s) {

            }

            @Override
            public RawMessage toRawMessage() {
                return null;
            }
        };
    }

    @Override
    public void fillOrientation(Quaternion orientation) {
        orientation = new Quaternion() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public void setX(double v) {

            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setY(double v) {

            }

            @Override
            public double getZ() {
                return 0;
            }

            @Override
            public void setZ(double v) {

            }

            @Override
            public double getW() {
                return 0;
            }

            @Override
            public void setW(double v) {

            }

            @Override
            public RawMessage toRawMessage() {
                return null;
            }
        };
    }

    @Override
    public void fillOrientationCovariance(double[] orientationCovariance) {
        orientationCovariance = new double[9];
    }

    @Override
    public void fillAngularVelocity(Vector3 angularVelocity) {
        angularVelocity = new Vector3() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public void setX(double v) {

            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setY(double v) {

            }

            @Override
            public double getZ() {
                return 0;
            }

            @Override
            public void setZ(double v) {

            }

            @Override
            public RawMessage toRawMessage() {
                return null;
            }
        };
    }

    @Override
    public void fillAngularVelocityCovariance(double[] angularVelocityCovariance) {
        angularVelocityCovariance = new double[9];
    }

    @Override
    public void fillLinearAcceleration(Vector3 linearAcceleration) {
        linearAcceleration = new Vector3() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public void setX(double v) {

            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setY(double v) {

            }

            @Override
            public double getZ() {
                return 0;
            }

            @Override
            public void setZ(double v) {

            }

            @Override
            public RawMessage toRawMessage() {
                return null;
            }
        };
    }

    @Override
    public void fillLinearAccelerationCovariance(double[] linearAccelerationCovariance) {
        linearAccelerationCovariance = new double[9];
    }
}
