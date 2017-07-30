package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.greenhouse.listeners.SignUpAsyncTaskListener;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;
import com.ardic.android.iotignite.lib.restclient.model.AppKey;
import com.ardic.android.iotignite.lib.restclient.model.CreateRestrictedUser;
import com.ardic.android.iotignite.lib.restclient.model.UserCreateCredentials;

/**
 * Created by codemania on 7/19/17.
 */

public class SignUpController extends AsyncTask<Void, Void, CreateRestrictedUser> {

    private static final String TAG = SignUpController.class.getSimpleName();
    private IgniteRestClientManager mIgniteRestClientManager;
    private IgniteRestClient mIgniteRestClient;
    private CreateRestrictedUser mRestrictedUser;
    private AppKey mAppKey;

    // UserCreate parameters

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private SignUpAsyncTaskListener mSignUpAsyncTaskListener;


    public SignUpController(Context appContext, String fName, String lName, String email, String password, SignUpAsyncTaskListener mListener) {
        mIgniteRestClientManager = IgniteRestClientManager.getInstance(appContext);
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = password;
        this.mSignUpAsyncTaskListener = mListener;
    }

    @Override
    protected CreateRestrictedUser doInBackground(Void... voids) {

        mIgniteRestClient = mIgniteRestClientManager.createClient(Constants.MASTER_TENANT_USER, Constants.MASTER_TENANT_PASS, true);

        try {
            mAppKey = mIgniteRestClient.getAppKey();

            UserCreateCredentials mUserCreateCredentials = new UserCreateCredentials(mAppKey.getAppKey(),
                    Constants.MASTER_TENANT_BRAND,
                    firstName,
                    lastName,
                    email,
                    password,
                    Constants.MASTER_TENANT_PROFILE);

            mRestrictedUser = mIgniteRestClient.createRestrictedUser(mUserCreateCredentials);
        } catch (IgniteRestClientException e) {
            Log.e(TAG, "SignUpController:" + e);
        }

        return mRestrictedUser;
    }

    @Override
    protected void onPostExecute(CreateRestrictedUser mRestrictedUser) {
        super.onPostExecute(mRestrictedUser);

        if (mSignUpAsyncTaskListener != null) {
            mSignUpAsyncTaskListener.onSignUpTaskComplete(mRestrictedUser);
        }
    }
}
