package com.example.cou_project_tm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.adapter.PlaceAndAddressAdapter;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.services.PlaceRepoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesListActivity extends AppCompatActivity {

    private ListView lvPlaces;
    private List<PlaceAndAddress> places;
    private PlaceAndAddressAdapter placesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);

        places = new ArrayList<>();
        lvPlaces = findViewById(R.id.lv_places);
        placesAdapter = new PlaceAndAddressAdapter(this,R.id.lv_places,places);
        lvPlaces.setAdapter(placesAdapter);
        loadPlaces();

    }

    private void loadPlaces() {
        PlaceRepoService.getPlacesAndAddress().enqueue(new Callback<List<PlaceAndAddress>>() {
            @Override
            public void onResponse(Call<List<PlaceAndAddress>> call, Response<List<PlaceAndAddress>> response) {
                Log.i("places",response.body().toString());
                places.addAll(response.body());
                placesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PlaceAndAddress>> call, Throwable t) {
                Log.i("failPlaces","fail");
            }
        });

    }
}
