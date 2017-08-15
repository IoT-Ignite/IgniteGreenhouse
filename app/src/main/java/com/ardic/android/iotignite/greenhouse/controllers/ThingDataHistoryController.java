package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.listeners.ThingDataHistoryAsyncTaskListener;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.model.ThingDataHistory;

/**
 * Created by yavuz.erzurumlu on 8/9/17.
 */

public class ThingDataHistoryController extends AsyncTask<Void, Void, ThingDataHistory> {

    private static final String TAG = ThingDataHistoryController.class.getSimpleName();
    private IgniteRestClient mIgniteRestClient;
    private Context appContext;
    private String deviceId;
    private String nodeId;
    private String thingId;
    private long startDate;
    private long endDate;
    private int dataLimit;
    private ThingDataHistory dataHistory;
    private ThingDataHistoryAsyncTaskListener mThingDataHistoryAsyncTaskListener;


    public ThingDataHistoryController(Context appContext, String deviceId, String nodeId,
                                      String thingId, long startDate, long endDate, int dataLimit,
                                      ThingDataHistoryAsyncTaskListener listener) {
        this.appContext = appContext;
        this.deviceId = deviceId;
        this.nodeId = nodeId;
        this.thingId = thingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dataLimit = dataLimit;
        this.mThingDataHistoryAsyncTaskListener = listener;


    }

    @Override
    protected ThingDataHistory doInBackground(Void... voids) {

        mIgniteRestClient = RestClientHolder.getInstance(appContext).getActiveClient();

        try {
            dataHistory = mIgniteRestClient.getThingDataHistory(deviceId, nodeId, thingId, startDate, endDate, dataLimit);

        } catch (IgniteRestClientException e) {
            Log.e(TAG, "LastThingDataController: " + e);
        }


        return dataHistory;

    }


    @Override
    protected void onPostExecute(ThingDataHistory dataHistory) {
        super.onPostExecute(dataHistory);

        if (mThingDataHistoryAsyncTaskListener != null) {
            mThingDataHistoryAsyncTaskListener.onDataHistoryTaskComplete(dataHistory);
        }
    }
}
