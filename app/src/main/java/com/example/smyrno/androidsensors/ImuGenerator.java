package com.example.smyrno.androidsensors;

import geometry_msgs.Quaternion;
import geometry_msgs.Vector3;
import std_msgs.Header;

/**
 * http://docs.ros.org/api/sensor_msgs/html/msg/Imu.html
 */
public interface ImuGenerator {
    void fillHeader(Header header);
    void fillOrientation(Quaternion orientation);
    void fillOrientationCovariance(double[] orientationCovariance);
    void fillAngularVelocity(Vector3 angularVelocity);
    void fillAngularVelocityCovariance(double[] angularVelocityCovariance);
    void fillLinearAcceleration(Vector3 linearAcceleration);
    void fillLinearAccelerationCovariance(double[] linearAccelerationCovariance);
}
