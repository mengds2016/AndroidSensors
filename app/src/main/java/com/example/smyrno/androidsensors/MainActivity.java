package com.example.smyrno.androidsensors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ros.address.InetAddressFactory;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    SensorPublisher mSensorPublisher;
    NodeConfiguration mNodeConfiguration;
    URI uri;
    NodeMainExecutor mNodeMainExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText uriText = findViewById(R.id.master_chooser_uri);
        final Button okButton = findViewById(R.id.master_chooser_ok);
        final Button cancelButton = findViewById(R.id.master_chooser_cancel);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                try {
                    uri = new URI(uriText.getText().toString());
                    if (uri.toString().length() == 0) {
                        uri = NodeConfiguration.DEFAULT_MASTER_URI;
                    }
                    intent.putExtra("ROS_MASTER_URI", uri.toString());
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (URISyntaxException e) {
                    Toast.makeText(MainActivity.this, "Invalid URI",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        mSensorPublisher = new SensorPublisher(MainActivity.this);
        mNodeConfiguration = NodeConfiguration.newPublic(
                InetAddressFactory.newNonLoopback().getHostAddress());
        mNodeConfiguration.setMasterUri(uri);
        mNodeMainExecutor.execute(mSensorPublisher, mNodeConfiguration);
    }
}
