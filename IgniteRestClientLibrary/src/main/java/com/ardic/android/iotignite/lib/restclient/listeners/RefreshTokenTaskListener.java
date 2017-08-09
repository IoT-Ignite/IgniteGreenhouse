package com.ardic.android.iotignite.lib.restclient.listeners;

import com.ardic.android.iotignite.lib.restclient.model.AccessToken;

/**
 * Created by yavuz.erzurumlu on 8/9/17.
 */

public interface RefreshTokenTaskListener {
    void onTokenRefreshed(AccessToken token);
}
