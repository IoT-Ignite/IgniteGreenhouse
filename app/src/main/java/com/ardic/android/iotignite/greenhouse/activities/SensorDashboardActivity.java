package com.ardic.android.iotignite.greenhouse.activities;

import android.os.Bundle;
import android.os.Handler;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ardic.android.iotignite.greenhouse.CustomCardViewClickListener;
import com.ardic.android.iotignite.greenhouse.R;
import com.ardic.android.iotignite.greenhouse.RecyclerSensorAdapter;
import com.ardic.android.iotignite.greenhouse.SensorViewModel;
import com.ardic.android.iotignite.greenhouse.controllers.DROMController;
import com.ardic.android.iotignite.greenhouse.controllers.DeviceController;

import java.util.ArrayList;
import java.util.List;

public class SensorDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

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

    private String activeUser;
    private String activeUserPassword;
    private DROMController mDromController;
    private DeviceController mDeviceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_dashboard);

        initUI();

        //use "add" function onActivityResult method with result values.
        sensorList.add(new SensorViewModel("Temperature", "25 C", true));
        sensorList.add(new SensorViewModel("Humidity", "42%", false));

    }

    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.app_bar_sensor_dashboard_toolbar);
        setSupportActionBar(toolbar);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        fabAddSensor = (FloatingActionButton) findViewById(R.id.fab);
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
            Snackbar.make(view, "Scan QR code for register your sensor.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        //delete this add methods. its just for trying.
        sensorList.add(new SensorViewModel("Temperature", "30'C", false));
        sensorList.add(new SensorViewModel("Humidity", "25%", true));
        //

        refreshContent();
    }


    private void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sensorList = getNewSensorList();
                //      recyclerSensorAdapter = new RecyclerSensorAdapter(sensorList, SensorDashboardActivity.this);
                recyclerView.setAdapter(recyclerSensorAdapter);
                sensorSwipeRefreshLayout.setRefreshing(false);
            }
        }, 10);
    }

    /**
     * Get new gateway values
     */
    private List<SensorViewModel> getNewSensorList() {
        List<SensorViewModel> newSensorList = new ArrayList<>();

        // do something here for get new data with rest call,
        //newGatewayList.add(new GatewayViewModel("gateway label here", "gateway id", "gateway status, online:true, offline:false");
        //like this:
        //newGatewayList.add(new GatewayViewModel("My Potatoes", "Raspberry PI 121SDHB", false));

        //you can delete this "for loop" it is just for trying.
        for (int i = 1; i < sensorList.size(); i++) {
            newSensorList.add(sensorList.get(i));
        }

        return newSensorList;
    }

}

