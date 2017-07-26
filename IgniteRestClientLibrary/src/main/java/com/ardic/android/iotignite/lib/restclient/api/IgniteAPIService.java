package com.ardic.android.iotignite.lib.restclient.api;

import com.ardic.android.iotignite.lib.restclient.model.ActionMessage;
import com.ardic.android.iotignite.lib.restclient.model.AppKey;
import com.ardic.android.iotignite.lib.restclient.model.CreateRestrictedUser;
import com.ardic.android.iotignite.lib.restclient.model.Device;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;
import com.ardic.android.iotignite.lib.restclient.model.DromConfiguration;
import com.ardic.android.iotignite.lib.restclient.model.DromDevice;
import com.ardic.android.iotignite.lib.restclient.model.EndUser;
import com.ardic.android.iotignite.lib.restclient.model.LastThingData;
import com.ardic.android.iotignite.lib.restclient.model.SysUserInfo;
import com.ardic.android.iotignite.lib.restclient.model.ThingDataHistory;
import com.ardic.android.iotignite.lib.restclient.model.UserCreateCredentials;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yavuz.erzurumlu on 7/10/17.
 */

public interface IgniteAPIService {

    /**
     * Get tenants appkey for trusted messaging.
     *
     * @return
     */
    @GET("/api/v3/settings/messager/appkey")
    Call<AppKey> getAppKey();

    @POST("/api/v3/public/create-restricted-user")
    Call<CreateRestrictedUser> createRestrictedUser(@Body UserCreateCredentials userCredentials);

    @GET("/api/v3/sysuser/auditor")
    Call<SysUserInfo> getSysUserInfo();

    @GET("/api/v3/sysuser")
    Call<ResponseBody> isUserExists(@Query(value = "user", encoded = true) String email);

    // create drom conf for tenant.
    @POST("/api/v3/dromconfiguration/add")
    Call<ResponseBody> createDromConfiguration(@Body DromConfiguration dromConf);

    @POST("/api/v3/dromdeviceconfiguration/add")
    Call<ResponseBody> assingDromToDevice(@Body DromDevice dromDevice);

    @POST("/api/v3/dromdeviceconfiguration/push/{deviceId}")
    Call<ResponseBody> pushDromToDevice(@Path(value = "deviceId") String deviceId);

    @POST("/api/v3/device/{code}/control/sensor-agent-message")
    Call<ResponseBody> pushSensorAgentMessage(@Path(value = "code", encoded = true) String code, @Body ActionMessage actionMessage);

    @GET("/api/v3/enduser")
    Call<EndUser> getEndUser(@Query(value = "mail", encoded = true) String email);

    @GET("/api/v3/device")
    Call<Device> getDeviceInfo();

    @GET("/api/v3/device/summary")
    Call<Device> getDeviceSummary(@Query(value = "user", encoded = true) String user);

    @GET("/api/v3/device/{deviceId}/device-node-inventory")
    Call<DeviceNodeInventory> getDeviceNodeInventory(@Path(value = "deviceId") String deviceId);

    @GET("/api/v3/device/{deviceId}/sensor-data")
    Call<LastThingData> getLastData(@Path(value = "deviceId") String deviceId,
                                    @Query(value = "nodeId", encoded = true) String nodeId,
                                    @Query(value = "sensorId", encoded = true) String sensorId);

    @GET("/api/v3/device/{deviceId}/sensor-data-history")
    Call<ThingDataHistory> getThingDataHistory(@Path(value = "deviceId") String deviceId,
                                               @Query(value = "nodeId", encoded = true) String nodeId,
                                               @Query(value = "sensorId", encoded = true) String sensorId,
                                               @Query(value = "startDate", encoded = true) long startDate,
                                               @Query(value = "endDate", encoded = true) long endDate,
                                               @Query(value = "pageSize", encoded = true) int dataLimit);

    @PUT("api/v3/device/{code}/control/removecurrentuser")
    Call<ResponseBody> removeCurrentUser(@Path(value = "code") String deviceCode);

    @POST("api/v3/device/{code}/control/logoff")
    Call<ResponseBody> deactivateGateway(@Path(value = "code") String deviceCode);
}


