package com.ardic.android.iotignite.greenhouse.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.greenhouse.CustomCardViewClickListener;
import com.ardic.android.iotignite.greenhouse.R;
import com.ardic.android.iotignite.greenhouse.RecyclerSensorAdapter;
import com.ardic.android.iotignite.greenhouse.SensorViewModel;
import com.ardic.android.iotignite.greenhouse.controllers.DeviceNodeInventoryController;
import com.ardic.android.iotignite.greenhouse.controllers.LastThingDataController;
import com.ardic.android.iotignite.greenhouse.controllers.RegisterSensorController;
import com.ardic.android.iotignite.greenhouse.listeners.DeviceNodeInventoryAsyncTaskListener;
import com.ardic.android.iotignite.greenhouse.listeners.LastThingDataAsyncTaskListener;
import com.ardic.android.iotignite.greenhouse.listeners.RegisterSensorControllerAsyncTaskListener;
import com.ardic.android.iotignite.lib.restclient.model.ActionMessage;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventoryExtras;
import com.ardic.android.iotignite.lib.restclient.model.LastThingData;
import com.ardic.android.iotignite.lib.restclient.model.Node;
import com.ardic.android.iotignite.lib.restclient.model.Thing;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ardic.android.iotignite.greenhouse.Constants.CAMERA_PERMISSION_REQUEST;
import static com.ardic.android.iotignite.greenhouse.Constants.SENSOR_DASHBOARD_UPDATE_TIME;

public class SensorDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, CustomCardViewClickListener,
        DeviceNodeInventoryAsyncTaskListener, LastThingDataAsyncTaskListener, RegisterSensorControllerAsyncTaskListener {

    private static final String TAG = SensorDashboardActivity.class.getSimpleName();
    private FloatingActionButton fabAddSensor;
    private Toolbar toolbar;


    private RecyclerView recyclerView;
    private List<SensorViewModel> sensorList;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private LinearLayoutManager layoutManager;
    private RecyclerSensorAdapter recyclerSensorAdapter;
    private SwipeRefreshLayout sensorSwipeRefreshLayout;

    private String deviceLabel;
    private String deviceId;
    private String deviceCode;
    private DeviceNodeInventoryController mDeviceNodeInventoryController;
    private String registerThingId;

    private Dialog mSensorDialog;
    private AVLoadingIndicatorView loadingIndicator;
    private ImageView mNoSensorImageView;


    private int sensorAddTryCount = 0;

    private Handler sensorAddHandler = new Handler();

    private Runnable mSensorAddRunnable = new Runnable() {
        @Override
        public void run() {

            if (sensorAddTryCount < Constants.SENSOR_TRY_COUNT) {
                Log.i(TAG, "Waiting Sensor...");
                showLoadingProgress(true);
                sensorAddTryCount++;
                updateDashboard();
                sensorAddHandler.postDelayed(this, 5000L);

            } else {
                Toast.makeText(SensorDashboardActivity.this, "Sensor Registration Failure!!!", Toast.LENGTH_LONG).show();
                showLoadingProgress(false);
            }

        }
    };


    private Handler dynamicUiUpdateHandler = new Handler();

    private Runnable dynamicUiUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            if (sensorList != null && !sensorList.isEmpty()) {
                updateDashboard();
            }
            dynamicUiUpdateHandler.postDelayed(this, SENSOR_DASHBOARD_UPDATE_TIME);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_dashboard);

        getGatewayInfo();
        initUI();
        updateDashboard();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dynamicUiUpdateHandler.removeCallbacks(dynamicUiUpdateRunnable);
        dynamicUiUpdateHandler.postDelayed(dynamicUiUpdateRunnable, SENSOR_DASHBOARD_UPDATE_TIME);

    }

    @Override
    protected void onStop() {
        super.onStop();
        dynamicUiUpdateHandler.removeCallbacks(dynamicUiUpdateRunnable);
    }

    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.app_bar_sensor_dashboard_toolbar);
        setSupportActionBar(toolbar);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        fabAddSensor = (FloatingActionButton) findViewById(R.id.sensor_fab);
        fabAddSensor.setOnClickListener(this);

        drawer = (DrawerLayout) findViewById(R.id.activity_sensor_dashboard_drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.content_sensor_dashboard_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        sensorList = new ArrayList<>();
        recyclerSensorAdapter = new RecyclerSensorAdapter(sensorList, new CustomCardViewClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                Log.i("position", "Position on recycler view:" + position);
                SensorViewModel sensor = sensorList.get(position);
                Toast.makeText(getApplicationContext(), "position:" + " " + position + " " + "Sensor ID:" + sensor.getSensorId(), Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerSensorAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        sensorSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.app_bar_sensor_swipe_refresh_layout);
        sensorSwipeRefreshLayout.setOnRefreshListener(this);

        loadingIndicator = (AVLoadingIndicatorView) findViewById(R.id.progress);

        mNoSensorImageView = (ImageView) findViewById(R.id.no_sensor_image_view);

        setTitle(deviceLabel);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_sensor_dashboard_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gateways) {
            // Handle the camera action
        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_buy_device) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_about_us) {

        }
