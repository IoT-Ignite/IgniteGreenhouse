package com.ardic.android.iotignite.greenhouse.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    private static final String TAG = QRScanActivity.class.getSimpleName();
    private ZXingScannerView mScannerView;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        Intent i = getIntent();

        if (i != null) {
            action = i.getAction();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onRestart() {
        super.onRestart();

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        // Do something with the result here

        String qrCode = result.getText();
        Log.i(TAG, qrCode); // Prints scan results
        Log.i(TAG, result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)


        if (!TextUtils.isEmpty(qrCode) && BarcodeFormat.QR_CODE == result.getBarcodeFormat()) {
            sendResult(qrCode);
        }

    }


    private void sendResult(String qrCode) {


        Intent resultIntent = new Intent();
        if (action.equals(Constants.Actions.ACTION_GW_QR_CODE)) {
            resultIntent.setAction(Constants.Actions.ACTION_GW_QR_CODE_SUCCESS);
            resultIntent.putExtra(Constants.Extra.EXTRA_GW_QR_CODE, qrCode);
            setResult(RESULT_OK, resultIntent);
        } else if (action.equals(Constants.Actions.ACTION_SENSOR_QR_CODE)) {

            resultIntent.setAction(Constants.Actions.ACTION_SENSOR_QR_CODE_SUCCESS);
            resultIntent.putExtra(Constants.Extra.EXTRA_SENSOR_QR_CODE, qrCode);
            setResult(RESULT_OK, resultIntent);
        } else {
            resultIntent.setAction(Constants.Actions.ACTION_QR_CODE_FAILURE);
            setResult(RESULT_CANCELED, resultIntent);
        }
        finish();

    }
}
