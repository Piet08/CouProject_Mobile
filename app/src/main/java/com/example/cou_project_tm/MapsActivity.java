package com.example.cou_project_tm;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cou_project_tm.models.Address;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.services.AddressRepoService;
import com.example.cou_project_tm.services.GeocoderService;
import com.example.cou_project_tm.services.PlaceRepoService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GeocoderService geocoderService;
    private List<Place> placeList;
    private List<Address> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoderService = new GeocoderService(this);
        placeList = new ArrayList<>();
        addressList = new ArrayList<>();
        getAllPlace();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.mapstyle));
        mMap.setMinZoomPreference(11);

        LatLng mons = new LatLng(50.4535039, 3.9516516);
        mMap.addMarker(new MarkerOptions().position(mons).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(geocoderService.findLocationFromAddress("17 rue de la cour 7950 Huissignies")).title("Chez moi"));

        Log.i("Markers",placeList.toString());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(mons));
    }

    private void getAllPlace(){
        PlaceRepoService.query().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                Log.i("Places","Liste recup de la requete" + response.body().toString());
                placeList.addAll(response.body());
                addressList =  getAllAddressFromPlaces();
                Log.i("placesAfter",placeList.toString());
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Log.e("LoadQuery","Error loading places",t);
            }
        });
    }

    private List<Address> getAllAddressFromPlaces(){
        final List<Address> addressList = new ArrayList<>();

        for (Place place: placeList) {
            AddressRepoService.get(place.getIdAdr()).enqueue(new Callback<Address>() {
                @Override
                public void onResponse(Call<Address> call, Response<Address> response) {
                    addressList.add(response.body());
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {
                    Log.e("getQueryAddress","Error found address");
                }
            });
        }

        return addressList;
    }
}
