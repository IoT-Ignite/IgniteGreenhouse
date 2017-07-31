package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.listeners.LastThingDataAsyncTaskListener;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.model.LastThingData;

/**
 * Created by codemania on 7/29/17.
 */

public class LastThingDataController extends AsyncTask<Void, Void, LastThingData> {

    private static final String TAG = LastThingDataController.class.getSimpleName();

    private IgniteRestClient mIgniteRestClient;
    private Context appContext;
    private String nodeId;
    private String thingId;
    private String deviceId;
    private String thingType;
    private int connected;
    private LastThingData data;
    private LastThingDataAsyncTaskListener mLastThingDataAsyncTaskListener;

    public LastThingDataController(Context appContext, String deviceId, String nodeId,
                                   String thingId, String thingType, int connected,
                                   LastThingDataAsyncTaskListener mListener) {
        this.appContext = appContext;
        this.deviceId = deviceId;
        this.nodeId = nodeId;
        this.thingId = thingId;
        this.thingType = thingType;
        this.connected = connected;
        this.mLastThingDataAsyncTaskListener = mListener;
    }

    @Override
    protected LastThingData doInBackground(Void... voids) {

        mIgniteRestClient = RestClientHolder.getInstance(appContext).getActiveClient();
        try {
            data = mIgniteRestClient.getLastData(deviceId, nodeId, thingId);

        } catch (IgniteRestClientException e) {
            Log.e(TAG, "LastThingDataController: " + e);
        }

        return data;
    }

    @Override
    protected void onPostExecute(LastThingData lastThingData) {
        super.onPostExecute(lastThingData);


        if (mLastThingDataAsyncTaskListener != null) {
            mLastThingDataAsyncTaskListener.onLastThingDataTaskComplete(nodeId, thingId, thingType, connected, lastThingData);
        }
    }
}
