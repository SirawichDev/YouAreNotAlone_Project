package com.Sirawit.Projectx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.Sirawit.Projectx.ShakeEventManager.ShakeListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pubnub.api.Pubnub;

public class GMapsShareLocationActivity extends ActionBarActivity implements
        OnMapReadyCallback, ConnectionCallbacks, LocationListener,ShakeListener {

    // =============================================================================================
    // Properties
    // =============================================================================================

    private static final String TAG = "Tracker - GMaps Share";
    private boolean mRequestingLocationUpdates = true;
    private MenuItem mShareButton;

    // Google API - Locations
    private GoogleApiClient mGoogleApiClient;

    // Google Maps
    private GoogleMap mGoogleMap;
    private PolylineOptions mPolylineOptions;
    private LatLng mLatLng;
    private ShakeEventManager sd;

    // PubNub
    private Pubnub mPubnub;
     String channelName;
    String num = "0911670676";
    private String msg;
     String Phonenumber;

    // =============================================================================================
    // Activity Life Cycle
    // =============================================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmaps_view);
        sd = new ShakeEventManager(this);
        sd.setListener(this);
        sd.init(this);
        // Get Channel Name
        Intent intent = getIntent();
        Phonenumber = intent.getStringExtra("Phone");
        channelName = intent.getExtras().getString("channel");
        Log.d(TAG, "Passed Channel"+ channelName);
        // Start Google Client
        this.buildGoogleApiClient();

        // Set up View: Map & Action Bar
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Start PubNub
        mPubnub = PubNubManager.startPubnub();

    }



    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this).addApi(LocationServices.API)
                .build();
    }

    // =============================================================================================
    // Map CallBacks
    // =============================================================================================

    @Override
    public void onMapReady(GoogleMap map) {
        mGoogleMap = map;
        mGoogleMap.setMyLocationEnabled(true);
        startSharingLocation();
        Log.d(TAG, "Map Ready");
    }

    // =============================================================================================
    // Google Location API CallBacks
    // =============================================================================================

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "Connected to Google API for Location Management");
        if (mRequestingLocationUpdates) {
            LocationRequest mLocationRequest = createLocationRequest();
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
            initializePolyline();
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Connection to Google API suspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Location Detected");
        mLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        // Broadcast information on PubNub Channel
        PubNubManager.broadcastLocation(mPubnub, channelName, location.getLatitude(),
                       location.getLongitude(), location.getAltitude());

        // Update Map
        updateCamera();
        updatePolyline();
    }

    private LocationRequest createLocationRequest() {
        Log.d(TAG, "Building request");
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    // =============================================================================================
    // Button CallBacks
    // =============================================================================================


    public void startSharingLocation() {
        Log.d(TAG, "Starting Location Updates");
        mGoogleApiClient.connect();
    }

    public void stopSharingLocation() {
        Log.d(TAG, "Stop Location Updates & Disconect to Google API");
        stopLocationUpdates();
        mGoogleApiClient.disconnect();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }




    // =============================================================================================
    // Map Editing Methods
    // =============================================================================================

    private void initializePolyline() {
        mGoogleMap.clear();
        mPolylineOptions = new PolylineOptions();
        mPolylineOptions.color(Color.BLUE).width(10);
        mGoogleMap.addPolyline(mPolylineOptions);
    }

    private void updatePolyline() {
        mPolylineOptions.add(mLatLng);
        mGoogleMap.clear();
        mGoogleMap.addPolyline(mPolylineOptions);
    }

    private void updateCamera() {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 16));
    }
    public void onShake() {
        try {

            msg = "https://www.google.com/maps/place"+mLatLng;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(Phonenumber, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),
                    ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        sd.register();
    }

}