//        else if (id == R.id.nav_send) {
// TODO : isimleri değiştirip ekliycem 
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_gateway_dashboard_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(fabAddSensor)) {

            Snackbar.make(view, "Scan QR code for register your sensor.", Snackbar.LENGTH_SHORT)
                    .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {

                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);
                            checkCameraPermission();
                        }

                        @Override
                        public void onShown(Snackbar transientBottomBar) {
                            super.onShown(transientBottomBar);
                        }
                    }).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && Constants.Actions.ACTION_SENSOR_QR_CODE_SUCCESS.equals(data.getAction())) {

            String qr = data.getStringExtra(Constants.Extra.EXTRA_SENSOR_QR_CODE);
            Log.i(TAG, "QR Code Received !" + qr);
            Toast.makeText(this, "QR Code Received !" + qr, Toast.LENGTH_LONG).show();

            //Register sensor here
            showSensorDialog(qr);


        }
    }


    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        updateDashboard();
    }

    /**
     * Get new sensor values
     */

    private void updateDashboard() {
        showLoadingProgress(true);
        mDeviceNodeInventoryController = new DeviceNodeInventoryController(this, deviceId, this);
        mDeviceNodeInventoryController.execute();

    }

    @Override
    public void onItemClick(View v, int position) {
        Log.i(TAG, "Position on recycler view:" + position);
        SensorViewModel sensor = sensorList.get(position);
        Toast.makeText(getApplicationContext(), " Position: " + position + " Sensor ID: " + sensor.getSensorId(), Toast.LENGTH_SHORT).show();
    }

    private void getGatewayInfo() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            if (intent.hasExtra(Constants.Extra.EXTRA_DEVICE_ID)) {
                deviceId = intent.getStringExtra(Constants.Extra.EXTRA_DEVICE_ID);
            }

            if (intent.hasExtra(Constants.Extra.EXTRA_DEVICE_CODE)) {
                deviceCode = intent.getStringExtra(Constants.Extra.EXTRA_DEVICE_CODE);
            }
            if (intent.hasExtra(Constants.Extra.EXTRA_GATEWAY_LABEL)) {
                deviceLabel = intent.getStringExtra(Constants.Extra.EXTRA_GATEWAY_LABEL);
            }
        }
    }


    private void showSensorDialog(final String sensorQr) {
        // custom dialog
        mSensorDialog = new Dialog(this);
        mSensorDialog.setContentView(R.layout.sensor_registration_dialog);


        // set the custom dialog components - text, image and button
        final EditText editText = mSensorDialog.findViewById(R.id.sensor_name_edit_text);
        editText.setHint("mySampleThing");

        final Button mDialogBtn = mSensorDialog.findViewById(R.id.sensor_dialog_btn);
        // if button is clicked, close the custom dialog
        mDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerThingId = editText.getText().toString();
                Log.i(TAG, "THING ID: " + registerThingId);

                ActionMessage msg = generateActionMessage(sensorQr, registerThingId);
                new RegisterSensorController(getApplicationContext(), deviceCode, msg, SensorDashboardActivity.this).execute();
                mSensorDialog.dismiss();
            }
        });
        mSensorDialog.setTitle("Please Enter Your ThingID:");
        mSensorDialog.setCancelable(false);


        mSensorDialog.show();
    }

    @Override
    public void onDeviceNodeInventoryTaskComplete(DeviceNodeInventory mDeviceNodeInventory) {

        boolean isNodeContains = false;

        if (mDeviceNodeInventory != null) {

            Log.i(TAG, "Device Inventory:" + mDeviceNodeInventory.toString());
            DeviceNodeInventoryExtras extras = mDeviceNodeInventory.getExtras();


            for (Node n : extras.getNodes()) {
                if (Constants.GREENHOUSE_NODE.equals(n.getNodeId())) {
                    for (Thing t : n.getThings()) {
                        isNodeContains = true;
                        new LastThingDataController(this, deviceId, n.getNodeId(), t.getId(), t.getType(), t.getConnected(), this).execute();

                        if (!TextUtils.isEmpty(registerThingId) && t.getId().equals(registerThingId)) {

                            sensorAddHandler.removeCallbacks(mSensorAddRunnable);
                            sensorAddTryCount = 0;
                            registerThingId = null;
                            final String thing = t.getId();
                            final String type = t.getType();
                            final int connected = t.getConnected();

                            SensorDashboardActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showLoadingProgress(false);
                                    Toast.makeText(SensorDashboardActivity.this, "AWESOME ! Sensor Added Successfully ", Toast.LENGTH_LONG).show();
                                    new LastThingDataController(getApplicationContext(), deviceId,
                                            Constants.GREENHOUSE_NODE, thing, type, connected,
                                            SensorDashboardActivity.this).execute();
                                }
                            });
                        }
                    }
                }
            }
        }

        if (!isNodeContains) {
            setNoSensorImage();
            showLoadingProgress(false);
            recyclerSensorAdapter.notifyDataSetChanged();
            sensorSwipeRefreshLayout.setRefreshing(false);
        }
    }


    private void showLoadingProgress(final boolean state) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (loadingIndicator != null) {
                    Log.i(TAG, "INDICATOR STATE: " + state);
                    if (state) {
                        loadingIndicator.show();
                    } else {
                        loadingIndicator.hide();
                    }
                }
            }
        });
    }

    @Override
    public void onLastThingDataTaskComplete(String nodeId, String thingId, String thingType, int connected, LastThingData data) {

        List<String> lastDataList;
        Date dataDate = null;
        String lastData = null;
        if (data.getData() != null) {
            lastDataList = data.getData().getData();
            if (lastDataList != null && !lastDataList.isEmpty()) {
                lastData = lastDataList.get(0);
                Log.i(TAG, "LAST DATA : " + lastData);
                Log.i(TAG, " TYPE : " + thingType);

                if (Constants.GREENHOUSE_TEMPERATURE_THINGTYPE.equals(thingType)) {
                    lastData += Constants.LAST_DATA_TEMP_PREFIX;
                } else if (Constants.GREENHOUSE_HUMIDITY_THINGTYPE.equals(thingType)) {
                    lastData += Constants.LAST_DATA_HUM_PREFIX;
                }
                dataDate = new Date(data.getData().getCreateDate());
            }
        }

        if (TextUtils.isEmpty(lastData)) {
            lastData = "N/A";
        }

        if (dataDate == null) {
            dataDate = new Date(System.currentTimeMillis());
        }

        if (checkSensorId(thingId)) {
            // update
            updateSensorCardView(thingId, lastData, dataDate, connected);
        } else {
            SensorViewModel mdl = new SensorViewModel(thingId, thingType, nodeId, lastData, dataDate, connected == 1 ? true : false);
            addModelToSensorList(mdl);
        }


        showLoadingProgress(false);
        sensorSwipeRefreshLayout.setRefreshing(false);
    }

    private ActionMessage generateActionMessage(String thingCode, String thingId) {

        JSONObject actionMsg = new JSONObject();
        JSONObject actionJsonObject = new JSONObject();

        JSONArray thingsArray = new JSONArray();

        JSONObject thing = new JSONObject();

        try {
            thing.put(Constants.ActionMessage.THING_CODE, thingCode);
            thing.put(Constants.ActionMessage.THING_ID, thingId);

            thingsArray.put(thing);

            JSONObject nodeJsonObject = new JSONObject();

            nodeJsonObject.put(Constants.ActionMessage.NODE_ID, Constants.GREENHOUSE_NODE);
            nodeJsonObject.put(Constants.ActionMessage.THINGS, thingsArray);

            JSONArray addDeviceJsonArray = new JSONArray();
            addDeviceJsonArray.put(nodeJsonObject);


            actionJsonObject.put(Constants.ActionMessage.MESSAGE_ID, "12345");
            actionJsonObject.put(Constants.ActionMessage.ADD_DEVICE, addDeviceJsonArray);


            actionMsg.put(Constants.ActionMessage.MESSAGE, actionJsonObject.toString());

            Log.i(TAG, "ACTION JSON:\n" + actionJsonObject);
            Log.i(TAG, "FINAL JSON:\n" + actionMsg);
        } catch (JSONException e) {
            Log.e(TAG, "generateActionMessage: " + e);
        }

        ActionMessage msg = new ActionMessage(Constants.Actions.ACTION_MESSAGE_NODE_ID,
                actionMsg.toString(),
                Constants.Actions.ACTION_MESSAGE_THING_ID,
                Constants.Actions.ACTION_MESSAGE_NODE_ID);


        return msg;
    }

    @Override
    public void onRegisterSensorTaskComplete(boolean result) {
        if (result) {
            Log.i(TAG, "Action Message Delivered Successfully");
            sensorAddHandler.postDelayed(mSensorAddRunnable, 5000L);
        } else {
            Log.i(TAG, "Action Message Failure");
            Toast.makeText(SensorDashboardActivity.this, "Sensor Registration Failure!!!", Toast.LENGTH_LONG).show();
        }
    }


    private void setNoSensorImage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (sensorList != null && sensorList.isEmpty()) {
                    Log.i(TAG, "Setting no gw image.");
                    mNoSensorImageView.setVisibility(View.VISIBLE);
                } else {
                    mNoSensorImageView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void addModelToSensorList(SensorViewModel mdl) {
        sensorList.add(mdl);
        setNoSensorImage();
        recyclerSensorAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startQRActivity();
                } else {
                    Toast.makeText(this, "Camera permission required for read QR CODE!!!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    private void checkCameraPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(SensorDashboardActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST
            );

        } else {
            startQRActivity();
        }
    }

    private void startQRActivity() {
        Intent intent = new Intent(SensorDashboardActivity.this, QRScanActivity.class);
        intent.setAction(Constants.Actions.ACTION_SENSOR_QR_CODE);
        startActivityForResult(intent, Constants.READ_QR_CODE);
    }

    @Override
    protected void onDestroy() {
        if (drawer != null) {
            drawer.removeDrawerListener(toggle);
        }
        super.onDestroy();
    }


    private void updateSensorCardView(String thingId, String lastData, Date date, int connected) {
        int sensorIndex = getSensorViewModelIndexById(thingId);
        if (sensorIndex != -1) {
            sensorList.get(sensorIndex).setSensorValue(lastData);
            sensorList.get(sensorIndex).setSensorLastSyncDateString(date);
            sensorList.get(sensorIndex).setSensorOnline(connected == 1 ? true : false);
            recyclerSensorAdapter.notifyDataSetChanged();
        }
    }

    private int getSensorViewModelIndexById(String sensorId) {
        for (SensorViewModel mdl : sensorList) {
            if (mdl.getSensorId().equals(sensorId)) {
                return sensorList.indexOf(mdl);
            }
        }
        return -1;
    }

    private boolean checkSensorId(String sensorId) {
        for (SensorViewModel mdl : sensorList) {
            if (mdl.getSensorId().equals(sensorId)) {
                return true;
            }
        }

        return false;
    }
}

