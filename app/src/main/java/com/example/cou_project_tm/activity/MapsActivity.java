
package com.example.cou_project_tm.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.CustomInfoWindowAdapter;
import com.example.cou_project_tm.models.EnumTypePlace;
import com.example.cou_project_tm.models.Marker;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.services.GeocoderService;
import com.example.cou_project_tm.services.PlaceRepoService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private GeocoderService geocoderService;
    private List<PlaceAndAddress> placeAndAddressList;
    private List<Marker> allPlacesAndAddresses;
    private List<Marker> markerListFiltered;
    private ClusterManager<Marker> clusterManager;
    private Context context;

    private Spinner spType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity(savedInstanceState);
        context = this;
        listenerSpiner();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initMap(googleMap);
        listenerInfoWindow();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1340){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }else{
                Toast.makeText(this,"Permission was not granted",Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }

    private void initMap(GoogleMap googleMap){
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));
        mMap.setMinZoomPreference(13);
        clusterManager = new ClusterManager<>(this, mMap);
        mMap.setOnCameraIdleListener(clusterManager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }else{
                if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                    Toast.makeText(this,"Position permisition is needed to show you on the map.",Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1340);
            }
        }


        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));

        LatLng mons = new LatLng(50.4535039, 3.9516516);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mons));
    }

    private void getAllPlaceAndAddress(final String type) {
        PlaceRepoService.getPlacesAndAddress().enqueue(new Callback<List<PlaceAndAddress>>() {
            @Override
            public void onResponse(Call<List<PlaceAndAddress>> call, Response<List<PlaceAndAddress>> response) {
                clusterManager.clearItems();
                markerListFiltered.clear();

                if(placeAndAddressList.size() == 0){
                    allPlacesAndAddresses.clear();
                    placeAndAddressList.addAll(response.body());
                    for (PlaceAndAddress pAa : placeAndAddressList){
                        Marker m = new Marker(pAa.getAddress().getId(), pAa.getAddress().getCity(), pAa.getAddress().getStraat(), pAa.getAddress().getNum(), pAa.getAddress().getPostalCode(), pAa.getPlace().getId(), pAa.getPlace().getName(), pAa.getPlace().getDescription(), pAa.getPlace().getType(), pAa.getAvgRate());
                        LatLng tmpLatLng = geocoderService.findLocationFromAddress(m.getNum() + " " + m.getStraat() + " " + m.getCity() + " " + m.getPostalCode());
                        if(tmpLatLng != null){
                            m.setLatLng(tmpLatLng);
                            allPlacesAndAddresses.add(m);
                        }
                    }
                }else{
                    for (Marker m: allPlacesAndAddresses) {
                        if(m.getType().equals(type)) markerListFiltered.add(m);
                    }
                }

                List<Marker> tmp;
                if(type.equals("ALL")){
                    tmp = allPlacesAndAddresses;
                }else{
                    tmp = markerListFiltered;
                }
                clusterManager.addItems(tmp);
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
        allPlacesAndAddresses = new ArrayList<>();
        markerListFiltered = new ArrayList<>();
        spType = new Spinner(this);
        initSpiner();
    }

    private void initSpiner(){
        spType = findViewById(R.id.sp_type_sort);

        List<String> listItems = new ArrayList<String>();
        for(int i = 0; i< EnumTypePlace.values().length; i++)
            listItems.add(EnumTypePlace.values()[i].toString());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(dataAdapter);
    }

    private void listenerSpiner(){
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getAllPlaceAndAddress(spType.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getAllPlaceAndAddress("ALL");
            }
        });
    }

    private void listenerInfoWindow(){
        if(mMap != null){
            mMap.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
                @Override
                public void onInfoWindowLongClick(com.google.android.gms.maps.model.Marker marker) {
                    if((int) marker.getTag() == -1) return;
                    Intent reviewIntent = new Intent(context,ReviewListActivity.class);
                    reviewIntent.putExtra("id", (int) marker.getTag());
                    PlaceAndAddress place = null;
                    for (int i = 0; i < placeAndAddressList.size(); i++) {
                        if(placeAndAddressList.get(i).getPlace().getId() == (int) marker.getTag()){
                            place = placeAndAddressList.get(i);
                        }
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("place",place);
                    reviewIntent.putExtras(bundle);
                    startActivity(reviewIntent);
                }
            });
        }

    }

    public List<Marker> getAllPlacesAndAddresses() { return allPlacesAndAddresses; }

    public void setAllPlacesAndAddresses(List<Marker> allPlacesAndAddresses) { this.allPlacesAndAddresses = allPlacesAndAddresses; }
}