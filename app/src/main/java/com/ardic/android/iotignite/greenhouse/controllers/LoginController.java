package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.listeners.LoginAsyncTaskListener;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;

/**
 * Created by codemania on 7/19/17.
 */

public class LoginController extends AsyncTask<Void, Void, Boolean> {


    private static final String TAG = SignUpController.class.getSimpleName();
    private IgniteRestClient mIgniteRestClient;
    private String mail;
    private String password;
    private Context appContext;
    private LoginAsyncTaskListener mLoginControllerListener;


    public LoginController(Context appContext, String mail, String pass, LoginAsyncTaskListener mListener) {
        this.appContext = appContext;
        this.mail = mail;
        this.password = pass;
        this.mLoginControllerListener = mListener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Log.i(TAG, "Creating client...");
        try {
            RestClientHolder.getInstance(appContext).initClients(mail, password);
            mIgniteRestClient = RestClientHolder.getInstance(appContext).getActiveClient();
        } catch (IgniteRestClientException e) {
            Log.e(TAG, "LoginController:" + e);
            return false;
        }


        return (mIgniteRestClient != null);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (mLoginControllerListener != null) {
            mLoginControllerListener.onLoginTaskComplete(result);
        }

    }
}
