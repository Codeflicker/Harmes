package com.harmes.codex.main.locationEngine;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.harmes.codex.config.Config;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationAddress {


    public static void getLocationAddress(Location location, Activity activity){
        try {
            Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); //
            Log.i("LocationAddress", "getLocationAddress: Lat:"+location.getLatitude()+", Longi: "+location.getLongitude()+", Address :"+address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
