package com.ardic.android.iotignite.greenhouse.listeners;

import com.ardic.android.iotignite.lib.restclient.model.LastThingData;

/**
 * Created by yavuz.erzurumlu on 7/31/17.
 */

public interface LastThingDataAsyncTaskListener {
    void onLastThingDataTaskComplete(String nodeId, String thingId, String thingType, int connected, LastThingData data);
}
