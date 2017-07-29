package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;

/**
 * Created by yavuz.erzurumlu on 7/28/17.
 */

public class DeviceNodeInventoryController extends AsyncTask<Void, Void, DeviceNodeInventory> {

    private static final String TAG = DeviceNodeInventoryController.class.getSimpleName();
    private String deviceId;
    private IgniteRestClient mIgniteRestClient;
    private Context appContext;

    public DeviceNodeInventoryController(Context appContext, String deviceId) {
        this.appContext = appContext;
        this.deviceId = deviceId;
    }

    @Override
    protected DeviceNodeInventory doInBackground(Void... voids) {
        DeviceNodeInventory mDeviceNodeInventory = null;

        mIgniteRestClient = RestClientHolder.getInstance(appContext).getActiveClient();

        try {
            mDeviceNodeInventory = mIgniteRestClient.getDeviceNodeInventory(deviceId);

        } catch (IgniteRestClientException e) {
            Log.e(TAG, "DeviceNodeInventoryController: " + e);
        }
        return mDeviceNodeInventory;

    }

    @Override
    protected void onPostExecute(DeviceNodeInventory deviceNodeInventory) {
        super.onPostExecute(deviceNodeInventory);
        Log.i(TAG, "Device Node Inventory: " + deviceNodeInventory);
    }
}
