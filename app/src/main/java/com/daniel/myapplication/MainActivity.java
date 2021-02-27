package com.daniel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daniel.screenshot.SLogger;
import com.daniel.screenshot.Severity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        SLogger sLogger = SLogger.getInstance(this);

        requestPermissionAndSave();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sLogger.log(MainActivity.this, Severity.INFO, "Button pressed With Screenshot", true);
                sLogger.log(MainActivity.this, Severity.INFO, "Button pressed Without Screenshot", false);

                //Export all logs from Room to file
                sLogger.exportLogsToFile();

                //Delete all logs from Room & delete logs file from directory
                sLogger.cleanMyLogs();
            }
        });
    }

    private void requestPermissionAndSave() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
}