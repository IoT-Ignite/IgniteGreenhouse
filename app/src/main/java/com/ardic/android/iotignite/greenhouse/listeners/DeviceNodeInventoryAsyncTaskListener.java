package com.ardic.android.iotignite.greenhouse.listeners;

import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;

/**
 * Created by codemania on 7/30/17.
 */

public interface DeviceNodeInventoryAsyncTaskListener {
    void onDeviceNodeInventoryTaskComplete(DeviceNodeInventory mDeviceNodeInventory);
}
