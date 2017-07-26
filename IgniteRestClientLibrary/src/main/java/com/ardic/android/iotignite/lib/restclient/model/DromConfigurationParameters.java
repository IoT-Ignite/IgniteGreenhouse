package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by yavuz.erzurumlu on 7/17/17.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * configuration :"{

 "appKey":"2bb69ddce24f4021a1c6b77f1ab9302c",

 "activationCode":"044442",

 "autoLogin":true,

 "profileName":"Default",

 "policyName":"Default",

 "checkProvision":false,"

 empoweredMode":false,

 "userConfigurationMode":"none"

 }"
 */
public class DromConfigurationParameters {

    private static final String TAG = DromConfigurationParameters.class.getSimpleName();

    private String appKey;

    private String activationCode;

    private boolean autoLogin = true;

    private String profileName = "Default";

    private String policyName = "Default";

    private boolean checkProvision = false;

    private boolean empoweredMode = false;

    private String userConfigurationMode ="none";

    public DromConfigurationParameters(String appKey, String activationCode, boolean autoLogin,
                                       String profileName, String policyName, boolean checkProvision,
                                       boolean empoweredMode, String userConfigurationMode) {
        this.appKey = appKey;
        this.activationCode = activationCode;
        this.autoLogin = autoLogin;
        this.profileName = profileName;
        this.policyName = policyName;
        this.checkProvision = checkProvision;
        this.empoweredMode = empoweredMode;
        this.userConfigurationMode = userConfigurationMode;
        generateJson();
    }

    private JSONObject configJson = new JSONObject();

    public String getConfigJsonStr(){
        return configJson.toString();
    }

    private void generateJson(){

        try {
            configJson.put("appKey",appKey);
            configJson.put("activationCode",activationCode);
            configJson.put("autoLogin",true);
            configJson.put("profileName",profileName);
            configJson.put("checkProvision",false);
            configJson.put("empoweredMode",false);
            configJson.put("userConfigurationMode",userConfigurationMode);
        } catch (JSONException e) {
            Log.e(TAG,"JsonException on generateJson() function: " +e);
        }
    }


}
