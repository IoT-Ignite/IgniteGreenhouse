package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.activities.LoginActivity;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;
import com.ardic.android.iotignite.lib.restclient.model.Device;
import com.ardic.android.iotignite.lib.restclient.model.DeviceContent;


/**
 * Created by codemania on 7/24/17.
 */

public class DeviceController extends AsyncTask<Void, Void, Device> {

    private static final String TAG = DROMController.class.getSimpleName();
    private IgniteRestClientManager mIgniteRestClientManager;
    private IgniteRestClient mIgniteRestClient;
    private String user;
    private String password;
    private Device device;

    public DeviceController(Context appContext, String user, String password) {
        this.user = user;
        this.password = password;

        mIgniteRestClientManager = IgniteRestClientManager.getInstance(appContext);
    }

    @Override
    protected Device doInBackground(Void... voids) {

        mIgniteRestClient = mIgniteRestClientManager.createClient(user, password, true);
        try {
            device = mIgniteRestClient.getDeviceSummary(user);
        } catch (IgniteRestClientException e) {
            Log.e(TAG, "DeviceController:" + e);
        }
        return device;
    }

    @Override
    protected void onPostExecute(Device device) {
        super.onPostExecute(device);
        Log.i(TAG, "Device : " + device);

        if (device != null) {
            for (DeviceContent content : device.getDeviceContents()) {
                if (content.getCurrentUser() != null && !TextUtils.isEmpty(content.getCurrentUser().getMail()) && content.getCurrentUser().getMail().equals(user)) {
                    Log.i(TAG, "Device Content: \n" + content);
                }
            }
        }
    }
}
