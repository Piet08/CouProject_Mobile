package com.example.cou_project_tm.services;

import android.util.Log;

import com.example.cou_project_tm.RequestInterceptor;
import com.example.cou_project_tm.config.Configuration;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.repository.PlaceRepo;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor( (chain) -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization","Bearer "+AuthentificationService.getCurrentUser().getToken())
                    .build();
            return  chain.proceed(newRequest);
        }).build();


        repository = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Configuration.URL_API) //Localhost du PC et non du simulateur (5000 http, 5001 https)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlaceRepo.class);
    }

    public static Call<List<PlaceAndAddress>> getPlacesAndAddress(){return ourInstance.repository.getPlacesAndAddress();}

    public static Call<PlaceAndAddress> getPlaceAndAddress(int id){return ourInstance.repository.getPlaceAndAddress(id);}

    public static Call<List<Place>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Void> deletePlaceCascade(int id){return ourInstance.repository.deletePlaceCascade(id);}

    public static Call<Place> post(Place place){
        return ourInstance.repository.post(place);
    }

    public static Call<Place> post(PlaceAndAddress placeAndAddress){
        return ourInstance.repository.post(placeAndAddress);
    }


    public static Call<Void> delete(int id){
        return ourInstance.repository.delete(id);
    }

    public static Call<Void> put(Place place){
        return ourInstance.repository.put(place);
    }
}
