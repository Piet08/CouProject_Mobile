package com.example.cou_project_tm.services;

import com.example.cou_project_tm.config.Configuration;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.repository.PlaceRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceRepoService {
    private static final PlaceRepoService ourInstance = new PlaceRepoService();
    private PlaceRepo repository;

    public static PlaceRepoService getInstance() {
        return ourInstance;
    }

    private PlaceRepoService() {
        init();
    }

    private void init(){
        repository = new Retrofit.Builder()
                .baseUrl(Configuration.URL_API) //Localhost du PC et non du simulateur (5000 http, 5001 https)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlaceRepo.class);

    }

    public static Call<List<PlaceAndAddress>> getPlacesAndAddress(){return ourInstance.repository.getPlacesAndAddress();}

    public static Call<List<Place>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Place> post(Place place){
        return ourInstance.repository.post(place);
    }

    public static Call<Void> delete(int id){
        return ourInstance.repository.delete(id);
    }

    public static Call<Void> put(Place place){
        return ourInstance.repository.put(place);
    }
}
