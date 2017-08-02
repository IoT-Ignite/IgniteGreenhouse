package com.ardic.android.iotignite.lib.restclient.manager;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.ardic.android.iotignite.lib.restclient.api.IgniteAPIService;
import com.ardic.android.iotignite.lib.restclient.api.TokenService;
import com.ardic.android.iotignite.lib.restclient.constant.Api;
import com.ardic.android.iotignite.lib.restclient.constant.ErrorMessages;
import com.ardic.android.iotignite.lib.restclient.constant.ResponseCode;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientException;
import com.ardic.android.iotignite.lib.restclient.exception.IgniteRestClientThrowable;
import com.ardic.android.iotignite.lib.restclient.model.AccessToken;
import com.ardic.android.iotignite.lib.restclient.model.ActionMessage;
import com.ardic.android.iotignite.lib.restclient.model.AppKey;
import com.ardic.android.iotignite.lib.restclient.model.CreateRestrictedUser;
import com.ardic.android.iotignite.lib.restclient.model.Device;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;
import com.ardic.android.iotignite.lib.restclient.model.DromConfiguration;
import com.ardic.android.iotignite.lib.restclient.model.DromDevice;
import com.ardic.android.iotignite.lib.restclient.model.EndUser;
import com.ardic.android.iotignite.lib.restclient.model.LastThingData;
import com.ardic.android.iotignite.lib.restclient.model.SysUserInfo;
import com.ardic.android.iotignite.lib.restclient.model.ThingDataHistory;
import com.ardic.android.iotignite.lib.restclient.model.UserCreateCredentials;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yavuz.erzurumlu on 07/07/2017.
 */

public class IgniteRestClient {

    private static final String TAG = IgniteRestClient.class.getSimpleName();
    private boolean autoRefresh = true;
    private static final String AUTH_REQUEST_TOKEN = "Basic ZnJvbnRlbmQ6";
    private static final String GRANT_TYPE = "password";
    private static final String REFRESH_GRANT_TYPE = "refresh_token";

    private Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private Retrofit mRetrofit;

    private OkHttpClient.Builder httpClientBuilder;

    private Call<AccessToken> callAccess;

    private TokenService mTokenService;

    private IgniteAPIService mIgniteService;

    private AccessToken accessToken;
    private long refreshTimeInMillis = -1L;

    private String dromConfigId;

    private Runnable tokenWatchDog = new Runnable() {
        @Override
        public void run() {

            if (Api.DEBUG) {
                Log.i(TAG, "Refresh token watchdog on duty.");
            }
            refreshToken();
            tokenWatchdogHandler.postDelayed(this, refreshTimeInMillis);
        }
    };

    private Handler tokenWatchdogHandler = new Handler(Looper.getMainLooper());


    private <S> S createTokenService(Class<S> serviceClass, final String authToken) {

        if (!TextUtils.isEmpty(authToken)) {
            httpClientBuilder = new OkHttpClient.Builder();
            ServiceAuthenticationInterceptor serviceAuthenticationInterceptor =
                    new ServiceAuthenticationInterceptor(authToken);

            if (!httpClientBuilder.interceptors().contains(serviceAuthenticationInterceptor)) {
                httpClientBuilder.addInterceptor(serviceAuthenticationInterceptor);

                mRetrofitBuilder.client(httpClientBuilder.build());
                mRetrofit = mRetrofitBuilder.build();
            }
        }

        return mRetrofit.create(serviceClass);
    }

