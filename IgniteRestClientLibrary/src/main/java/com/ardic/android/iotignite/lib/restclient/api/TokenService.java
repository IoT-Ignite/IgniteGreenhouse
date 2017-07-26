package com.ardic.android.iotignite.lib.restclient.api;

import com.ardic.android.iotignite.lib.restclient.model.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yavuz.erzurumlu on 07/07/2017.
 */

public interface TokenService {

    @FormUrlEncoded
    @POST("/api/v3/login/oauth")
    Call<AccessToken> getAccessToken(
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("/api/v3/login/oauth")
    Call<AccessToken> getRefreshToken(
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken);
}
