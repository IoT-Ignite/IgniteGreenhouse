package com.ardic.android.iotignite.lib.restclient;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClient;
import com.ardic.android.iotignite.lib.restclient.manager.IgniteRestClientManager;
import com.ardic.android.iotignite.lib.restclient.model.ActionMessage;
import com.ardic.android.iotignite.lib.restclient.model.AppKey;
import com.ardic.android.iotignite.lib.restclient.model.CreateRestrictedUser;
import com.ardic.android.iotignite.lib.restclient.model.Device;
import com.ardic.android.iotignite.lib.restclient.model.DeviceContent;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;
import com.ardic.android.iotignite.lib.restclient.model.DromConfiguration;
import com.ardic.android.iotignite.lib.restclient.model.DromConfigurationParameters;
import com.ardic.android.iotignite.lib.restclient.model.DromDevice;
import com.ardic.android.iotignite.lib.restclient.model.EndUser;
import com.ardic.android.iotignite.lib.restclient.model.EndUserContent;
import com.ardic.android.iotignite.lib.restclient.model.LastThingData;
import com.ardic.android.iotignite.lib.restclient.model.SysUserInfo;
import com.ardic.android.iotignite.lib.restclient.model.ThingDataHistory;
import com.ardic.android.iotignite.lib.restclient.model.UserCreateCredentials;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IgniteAPITest {

    private static final String TAG = "TEST";


    //  private static final String USERNAME = "greenhouse@iot-ignite.com";
    // private static final String PASSWORD = "gr33nhous3";
    private static final String APP_KEY = "2bb69ddce24f4021a1c6b77f1ab9302c";
    private static final String TEST_DEVICE_ID = "b8:27:eb:df:c6:11@iotigniteagent";
    private static final String TEST_NODE_ID = "GreenHouse1";
    private static final String TEST_THING_ID = "Temperature";
    // private static final String TEST_TENANT_DOMAIN = "ignite.com_greenhouse_iot-ignite.com";
    // private static final String TEST_DROM_CONFIG_ID = "4d6b54d5-43ec-4bc3-a611-ffaab67d02fe";


    // TEST

    private static final String TEST_DEVICE = "10:68:3f:7a:db:1a";
    private static final String TEST_TENANT_DOMAIN = "modiverse.com_ar.dicc.testt_gmail.com";
    private static final String TEST_DROM_CONFIG_ID = "6bd45906-9aef-4b46-8d39-eaf66dd8d768";
    private static final String USERNAME = "ar.dicc.testt@gmail.com";
    private static final String PASSWORD = "258456159";

    private IgniteRestClient mTestClient;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.ardic.android.iotignite.lib.restclient.test", appContext.getPackageName());
    }

    @Test
    public void authentication() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        IgniteRestClientManager manager = IgniteRestClientManager.getInstance(appContext);
        mTestClient = manager.createClient(USERNAME, PASSWORD, true);

    }


    @Test
    public void refreshTokenTest() {

        authentication();
        mTestClient.refreshToken();
    }

    @Test
    public void appKeyTest() {
        authentication();
        String appKey = mTestClient.getAppKey().getAppKey();
        Log.i(TAG,"appKey : " + appKey);
    }


    @Test
    public void userCreateTest() {

        Log.d(TAG, "UserCreate Test...");
        authentication();

        UserCreateCredentials testUser = new UserCreateCredentials(mTestClient.getAppKey().getAppKey(), "IgniteGreenhouse", "Ignite", "Greenhouse", "greenhouse2017@testuser.com", "12345", "Default");
        CreateRestrictedUser user = mTestClient.createRestrictedUser(testUser);

        if (user != null) {
            Log.i(TAG, "Result :" + user.getResponseCode());

            Log.i(TAG, "USER :\n" + user.toString());

        } else {
            Log.e(TAG, "User is NULL");
        }

    }

    @Test
    public void getSysUserInfoTest() {

        Log.d(TAG, "getSysUserInfoTest...");

        authentication();

        SysUserInfo mSysUserInfo = mTestClient.getSystemUserInfo();

        Log.i(TAG, "Result : " + mSysUserInfo.toString());
    }


    @Test
    public void isUserExistsTest() {
        authentication();
        if (mTestClient.isSysUserExists("yavuz26@testuser.com")) {
            Log.i(TAG, "USER EXISTS");
        } else {
            Log.i(TAG, "USER NOT FOUND");
        }
    }

    @Test
    public void getEndUserTest() {
        authentication();

        //  EndUser e = mTestClient.getEndUser("greenhouse2017@testuser.com");

        EndUser e = mTestClient.getEndUser("dorukgeziciii@gmail.com");


        Log.i(TAG, "EndUser Size : " + e.getContents().size());

        for (EndUserContent email : e.getContents()) {
            Log.i(TAG, "User : " + email);
        }
    }

    @Test
    public void getDeviceInfo() {

        authentication();

        Device d = mTestClient.getDeviceInfo();


        Log.i(TAG, "Device Info : \n " + d);
    }

    @Test
    public void getDeviceSummary() {

        authentication();


        String testUserName = "ardicctestt%40gmail.com";
        Device d = mTestClient.getDeviceSummary(testUserName);

        Log.i(TAG, "Device : " + d );
    }


    @Test
    public void sendActionMessageTest() {

        authentication();

        DeviceContent activeDevice = null;

        Device d = mTestClient.getDeviceInfo();


        for (DeviceContent deviceContent : d.getDeviceContents()) {

            if (deviceContent.getDeviceId().equals(TEST_DEVICE_ID)) {
                activeDevice = deviceContent;
                break;
            }
        }


        JSONObject actionMsg = new JSONObject();


        String testMsg = "{\"addDevice\":[{\"nodeId\":\"testNode2\",\"things\":[{\"thingCode\":\"f4030687\",\"thingId\":\"Test Temp2\"},{\"thingCode\":\"10020001\",\"thingId\":\"Test Hum2\"},{\"thingCode\":\"f4030687\",\"thingId\":\"Test Temp3\"}]}]}";

        //  String adasd = " Yavuz Ezurumlu";

        try {
            actionMsg.put("message", testMsg);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Device Code : " + activeDevice.getCode());

        ActionMessage actionMessage = new ActionMessage("Configurator", actionMsg.toString(), "Configurator Thing", "Configurator");


        Log.i(TAG, "Test Message : " + actionMsg.toString());

        mTestClient.pushActionMessageToThing(activeDevice.getCode(), actionMessage);

    }


    @Test
    public void dromAddConfigTest() {


        authentication();


        EndUserContent activeUser = null;


        EndUser endUsers = mTestClient.getEndUser("greenhouse2017@testuser.com");

        for (EndUserContent end : endUsers.getContents()) {

            if (end.getMail().equals("greenhouse2017@testuser.com")) {
                activeUser = end;
                break;
            }
        }
        Log.i(TAG, "Activation Code :" + activeUser.getActivationCode());
        Log.i(TAG, "Mail " + activeUser.getMail());
        DromConfigurationParameters dromConfigParam = new DromConfigurationParameters(APP_KEY, activeUser.getActivationCode()
                , true, "Default", "Default", false, false, "none");

        DromConfiguration dromConfig = new DromConfiguration(dromConfigParam, activeUser.getMail(), mTestClient.getSystemUserInfo().getTenantDomain());

        mTestClient.createDromConfiguration(dromConfig);

    }

    @Test
    public void assignDromDeviceTest() {

        authentication();
        DromDevice dromDevice = new DromDevice(TEST_DROM_CONFIG_ID, TEST_DEVICE_ID, TEST_TENANT_DOMAIN);
        mTestClient.addDromDevice(dromDevice);


    }

    @Test
    public void dromDevicePushTest() {

        authentication();
        // mTestClient.pushDromToDevice(TEST_DEVICE_ID);

        /**
         * Oğuz Test
         */
        mTestClient.pushDromToDevice(TEST_DEVICE);
        mTestClient.pushDromToDevice(TEST_DEVICE);
    }

    @Test
    public void getDeviceNodeInventoryTest() {

        authentication();
        DeviceNodeInventory deviceNodeInventory = mTestClient.getDeviceNodeInventory(TEST_DEVICE_ID);
        Log.e(TAG, "TEST RESULT " + deviceNodeInventory.toString());
    }

    @Test
    public void getLastDataTest() {
        authentication();
        LastThingData mLastThingData = mTestClient.getLastData("b8:27:eb:df:c6:11@iotigniteagent", "GreenHouse1", "Temperature");
        Log.e(TAG, "TEST RESULT " + mLastThingData.getData().getData().toString());

    }


    @Test
    public void getThingDataHistoryTest() {
        authentication();
        ThingDataHistory mThingDataHistory = mTestClient.getThingDataHistory(TEST_DEVICE_ID, TEST_NODE_ID, TEST_THING_ID, 0L, System.currentTimeMillis(), 3);

        Log.e(TAG, "TEST RESULT " + mThingDataHistory.getList().toString());

        Log.e(TAG, "TEST LIST SIZE " + mThingDataHistory.getList().size());


    }

    @Test
    public void removeCurrentUserTest() {


        authentication();

        String deviceCode = "";

        Device devices = mTestClient.getDeviceInfo();

        for (DeviceContent cnt : devices.getDeviceContents()) {
            if (cnt.getDeviceId().equals(TEST_DEVICE)) {
                deviceCode = cnt.getCode();
                break;
            }
        }

        mTestClient.removeCurrentUser(deviceCode);
    }

    @Test
    public void deactivateGatewayTest() {

        authentication();
        String deviceCode = "";
        Device devices = mTestClient.getDeviceInfo();

        for (DeviceContent cnt : devices.getDeviceContents()) {
            Log.i(TAG, "Device CODEs : " + cnt.getCode());
            Log.i(TAG, "Device ID : " + cnt.getDeviceId());
            if (cnt.getDeviceId().equals(TEST_DEVICE)) {
                deviceCode = cnt.getCode();
                Log.i(TAG, "Device CODE : " + deviceCode);
                break;
            }
        }

        mTestClient.deactivateGateway(deviceCode);

    }

}
