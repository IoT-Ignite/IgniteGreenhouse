package com.ardic.android.iotignite.greenhouse.activities;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.greenhouse.R;
import com.ardic.android.iotignite.greenhouse.controllers.DROMController;
import com.ardic.android.iotignite.greenhouse.controllers.DeviceController;

import java.util.concurrent.ExecutionException;

public class GatewayDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = QRScanActivity.class.getSimpleName();
    private FloatingActionButton fabAddGateway;
    private String activeUser;
    private String activeUserPassword;
    private DROMController mDromController;
    private DeviceController mDeviceController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gateway);

        getLoginInfo();

        fabAddGateway = (FloatingActionButton) findViewById(R.id.activity_addgateway_fab_add_a_gateway);
        fabAddGateway.setOnClickListener(this);

        getDeviceInfo();
    }

    @Override
    public void onClick(View view) {
        /**
         * Start QR Scan Activity here.
         */
        if (view.equals(fabAddGateway)) {
            //TODO : Check camera permission here. - RunTime and Manifest.
            Intent intent = new Intent(GatewayDashboardActivity.this, QRScanActivity.class);
            startActivityForResult(intent, Constants.READ_QR_CODE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            String qr = data.getStringExtra(Constants.Extra.EXTRA_GW_QR_CODE);
            Log.i(TAG, "QR Code Received !" + qr);
            Toast.makeText(this, "QR Code Received !" + qr, Toast.LENGTH_LONG).show();

            mDromController = new DROMController(this, activeUser, activeUserPassword, qr);

            mDromController.execute();

            try {
                if (mDromController.get()) {

                    GatewayDashboardActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(GatewayDashboardActivity.this, "AWESOME ! DEVICE LICENCED SUCCESSFULLY. ", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } catch (InterruptedException e) {
                Log.e(TAG, "InterruptedException on onActivityResult function:" + e);
            } catch (ExecutionException e) {
                Log.e(TAG, "ExecutionException on onActivityResult function:" + e);
            }

        }
    }


    private void getLoginInfo() {

        Intent mIntent = getIntent();

        if (mIntent != null && mIntent.hasExtra(Constants.Extra.EXTRA_USERNAME)) {
            activeUser = mIntent.getStringExtra(Constants.Extra.EXTRA_USERNAME);
        }

        if (mIntent != null && mIntent.hasExtra(Constants.Extra.EXTRA_PASSWORD)) {
            activeUserPassword = mIntent.getStringExtra(Constants.Extra.EXTRA_PASSWORD);
        }
    }

    private void getDeviceInfo() {
        mDeviceController = new DeviceController(this, activeUser, activeUserPassword);
        mDeviceController.execute();
    }
}
