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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
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

        Intent gwQRIntent = new Intent(Constants.Actions.ACTION_GW_QR_CODE_SUCCESS);
        gwQRIntent.putExtra(Constants.Extra.EXTRA_GW_QR_CODE, qrCode);
        setResult(RESULT_OK, gwQRIntent);
        finish();

    }
}
