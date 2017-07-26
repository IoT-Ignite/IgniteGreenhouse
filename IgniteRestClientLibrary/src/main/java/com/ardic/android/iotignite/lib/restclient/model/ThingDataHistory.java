package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by codemania on 7/24/17.
 */

public class ThingDataHistory {

    @SerializedName("list")
    private List<ThingData> list;

    @SerializedName("count")
    private int count;

    @SerializedName("lastId")
    private String lastId;

    public List<ThingData> getList() {
        return list;
    }

    public void setList(List<ThingData> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    @Override
    public String toString() {
        return "ThingDataHistory{" +
                "list=" + list +
                ", count=" + count +
                ", lastId='" + lastId + '\'' +
                '}';
    }
}
