package com.ardic.android.iotignite.lib.restclient.manager;

import android.content.Context;
import android.content.ContextWrapper;

import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;

/**
 * Ignite Rest Client Manager gets required information about specified tenant
 * and creates a client with this credentials.
 * <p>
 * Created by yavuz.erzurumlu on 07/07/2017.
 */

public class IgniteRestClientManager {

    private static IgniteRestClientManager mIgniteRestClientManager = null;

    private static Object instanceLock = new Object();
    private Object clientLock = new Object();

    private Context appContext;

    private IgniteRestClient.IgniteRestClientBuilder mBuilder = new IgniteRestClient.IgniteRestClientBuilder();

    private IgniteRestClientManager(Context applicationContext) {
        this.appContext = applicationContext;
    }

    public static IgniteRestClientManager getInstance(Context applicationContext) {

        synchronized (instanceLock) {
            if (mIgniteRestClientManager == null) {
                mIgniteRestClientManager = new IgniteRestClientManager(applicationContext);
            }
        }

        return mIgniteRestClientManager;

    }

    public IgniteRestClient createClient(String username, String password, boolean autoRefreshToken) throws IgniteRestClientException {

        IgniteRestClient mRestClient;
        synchronized (clientLock) {

            try {
                mRestClient = mBuilder
                        .userName(username)
                        .password(password)
                        .autoRefreshToken(autoRefreshToken)
                        .build();

            } catch (IgniteRestClientException e) {
                throw e;
            }

        }

        return mRestClient;
    }

}
