package com.ardic.android.iotignite.greenhouse.listeners;

import com.ardic.android.iotignite.lib.restclient.model.ThingDataHistory;

/**
 * Created by yavuz.erzurumlu on 8/9/17.
 */

public interface ThingDataHistoryAsyncTaskListener {
    void onDataHistoryTaskComplete(ThingDataHistory dataHistory);
}
