package com.ardic.android.iotignite.greenhouse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.greenhouse.R;
import com.ardic.android.iotignite.greenhouse.controllers.DROMController;
import com.ardic.android.iotignite.greenhouse.controllers.DeviceController;

import java.util.concurrent.ExecutionException;

public class GatewayDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = GatewayDashboardActivity.class.getSimpleName();
    private FloatingActionButton fabAddGateway;
    private String activeUser;
    private String activeUserPassword;
    private DROMController mDromController;
    private DeviceController mDeviceController;

    private CardView cardViewGateway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gateway_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getLoginInfo();

        fabAddGateway = (FloatingActionButton) findViewById(R.id.fab);
        fabAddGateway.setOnClickListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getDeviceInfo();


        cardViewGateway = (CardView) findViewById(R.id.card_view);
        cardViewGateway.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gateway_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

        /**
         * Start QR Scan Activity here.
         */
        if (view.equals(fabAddGateway)) {

            Snackbar.make(view, "Scan your gateway", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            //TODO : Check camera permission here. - RunTime and Manifest.
            Intent intent = new Intent(GatewayDashboardActivity.this, QRScanActivity.class);
            startActivityForResult(intent, Constants.READ_QR_CODE);

        } else if (view instanceof CardView) {
            //  startActivity(new Intent(GatewayDashboardActivity.this, SensorDashboardActivity.class));


            Log.i(TAG, "MUHAHA");

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            String qr = data.getStringExtra(Constants.Extra.EXTRA_GW_QR_CODE);
            Log.i(TAG, "QR Code Received !" + qr);
            Toast.makeText(this, "QR Code Received !" + qr, Toast.LENGTH_LONG).show();

            mDromController = new DROMController(this, activeUser, activeUserPassword, qr);

            mDromController.execute();

            try {
                if (mDromController.get()) {

                    GatewayDashboardActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(GatewayDashboardActivity.this, "AWESOME ! DEVICE LICENCED SUCCESSFULLY. ", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } catch (InterruptedException e) {
                Log.e(TAG, "InterruptedException on onActivityResult function:" + e);
            } catch (ExecutionException e) {
                Log.e(TAG, "ExecutionException on onActivityResult function:" + e);
            }

        }
    }

    private void getLoginInfo() {

        Intent mIntent = getIntent();

        if (mIntent != null && mIntent.hasExtra(Constants.Extra.EXTRA_USERNAME)) {
            activeUser = mIntent.getStringExtra(Constants.Extra.EXTRA_USERNAME);
        }

        if (mIntent != null && mIntent.hasExtra(Constants.Extra.EXTRA_PASSWORD)) {
            activeUserPassword = mIntent.getStringExtra(Constants.Extra.EXTRA_PASSWORD);
        }
    }

    private void getDeviceInfo() {
        mDeviceController = new DeviceController(this, activeUser, activeUserPassword);
        mDeviceController.execute();
    }
}
