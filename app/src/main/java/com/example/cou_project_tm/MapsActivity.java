package com.example.cou_project_tm;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.example.cou_project_tm.models.Marker;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.services.GeocoderService;
import com.example.cou_project_tm.services.PlaceRepoService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GeocoderService geocoderService;
    private List<PlaceAndAddress> placeAndAddressList;
    private List<Marker> addressListFromPlaces;
    private ClusterManager<Marker> clusterManager;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initActivity(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initMap(googleMap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1340) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }


    }

    private void initMap(GoogleMap googleMap){
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));
        mMap.setMinZoomPreference(13);
        clusterManager = new ClusterManager<>(this, mMap);
        getAllPlaceAndAddress();
        mMap.setOnCameraIdleListener(clusterManager);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        LatLng mons = new LatLng(50.4535039, 3.9516516);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mons));
    }

    private void getAllPlaceAndAddress() {
        PlaceRepoService.getPlacesAndAddress().enqueue(new Callback<List<PlaceAndAddress>>() {
            @Override
            public void onResponse(Call<List<PlaceAndAddress>> call, Response<List<PlaceAndAddress>> response) {
                placeAndAddressList.addAll(response.body());
                for (final PlaceAndAddress pAa : placeAndAddressList) {
                    Marker m = new Marker(pAa.getAddress().getId(), pAa.getAddress().getCity(), pAa.getAddress().getStraat(), pAa.getAddress().getNum(), pAa.getAddress().getPostalCode(), pAa.getPlace().getId(),pAa.getPlace().getName(),pAa.getPlace().getDescription(),pAa.getPlace().getType(), pAa.getAvgRate());
                    LatLng tmpLatLng = geocoderService.findLocationFromAddress(m.getNum() + " " + m.getStraat() + " " + m.getCity() + " " + m.getPostalCode());
                    m.setLatLng(tmpLatLng);
                    addressListFromPlaces.add(m);
                }

                clusterManager.addItems(addressListFromPlaces);
                clusterManager.cluster();
            }

            @Override
            public void onFailure(Call<List<PlaceAndAddress>> call, Throwable t) {
                Log.e("loadingPlacesAddresses", "", t);
            }
        });
    }

    private void initActivity(Bundle savedInstanceState){
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoderService = new GeocoderService(this);
        placeAndAddressList = new ArrayList<>();
        addressListFromPlaces = new ArrayList<>();
    }
}
