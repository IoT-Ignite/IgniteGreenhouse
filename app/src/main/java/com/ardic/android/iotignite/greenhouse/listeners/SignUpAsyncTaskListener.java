package com.ardic.android.iotignite.greenhouse.listeners;

import com.ardic.android.iotignite.lib.restclient.model.CreateRestrictedUser;

/**
 * Created by codemania on 7/30/17.
 */

public interface SignUpAsyncTaskListener {
    void onSignUpTaskComplete(CreateRestrictedUser user);
}
