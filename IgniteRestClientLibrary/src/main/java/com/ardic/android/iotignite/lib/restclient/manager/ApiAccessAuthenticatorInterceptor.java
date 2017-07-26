package com.ardic.android.iotignite.lib.restclient.manager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HakkiYavuz on 16.7.2017.
 */

public class ApiAccessAuthenticatorInterceptor implements Interceptor {
    private String token;


    public ApiAccessAuthenticatorInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
