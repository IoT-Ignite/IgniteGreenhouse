package com.ardic.android.iotignite.lib.restclient.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.ardic.android.iotignite.lib.restclient.api.TokenService;
import com.ardic.android.iotignite.lib.restclient.constant.Api;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.listeners.RefreshTokenTaskListener;
import com.ardic.android.iotignite.lib.restclient.model.AccessToken;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by yavuz.erzurumlu on 8/9/17.
 */

public class RefreshTokenAsyncTask extends AsyncTask<Void, Void, AccessToken> {

    private static final String TAG = RefreshTokenAsyncTask.class.getSimpleName();
    private TokenService mTokenService;
    private AccessToken accessToken;
    private static final String REFRESH_GRANT_TYPE = "refresh_token";
    private RefreshTokenTaskListener mListener;


    public RefreshTokenAsyncTask(TokenService service, AccessToken token, RefreshTokenTaskListener listener) {
        this.mTokenService = service;
        this.accessToken = token;
        this.mListener = listener;
    }

    @Override
    protected AccessToken doInBackground(Void... voids) {

        int responseCode;
        AccessToken refreshToken;
        Call<AccessToken> accessTokenCall = mTokenService.getRefreshToken(REFRESH_GRANT_TYPE, accessToken.getRefreshToken());

        try {
            Response<AccessToken> accessTokenResponse = accessTokenCall.execute();
            responseCode = accessTokenResponse.code();

            refreshToken = accessTokenResponse.body();


            if (Api.DEBUG) {
                Log.d(TAG, "Code: \n" + responseCode);
                Log.d(TAG, "Error: \n" + accessTokenResponse.message());
                Log.d(TAG, "AccessToken: \n" + refreshToken.getAccessToken());
                Log.d(TAG, "Token Type: \n" + refreshToken.getTokenType());
                Log.d(TAG, "Refresh Token: \n" + refreshToken.getRefreshToken());
                Log.d(TAG, "Expires In: \n" + refreshToken.getExpiresIn());
                Log.d(TAG, "Scope: \n" + refreshToken.getScope());
                Log.d(TAG, "Response Code: " + refreshToken.getResponseCode());
            }

            accessToken = refreshToken;

        } catch (IOException e) {
            Log.e(TAG, "refreshToken(): " + e);
            throw new IgniteRestClientException(e);
        }

        return accessToken;
    }

    @Override
    protected void onPostExecute(AccessToken accessToken) {
        super.onPostExecute(accessToken);

        if (mListener != null) {
            mListener.onTokenRefreshed(accessToken);
        }
    }
}
