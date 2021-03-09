package com.harmes.engine.location;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.LocationManager;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LocationEngine {

    private static final String TAG = LocationEngine.class.getSimpleName();
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private LocationRequest locationRequest;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public LocationEngine(){
    }

    public void startEngineWithActivity(Activity activity,int activityCode){
        prepareEngine();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        prepareEngineSettings(activity,activityCode);
    }

    public void startEngineWithContext(Context mCtx){
        prepareEngine();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mCtx);
        prepareEngineSettings(mCtx);

    }

    private void prepareEngine(){
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    private void prepareEngineSettings(Activity activity, int activityCode){
        LocationSettingsRequest request = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task  = client.checkLocationSettings(request);
        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // setting require are satisfied,  start location update
                startLocationUpdate();      //
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ResolvableApiException){
                    ResolvableApiException exception = (ResolvableApiException)e;
                    try {
                        exception.startResolutionForResult(activity,activityCode);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            }
        });
    }

    private void prepareEngineSettings(Context mCtx){
        LocationSettingsRequest request = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(mCtx);
        Task<LocationSettingsResponse> task  = client.checkLocationSettings(request);
        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // setting require are satisfied,  start location update
                startLocationUpdate();      //
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: "+e.getLocalizedMessage());
            }
        });
    }

    private void startLocationUpdate(){
        // start location update
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());
    }

    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            Log.d(TAG, "onLocationResult: "+locationResult.getLastLocation().getLatitude() +", "+locationResult.getLastLocation().getLongitude());
           // LocationAddress.getLocationAddress(locationResult.getLastLocation(),activity);
        }
    };

    public void stopEngine(){
        if(fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    public static boolean isGPSOn(Context mCtx){
        LocationManager manager = (LocationManager)mCtx.getSystemService(Context.LOCATION_SERVICE );
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
