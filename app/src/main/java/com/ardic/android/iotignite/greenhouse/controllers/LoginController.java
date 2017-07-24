package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;

/**
 * Created by codemania on 7/19/17.
 */

public class LoginController extends AsyncTask<Void, Void, Boolean> {


    private static final String TAG = SignUpController.class.getSimpleName();
    private IgniteRestClientManager mIgniteRestClientManager;
    private IgniteRestClient mIgniteRestClient;
    private String mail;
    private String password;


    public LoginController(Context appContext, String mail, String pass) {
        this.mail = mail;
        this.password = pass;
        mIgniteRestClientManager = IgniteRestClientManager.getInstance(appContext);

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Log.i(TAG, "Creating client...");
        try {
            mIgniteRestClient = mIgniteRestClientManager.createClient(mail, password, true);
        } catch (IgniteRestClientException e) {
            Log.e(TAG, "LoginController:" + e);
            return false;
        }


        return (mIgniteRestClient != null);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