    private <S> S createService(Class<S> serviceClass, final String token) {

        if (!TextUtils.isEmpty(token)) {
            ApiAccessAuthenticatorInterceptor apiAccessAuthenticatorInterceptor = new ApiAccessAuthenticatorInterceptor(token);
            httpClientBuilder = new OkHttpClient.Builder();

            if (!httpClientBuilder.interceptors().contains(apiAccessAuthenticatorInterceptor)) {
                httpClientBuilder.addInterceptor(apiAccessAuthenticatorInterceptor);
            }
        }
        httpClientBuilder.connectTimeout(Api.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(Api.READ_TIMEOUT, TimeUnit.SECONDS);

        mRetrofitBuilder.client(httpClientBuilder.build());
        mRetrofit = mRetrofitBuilder.build();

        return mRetrofit.create(serviceClass);
    }

    private IgniteRestClient(String userName, String userPassword, boolean autoRefreshToken) {
        this.autoRefresh = autoRefreshToken;
        mTokenService = createTokenService(TokenService.class, AUTH_REQUEST_TOKEN);
        callAccess = mTokenService.getAccessToken(GRANT_TYPE, userName, userPassword);
        mIgniteService = createService(IgniteAPIService.class, getAccessToken().getAccessToken());
        if (autoRefresh) {
            if (Api.DEBUG) {
                Log.i(TAG, "Scheduling refresh token handler...");
            }
            tokenWatchdogHandler.postDelayed(tokenWatchDog, refreshTimeInMillis);
        }
    }


    private synchronized AccessToken getAccessToken() throws IgniteRestClientException {

        int responseCode;
        try {
            Response<AccessToken> accessTokenResponse = callAccess.execute();
            responseCode = accessTokenResponse.code();
            if (responseCode == ResponseCode.SUCCESS) {
                accessToken = accessTokenResponse.body();
                if (accessToken != null) {
                    accessToken.setResponseCode(responseCode);

                    refreshTimeInMillis = (accessToken.getExpiresIn() * 1000) - 1000L;

                    if (Api.DEBUG) {
                        Log.d(TAG, "AccessToken: \n" + accessToken.getAccessToken());
                        Log.d(TAG, "Token Type: \n" + accessToken.getTokenType());
                        Log.d(TAG, "Refresh Token: \n" + accessToken.getRefreshToken());
                        Log.d(TAG, "Expires In: \n" + accessToken.getExpiresIn());
                        Log.d(TAG, "Scope: \n" + accessToken.getScope());
                        Log.d(TAG, "Response Code: " + accessToken.getResponseCode());
                    }
                }
            } else {
                throwIgniteException("Response Code: " + responseCode + " - " + accessTokenResponse.message());
            }
        } catch (IOException e) {
            Log.e(TAG, "getAccessToken(): " + e);
            throw new IgniteRestClientException(e);
        }

        return accessToken;
    }

    public synchronized void refreshToken() {

        int responseCode;
        AccessToken refreshToken;
        Call<AccessToken> accessTokenCall = mTokenService.getRefreshToken(REFRESH_GRANT_TYPE, accessToken.getRefreshToken());
        try {
            Response<AccessToken> accessTokenResponse = accessTokenCall.execute();
            responseCode = accessTokenResponse.code();

            refreshToken = accessTokenResponse.body();

            refreshTimeInMillis = (refreshToken.getExpiresIn() * 1000) - 1000L;

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
    }

    public AppKey getAppKey() throws IgniteRestClientException {
        AppKey appKeyModel = null;
        int responseCode;
        if (accessToken != null && !TextUtils.isEmpty(accessToken.getAccessToken())) {
            Call<AppKey> appKeyCall = mIgniteService.getAppKey();
            try {
                Response<AppKey> appKeyResponse = appKeyCall.execute();
                responseCode = appKeyResponse.code();

                if (responseCode == ResponseCode.SUCCESS) {
                    appKeyModel = appKeyResponse.body();
                    if (appKeyModel != null) {
                        appKeyModel.setResponseCode(responseCode);
                        if (Api.DEBUG) {
                            Log.d(TAG, "AppKey: \n" + appKeyModel.getAppKey());
                            Log.d(TAG, "Enabled: \n" + appKeyModel.isEnabled());
                            Log.d(TAG, "Link: \n" + appKeyModel.getLinks());
                            Log.d(TAG, "Response Code: " + appKeyModel.getResponseCode());
                        }
                    }
                } else {
                    throwIgniteException("Response Code: " + responseCode + " - " + appKeyResponse.message());
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException on getAppKey() function:  " + e);
                throw new IgniteRestClientException(e);
            }
        } else {
            Log.e(TAG, "Access token is NULL");
            throwIgniteException("Access token is NULL");
        }

        return appKeyModel;
    }

    public CreateRestrictedUser createRestrictedUser(UserCreateCredentials userCredentials) throws IgniteRestClientException {
        CreateRestrictedUser createRestrictedUser = null;
        int responseCode;
        if (userCredentials != null && !TextUtils.isEmpty(userCredentials.getMail())) {
            if (!isSysUserExists(userCredentials.getMail())) {
                Call<CreateRestrictedUser> mCreateRestrictedUserCall = mIgniteService.createRestrictedUser(userCredentials);
                try {
                    if (mCreateRestrictedUserCall != null) {
                        Response<CreateRestrictedUser> mCreateRestrictedUserResponse = mCreateRestrictedUserCall.execute();
                        responseCode = mCreateRestrictedUserResponse.code();

                        if (Api.DEBUG) {
                            Log.i(TAG, "User Response Code:" + responseCode);
                        }
                        if (responseCode == ResponseCode.USER_CREATE_SUCCESS) {
                            createRestrictedUser = mCreateRestrictedUserResponse.body();
                            if (Api.DEBUG) {
                                Log.i(TAG, "RestrictedUSER:" + createRestrictedUser);
                            }
                        } else {
                            throwIgniteException("Response Code: " + responseCode + " - " + mCreateRestrictedUserResponse.message());
                        }
                    } else {
                        throwIgniteException("mCreateRestrictedUserCall is NULL");
                    }

                } catch (IOException e) {
                    Log.e(TAG, "IOException on createRestrictedUser() function: " + e);
                    throw new IgniteRestClientException(e);
                }
            } else {
                throwIgniteException("User is already exists");
            }
        }
        return createRestrictedUser;
    }

    public SysUserInfo getSystemUserInfo() throws IgniteRestClientException {

        SysUserInfo mSysUserInfo = null;
        int responseCode;
        Call<SysUserInfo> mSysUserInfoCall = mIgniteService.getSysUserInfo();
        try {

            if (mSysUserInfoCall != null) {
                Response<SysUserInfo> mSysUserInfoResponse = mSysUserInfoCall.execute();
                responseCode = mSysUserInfoResponse.code();

                if (responseCode == ResponseCode.SUCCESS) {
                    mSysUserInfo = mSysUserInfoResponse.body();
                    if (mSysUserInfo != null) {
                        mSysUserInfo.setResponseCode(responseCode);
                        if (Api.DEBUG) {
                            Log.i(TAG, "Response Code: " + responseCode);
                            Log.i(TAG, "SysUserInfo: \n" + mSysUserInfo);
                        }
                    }
                } else {
                    throwIgniteException("Response Code:" + responseCode + " - " + mSysUserInfoResponse.message());
                }
            }

        } catch (IOException e) {
            Log.e(TAG, "IOException on createRestrictedUser() function: " + e);
            throw new IgniteRestClientException(e);
        }
        return mSysUserInfo;
    }

    public boolean isSysUserExists(String email) throws IgniteRestClientException {

        int responseCode;
        Call<ResponseBody> mUserCall = mIgniteService.isUserExists(email);

        try {

            if (mUserCall != null) {
                Response<ResponseBody> mUserExistsResponse = mUserCall.execute();
                responseCode = mUserExistsResponse.code();

                if (responseCode == ResponseCode.SUCCESS) {
                    return true;
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException on isSysUserExists() function: " + e);
            throw new IgniteRestClientException(e);
        }
        return false;

    }


    public EndUser getEndUser(String email) throws IgniteRestClientException {

        EndUser endUser = null;
        int responseCode;

        if (!TextUtils.isEmpty(email)) {
            Call<EndUser> endUserCall = mIgniteService.getEndUser(email);

            Log.d(TAG, "REQ : " + endUserCall.request().toString());

            if (endUserCall != null) {
                try {

                    Response<EndUser> endUserResponse = endUserCall.execute();
                    responseCode = endUserResponse.code();

                    if (responseCode == ResponseCode.SUCCESS) {
                        endUser = endUserResponse.body();
                        if (Api.DEBUG) {
                            Log.i(TAG, "Response Code: " + responseCode);
                            Log.i(TAG, "EndUsers: \n" + endUser);
                        }
                    } else {
                        throwIgniteException("Response Code:" + responseCode + " - " + endUserResponse.message());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IOException on getEndUser() function :" + e);
                    throw new IgniteRestClientException(e);
                }
            }
        } else {
            throw new IgniteRestClientException(new IgniteRestClientThrowable(ErrorMessages.EMPTY_MAIL));
        }

        return endUser;
    }

    public Device getDeviceInfo() {

        Device device = null;
        int responseCode;
        Call<Device> deviceCall = mIgniteService.getDeviceInfo();

        try {
            if (deviceCall != null) {

                Response<Device> deviceResponse = deviceCall.execute();

                responseCode = deviceResponse.code();

                if (responseCode == ResponseCode.SUCCESS) {
                    device = deviceResponse.body();
                } else {
                    throwIgniteException("Response Code:" + responseCode + " - " + deviceResponse.message());
                }
            } else {
                throwIgniteException(ErrorMessages.NULL_PARAMETER);
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException on getDeviceInfo() function :" + e);
            throw new IgniteRestClientException(e);
        }
        return device;

    }

    public Device getDeviceSummary(String username) {

        Device device = null;

        int responseCode;

        Call<Device> deviceCall = mIgniteService.getDeviceSummary(username);

        Log.i(TAG, "DEVICE CALL : " + deviceCall.request().toString());
        try {
            Response<Device> deviceResponse = deviceCall.execute();

            responseCode = deviceResponse.code();
            Log.i(TAG, "response Code : " + responseCode);

            if (ResponseCode.SUCCESS == responseCode) {
                device = deviceResponse.body();
            }
        } catch (IOException e) {
            Log.e(TAG, "getDeviceSummary() :" + e);
        }

        return device;
    }


    public boolean pushActionMessageToThing(String deviceCode, ActionMessage actionMessage) {

        int responseCode;
        Call<ResponseBody> pushCall = mIgniteService.pushSensorAgentMessage(deviceCode, actionMessage);
        if (pushCall != null) {
            try {
                Response<ResponseBody> pushActionResponse = pushCall.execute();

                responseCode = pushActionResponse.code();

                if (responseCode == ResponseCode.THING_ACTION_MESSAGE_SUCCESS) {

                    if (Api.DEBUG) {
                        Log.i(TAG, "Action message success. Response Code: " + responseCode);
                    }
                    return true;
                } else {
                    Log.i(TAG, "Action message failure. Response Code: " + responseCode);

                }
            } catch (IOException e) {
                Log.e(TAG, "IOException on pushActionMessageToThing() function :" + e);
                throw new IgniteRestClientException(e);
            }
        } else {
            throwIgniteException(ErrorMessages.NULL_PARAMETER);
        }

        return false;

    }

    public String createDromConfiguration(DromConfiguration dromConfiguration) {

        int responseCode;

        Call<ResponseBody> createDrom = mIgniteService.createDromConfiguration(dromConfiguration);

        if (createDrom != null) {
            try {

                Response<ResponseBody> createDromResponse = createDrom.execute();

                responseCode = createDromResponse.code();

                if (ResponseCode.DROM_CREATE_SUCCESS == responseCode) {

                    if (Api.DEBUG) {
                        Log.i(TAG, "DROM Created Successfully !! " + responseCode);
                    }

                    String configurationIdJsonString = createDromResponse.body().string();
                    try {
                        JSONObject object = new JSONObject(configurationIdJsonString);

                        dromConfigId = object.getString("configurationId");
                        if (Api.DEBUG) {
                            Log.i(TAG, "dromConfigId  : \n" + dromConfigId);
                        }

                    } catch (JSONException e) {
                        Log.e(TAG, "JSONException on createDromConfiguration() function :" + e);
                        throw new IgniteRestClientException(e);
                    }
                    return dromConfigId;
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException on createDromConfiguration() function :" + e);
                throw new IgniteRestClientException(e);
            }
        } else {
            throwIgniteException(ErrorMessages.NULL_PARAMETER);
        }

        return dromConfigId;
    }


    public boolean addDromDevice(DromDevice dromDevice) {

        int responseCode;

        Call<ResponseBody> dromResponseCall = mIgniteService.assingDromToDevice(dromDevice);

        try {
            Response<ResponseBody> dromResponse = dromResponseCall.execute();

            responseCode = dromResponse.code();

            if (Api.DEBUG) {
                Log.i(TAG, "DROM Response Code: " + responseCode);
            }

            if (responseCode == ResponseCode.DROM_CREATE_SUCCESS) {

                if (Api.DEBUG) {
                    Log.i(TAG, "DROM Response :\n " + dromResponse.body().string());
                }
                return true;
            }

        } catch (IOException e) {
            Log.e(TAG, "IOException on pushDromToDevice() function :" + e);
            throw new IgniteRestClientException(e);
        }

        return false;
    }

    public boolean pushDromToDevice(String deviceId) {

        int responseCode;

        if (!TextUtils.isEmpty(deviceId)) {

            Call<ResponseBody> deviceDromCall = mIgniteService.pushDromToDevice(deviceId);

            if (deviceDromCall != null) {
                try {
                    Response<ResponseBody> deviceDromResponse = deviceDromCall.execute();

                    responseCode = deviceDromResponse.code();

                    Log.i(TAG, "Response Code DROM PUSH : " + responseCode);
                    if (responseCode == ResponseCode.SUCCESS) {

                        Log.i(TAG, "Response Code DROM PUSH : " + responseCode);

                        Log.i(TAG, "Response Code DROM PUSH : " + deviceDromResponse.message());
                        return true;
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IOException on pushDromToDevice() function :" + e);
                    throw new IgniteRestClientException(e);
                }
            }

        }

        return false;

    }


    public DeviceNodeInventory getDeviceNodeInventory(String deviceId) {

        DeviceNodeInventory deviceNodeInventory = null;
        int responseCode;


        Call<DeviceNodeInventory> mDeviceNodeInventoryCall = mIgniteService.getDeviceNodeInventory(deviceId);


        try {
            Response<DeviceNodeInventory> mDeviceNodeInventoryResponse = mDeviceNodeInventoryCall.execute();

            responseCode = mDeviceNodeInventoryResponse.code();

            if (responseCode == ResponseCode.SUCCESS) {
                deviceNodeInventory = mDeviceNodeInventoryResponse.body();
            }
        } catch (IOException e) {
            Log.e(TAG, "getDeviceNodeInventory: " + e);
        }


        return deviceNodeInventory;
    }


    public LastThingData getLastData(String deviceId, String nodeId, String thingId) {

        LastThingData lastThingData = null;
        int responseCode;

        Call<LastThingData> mLastThingDataCall = mIgniteService.getLastData(deviceId, nodeId, thingId);


        try {
            Response<LastThingData> mLastThingDataResponse = mLastThingDataCall.execute();


            responseCode = mLastThingDataResponse.code();

            if (responseCode == ResponseCode.SUCCESS) {

                lastThingData = mLastThingDataResponse.body();
            }
        } catch (IOException e) {
            Log.e(TAG, "getLastData: " + e);
        }

        return lastThingData;
    }

    public ThingDataHistory getThingDataHistory(String deviceId, String nodeId, String thingId,
                                                long startDate, long endDate, int dataLimit) {


        ThingDataHistory mThingDataHistory = null;
        int responseCode;

        Call<ThingDataHistory> mThingDataHistoryCall = mIgniteService.getThingDataHistory(deviceId,
                nodeId, thingId, startDate, endDate, dataLimit);


        try {
            Response<ThingDataHistory> mThingDataHistoryResponse = mThingDataHistoryCall.execute();
            responseCode = mThingDataHistoryResponse.code();

            if (responseCode == ResponseCode.SUCCESS) {

                mThingDataHistory = mThingDataHistoryResponse.body();
            }
        } catch (IOException e) {
            Log.e(TAG, "getThingDataHistory: " + e);
        }


        return mThingDataHistory;
    }

    public boolean removeCurrentUser(String deviceCode) {

        int responseCode;

        Call<ResponseBody> responseBodyCall = mIgniteService.removeCurrentUser(deviceCode);

        try {
            Response<ResponseBody> responseBodyResponse = responseBodyCall.execute();

            responseCode = responseBodyResponse.code();

            if (responseCode == ResponseCode.SUCCESS || responseCode == ResponseCode.USER_CREATE_SUCCESS) {


                try {
                    JSONObject object = new JSONObject(responseBodyResponse.body().string());

                    Log.i(TAG, "RESPONSE : " + object.toString());
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int deactivateGateway(String deviceCode) {

        int responseCode = -1;

        Call<ResponseBody> responseBodyCall = mIgniteService.deactivateGateway(deviceCode);


        try {
            Response<ResponseBody> responseBodyResponse = responseBodyCall.execute();

            responseCode = responseBodyResponse.code();

            Log.i(TAG, "RESPONSE CODE " + responseCode);

            if (responseCode == ResponseCode.SUCCESS) {

                try {
                    JSONObject object = new JSONObject(responseBodyResponse.body().string());
                    Log.i(TAG, "RESPONSE JSON " + object.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseCode;

    }

    public static class IgniteRestClientBuilder {

        private String builderUsername = null;
        private String builderPassword = null;
        private boolean builderAutoRefresh = false;


        protected IgniteRestClientBuilder() {
        }


        public IgniteRestClientBuilder userName(String usrName) {
            this.builderUsername = usrName;
            return this;
        }

        public IgniteRestClientBuilder password(String password) {
            this.builderPassword = password;
            return this;
        }


        public IgniteRestClientBuilder autoRefreshToken(boolean autoRefresh) {
            this.builderAutoRefresh = autoRefresh;
            return this;
        }

        public IgniteRestClient build() throws IgniteRestClientException {

            if (!TextUtils.isEmpty(builderUsername) && !TextUtils.isEmpty(builderPassword)) {
                return new IgniteRestClient(builderUsername, builderPassword, builderAutoRefresh);
            }

            throw new IgniteRestClientException(new IgniteRestClientThrowable(ErrorMessages.NULL_PARAMETER));
        }

    }

    private void throwIgniteException(String msg) {
        throw new IgniteRestClientException(new IgniteRestClientThrowable(msg));
    }
}
