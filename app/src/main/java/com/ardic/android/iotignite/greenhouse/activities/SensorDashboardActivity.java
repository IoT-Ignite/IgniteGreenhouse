package com.ardic.android.iotignite.greenhouse.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = SensorDashboardActivity.class.getSimpleName();
    private FloatingActionButton fabAddGateway;
    private Toolbar toolbar;


    private RecyclerView recycler_view;
    private List<SensorViewModel> sensorList;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private LinearLayoutManager layoutManager;
    private RecyclerSensorAdapter adapter_items;

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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        fabAddGateway = (FloatingActionButton) findViewById(R.id.fab);
        fabAddGateway.setOnClickListener(this);

        drawer = (DrawerLayout) findViewById(R.id.activity_sensor_dashboard_drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recycler_view = (RecyclerView) findViewById(R.id.content_sensor_dashboard_recycler_view);
        recycler_view.setLayoutManager(layoutManager);

        sensorList = new ArrayList<>();
        adapter_items = new RecyclerSensorAdapter(sensorList, new CustomCardViewClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                Log.i("position", "Position on recycler view:" + position);
                SensorViewModel sensor = sensorList.get(position);
                Toast.makeText(getApplicationContext(), "position:" + " " + position + " " + "Sensor ID:" + sensor.getSensorId(), Toast.LENGTH_SHORT).show();

            }
        });

        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(adapter_items);
        recycler_view.setItemAnimator(new DefaultItemAnimator());

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
        if (view.equals(fabAddGateway)) {
            Snackbar.make(view, "Scan QR code for register your sensor.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}

