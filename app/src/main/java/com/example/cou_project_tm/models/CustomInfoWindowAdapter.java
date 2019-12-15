package com.example.cou_project_tm.models;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.cou_project_tm.activity.MapsActivity;
import com.example.cou_project_tm.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

    private final View mWindow;
    private MapsActivity context;


    public CustomInfoWindowAdapter(MapsActivity context) {
        this.context = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_marker,null);

    }

    private void renderWindow(Marker marker, View v){
        String address;
        String place;
        if(marker.getTitle() == null && marker.getSnippet() == null) {
            address = "..Zoomez pour voir les lieux concentrés..";
            place = ".....";
        }else{
            address = marker.getTitle();
            place = marker.getSnippet();
        }

        TextView tvAddress = v.findViewById(R.id.customTitle);
        TextView tvPlace = v.findViewById(R.id.customDescription);

        tvAddress.setText(address);
        tvPlace.setText(place);
    }


    @Override
    public View getInfoWindow(Marker marker) {
        renderWindow(marker,mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return mWindow;
    }
}
