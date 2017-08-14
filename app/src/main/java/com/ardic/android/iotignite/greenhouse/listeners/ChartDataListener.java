package com.ardic.android.iotignite.greenhouse.listeners;

import com.ardic.android.iotignite.lib.restclient.model.ThingData;

/**
 * Created by yavuz.erzurumlu on 8/10/17.
 */

public interface ChartDataListener {
    void onNewData(String thingId, ThingData data);
}
