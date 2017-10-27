package com.example.smyrno.androidsensors;

import android.content.Context;
import android.util.Log;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

import sensor_msgs.Imu;

/**
 * http://wiki.ros.org/rosjava/Tutorials/Create%20a%20ROS%20Android%20Node#Update_AccelerationTalker_to_publish_the_current_value.
 */
public class SensorPublisher implements NodeMain {
    private static final String TAG = SensorPublisher.class.getName();

    private ImuListener mImuListener;

    public SensorPublisher(Context context) {
        mImuListener = new ImuListener(context);
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        final Publisher<sensor_msgs.Imu> publisher =
                connectedNode.newPublisher("Publisher/Imu", "sensor_msgs/Imu");
        final CancellableLoop aLoop = new CancellableLoop() {
            @Override
            protected void loop() throws InterruptedException {
                Imu imuMessage = publisher.newMessage();
                mImuListener.fillHeader(imuMessage.getHeader());
                mImuListener.fillOrientation(imuMessage.getOrientation());
                mImuListener.fillOrientationCovariance(imuMessage.getOrientationCovariance());
                mImuListener.fillAngularVelocity(imuMessage.getAngularVelocity());
                mImuListener.fillAngularVelocityCovariance(imuMessage.getAngularVelocityCovariance());
                mImuListener.fillLinearAcceleration(imuMessage.getLinearAcceleration());
                mImuListener.fillLinearAccelerationCovariance(imuMessage.getLinearAccelerationCovariance());
                publisher.publish(imuMessage);
                Thread.sleep(1000);
            }
        };
        // This CancellableLoop will be cancelled automatically when the Node
        // shuts down.
        connectedNode.executeCancellableLoop(aLoop);
        mImuListener.onResume();
    }

    @Override
    public GraphName getDefaultNodeName() {
        return null;
    }

    @Override
    public void onShutdownComplete(Node node) {
        Log.d(TAG, node.getName() + " shut down");
    }

    @Override
    public void onError(Node node, Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }

    @Override
    public void onShutdown(Node node) {
        Log.d(TAG, "Shutting down " + node.getName());
        mImuListener.onPause();
    }
}
