package com.ardic.android.iotignite.greenhouse.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;
import com.ardic.android.iotignite.lib.restclient.model.DromConfiguration;
import com.ardic.android.iotignite.lib.restclient.model.DromConfigurationParameters;
import com.ardic.android.iotignite.lib.restclient.model.DromDevice;
import com.ardic.android.iotignite.lib.restclient.model.EndUser;
import com.ardic.android.iotignite.lib.restclient.model.EndUserContent;

/**
 * Created by codemania on 7/22/17.
 */

public class DROMController extends AsyncTask<Void, Void, Boolean> {


    private static final String TAG = DROMController.class.getSimpleName();
    private IgniteRestClientManager mIgniteRestClientManager;
    private IgniteRestClient mIgniteRestClient;
    private String mail;
    private String password;
    private String deviceId;
    private String dromConfigId;
    private DromConfigurationParameters dromConfigurationParameters;
    private DromConfiguration dromConfiguration;
    private DromDevice dromDevice;
    private EndUser endUsers;
    private EndUserContent endUserContent;


    public DROMController(Context appContext, String mail, String password, String deviceId) {
        this.mail = mail;

        //TODO remove password
        this.password = password;
        this.deviceId = deviceId;

        mIgniteRestClientManager = IgniteRestClientManager.getInstance(appContext);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Log.i(TAG, "Creating client...");
        try {
            mIgniteRestClient = mIgniteRestClientManager.createClient(Constants.MASTER_TENANT_USER, Constants.MASTER_TENANT_PASS, true);


            Log.i(TAG, "Getting active end user...");
            endUsers = mIgniteRestClient.getEndUser(mail);


            for (EndUserContent end : endUsers.getContents()) {

                if (end.getMail().equals(mail)) {
                    endUserContent = end;
                    break;
                }
            }

            if (endUserContent != null) {

                Log.i(TAG, "Creating dromConfiguration parameters...");

                dromConfigurationParameters = new DromConfigurationParameters(
                        Constants.MASTER_APP_KEY,
                        endUserContent.getActivationCode(),
                        true,
                        Constants.MASTER_PROFILE,
                        Constants.MASTER_POLICY,
                        false,
                        false,
                        Constants.USER_CONFIG_MODE);


                dromConfiguration = new DromConfiguration(dromConfigurationParameters,
                        mail+deviceId,
                        Constants.MASTER_TENANT_DOMAIN);


                dromConfigId = mIgniteRestClient.createDromConfiguration(dromConfiguration);

                Log.i(TAG, "Adding drom to device...");

                dromDevice = new DromDevice(dromConfigId, deviceId, Constants.MASTER_TENANT_DOMAIN);

                if (mIgniteRestClient.addDromDevice(dromDevice)) {

                    Log.i(TAG, "Pushing drom to device...");
                    if (mIgniteRestClient.pushDromToDevice(deviceId)) {
                        Log.i(TAG, "Drom pushed to device successfully");

                        //TODO : check drom state until it finishes or timeout.
                        return true;
                    }
                }
            }


        } catch (IgniteRestClientException e) {
            Log.e(TAG, "DROMController:" + e);
            return false;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
