package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;

/**
 * Created by yavuz.erzurumlu on 7/28/17.
 */

public class DeviceNodeInventoryController extends AsyncTask<Void, Void, DeviceNodeInventory> {

    private static final String TAG = DeviceNodeInventoryController.class.getSimpleName();
    private String user;
    private String password;
    private String deviceId;
    private IgniteRestClientManager mIgniteRestClientManager;
    private IgniteRestClient mIgniteRestClient;

    public DeviceNodeInventoryController(Context appContext, String user, String password, String deviceId) {
        this.user = user;
        this.password = password;
        this.deviceId = deviceId;
        mIgniteRestClientManager = IgniteRestClientManager.getInstance(appContext);
    }

    @Override
    protected DeviceNodeInventory doInBackground(Void... voids) {
        DeviceNodeInventory mDeviceNodeInventory = null;

        mIgniteRestClient = mIgniteRestClientManager.createClient(user, password, false);

        mDeviceNodeInventory = mIgniteRestClient.getDeviceNodeInventory(deviceId);

        return mDeviceNodeInventory;

    }

    @Override
    protected void onPostExecute(DeviceNodeInventory deviceNodeInventory) {
        super.onPostExecute(deviceNodeInventory);
        Log.i(TAG, "Device Node Inventory: " + deviceNodeInventory);
    }
}
