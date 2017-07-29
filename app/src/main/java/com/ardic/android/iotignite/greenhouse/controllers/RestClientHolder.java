package com.ardic.android.iotignite.greenhouse.controllers;


import android.content.Context;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;

/**
 * Created by codemania on 7/29/17.
 */

public class RestClientHolder {

    private static final String TAG = RestClientHolder.class.getSimpleName();
    private static RestClientHolder mHolder;

    private IgniteRestClientManager mIgniteRestClientManager;
    private IgniteRestClient mIgniteRestClient;
    private IgniteRestClient mMasterIgniteClient;
    private Context appContext;
    private String activeUser;
    private String activeUserPassword;
    private static Object lock = new Object();


    private RestClientHolder(Context appContext) {
        this.appContext = appContext;
    }

    public static RestClientHolder getInstance(Context appContext) {
        synchronized (lock) {
            if (mHolder == null) {
                mHolder = new RestClientHolder(appContext);
            }
        }
        return mHolder;
    }

    public boolean initClients(String activeUser, String password) {

        this.activeUser = activeUser;
        this.activeUserPassword = password;

        try {
            mIgniteRestClientManager = IgniteRestClientManager.getInstance(appContext);

            mIgniteRestClient = mIgniteRestClientManager.createClient(activeUser, password, true);

            mMasterIgniteClient = mIgniteRestClientManager.createClient(Constants.MASTER_TENANT_USER, Constants.MASTER_TENANT_PASS, true);
        } catch (IgniteRestClientException e) {
            Log.e(TAG, "initClients: " + e);
        }

        if (mIgniteRestClient != null && mMasterIgniteClient != null) {
            return true;
        }

        return false;
    }

    public IgniteRestClient getActiveClient() {
        return mIgniteRestClient;
    }

    public IgniteRestClient getMasterClient() {
        return mMasterIgniteClient;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public String getActiveUserPassword() {
        return activeUserPassword;
    }
}
