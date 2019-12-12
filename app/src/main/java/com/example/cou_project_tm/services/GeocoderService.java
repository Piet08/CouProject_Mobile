package com.example.cou_project_tm.services;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

public class GeocoderService {

    private Geocoder geocoder;

    public GeocoderService(Context context){
        initGeocoder(context);
    }

    private void initGeocoder(Context context){
        geocoder = new Geocoder(context);
    }

    public LatLng findLocationFromAddress(String strAddress){
        LatLng result = new LatLng(0,0);
        try {
            Address tmpAddress =  geocoder.getFromLocationName(strAddress,10).get(0);
            result = new LatLng(tmpAddress.getLatitude(),tmpAddress.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MarkerError","Erreur de marker",e);
        }
        return result;
    }
}
