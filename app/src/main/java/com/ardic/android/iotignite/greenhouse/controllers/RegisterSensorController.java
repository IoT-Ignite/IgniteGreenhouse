package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.icu.text.DateFormat;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.listeners.RegisterSensorControllerAsyncTaskListener;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.model.ActionMessage;

/**
 * Created by yavuz.erzurumlu on 7/31/17.
 */

public class RegisterSensorController extends AsyncTask<Void, Void, Boolean> {


    private static final String TAG = RegisterSensorController.class.getSimpleName();
    private IgniteRestClient mIgniteRestClient;

    private ActionMessage mActionMessage;
    private Context appContext;
    private String deviceCode;
    private RegisterSensorControllerAsyncTaskListener mRegisterSensorControllerAsyncTaskListener;

    public RegisterSensorController(Context appContext, String deviceCode, ActionMessage actionMessage,
                                    RegisterSensorControllerAsyncTaskListener mListener) {
        this.appContext = appContext;
        this.mActionMessage = actionMessage;
        this.deviceCode = deviceCode;
        this.mRegisterSensorControllerAsyncTaskListener = mListener;

        mIgniteRestClient = RestClientHolder.getInstance(appContext).getActiveClient();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean result = false;
        try {
            Log.e(TAG, "Device Code: " + deviceCode);

            Log.e(TAG, "Pushing action message: " + mActionMessage.getParams());
            result = mIgniteRestClient.pushActionMessageToThing(deviceCode, mActionMessage);
        } catch (IgniteRestClientException e) {
            Log.e(TAG, "RegisterSensorController" + e);
        }

        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (mRegisterSensorControllerAsyncTaskListener != null) {
            mRegisterSensorControllerAsyncTaskListener.onRegisterSensorTaskComplete(result);
        }

    }
}
