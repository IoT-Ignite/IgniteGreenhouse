package com.ardic.android.iotignite.greenhouse.listeners;

import com.ardic.android.iotignite.lib.restclient.model.Device;

/**
 * Created by codemania on 7/30/17.
 */

public interface DeviceAsyncTaskListener {
    void onDeviceTaskComplete(Device mDevice);
}
