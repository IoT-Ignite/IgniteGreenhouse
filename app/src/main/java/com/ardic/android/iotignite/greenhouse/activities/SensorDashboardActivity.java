package com.ardic.android.iotignite.greenhouse.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.greenhouse.CustomCardViewClickListener;
import com.ardic.android.iotignite.greenhouse.R;
import com.ardic.android.iotignite.greenhouse.RecyclerSensorAdapter;
import com.ardic.android.iotignite.greenhouse.SensorViewModel;
import com.ardic.android.iotignite.greenhouse.controllers.DeviceNodeInventoryController;
import com.ardic.android.iotignite.greenhouse.controllers.LastThingDataController;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventory;
import com.ardic.android.iotignite.lib.restclient.model.DeviceNodeInventoryExtras;
import com.ardic.android.iotignite.lib.restclient.model.LastThingData;
import com.ardic.android.iotignite.lib.restclient.model.Node;
import com.ardic.android.iotignite.lib.restclient.model.Thing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SensorDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, CustomCardViewClickListener {

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

    private String deviceId;
    private DeviceNodeInventoryController mDeviceNodeInventoryController;
    private String registerThingId;

    private Dialog mSensorDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_dashboard);


        getGatewayInfo();
        initUI();

        updateDashboard();
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
        drawer.setDrawerListener(toggle);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

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

                            Intent intent = new Intent(SensorDashboardActivity.this, QRScanActivity.class);
                            intent.setAction(Constants.Actions.ACTION_SENSOR_QR_CODE);
                            startActivityForResult(intent, Constants.READ_QR_CODE);
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
        // TODO : GET SENSOR INFO HERE!!!!!!

        DeviceNodeInventory mDeviceNodeInventory = null;
        mDeviceNodeInventoryController = new DeviceNodeInventoryController(this, deviceId);
        mDeviceNodeInventoryController.execute();

        try {
            mDeviceNodeInventory = mDeviceNodeInventoryController.get(Constants.ASYNC_GET_TIMEOUT, TimeUnit.MILLISECONDS);

            if (mDeviceNodeInventory != null) {

            }
        } catch (InterruptedException e) {
            Log.e(TAG, "updateDashboard:" + e);
        } catch (ExecutionException e) {
            Log.e(TAG, "updateDashboard:" + e);
        } catch (TimeoutException e) {
            Log.e(TAG, "updateDashboard:" + e);
        }


        if (mDeviceNodeInventory != null) {

            Log.i(TAG, "Device Inventory:" + mDeviceNodeInventory.toString());
            sensorList.clear();

            DeviceNodeInventoryExtras extras = mDeviceNodeInventory.getExtras();

            for (Node n : extras.getNodes()) {
                for (Thing t : n.getThings()) {
                    LastThingData data = null;
                    try {
                        data = new LastThingDataController(this, deviceId, n.getNodeId(), t.getId()).execute().get(Constants.ASYNC_GET_TIMEOUT, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "updateDashboard: " + e);
                    } catch (ExecutionException e) {
                        Log.e(TAG, "updateDashboard: " + e);
                    } catch (TimeoutException e) {
                        Log.e(TAG, "updateDashboard: " + e);
                    }

                    String lastData = null;
                    if (data.getData() != null) {
                        //  lastData = data.getData().getData().get(0);
                    }

                    if (TextUtils.isEmpty(lastData)) {
                        lastData = "N/A";
                    }

                    SensorViewModel mdl = new SensorViewModel(t.getId(), t.getType(), n.getNodeId(), lastData, new Date(System.currentTimeMillis()), t.getConnected() == 1 ? true : false);
                    sensorList.add(mdl);
                }
            }

            recyclerSensorAdapter.notifyDataSetChanged();
            sensorSwipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onItemClick(View v, int position) {
        Log.i(TAG, "Position on recycler view:" + position);
        SensorViewModel sensor = sensorList.get(position);
        Toast.makeText(getApplicationContext(), " Position: " + position + " Sensor ID: " + sensor.getSensorId(), Toast.LENGTH_SHORT).show();
    }

    private void getGatewayInfo() {
        if (getIntent() != null && getIntent().hasExtra(Constants.Extra.EXTRA_DEVICE_ID)) {
            Intent intent = getIntent();
            deviceId = intent.getStringExtra(Constants.Extra.EXTRA_DEVICE_ID);
        }
    }


    private void showSensorDialog(String sensorQr) {
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

                // TODO : register sensor here.
                mSensorDialog.dismiss();
            }
        });
        mSensorDialog.setTitle("Please Enter Your ThingID:");
        mSensorDialog.setCancelable(false);


        mSensorDialog.show();
    }
}

