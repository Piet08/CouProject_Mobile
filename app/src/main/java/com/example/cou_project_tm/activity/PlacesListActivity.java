package com.example.cou_project_tm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.adapter.PlaceAndAddressAdapter;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.services.PlaceRepoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        lvPlaces.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final PlaceAndAddress place = places.get(position);
        ReviewListActivity.setId(place.getPlace().getId());
        Intent reviewIntent = new Intent(this,ReviewListActivity.class);
        reviewIntent.putExtra(String.valueOf(R.string.ID_PLACE_FOR_REVIEW),place.getPlace().getId()+"");
        startActivity(new Intent(this,ReviewListActivity.class));
    }
}
