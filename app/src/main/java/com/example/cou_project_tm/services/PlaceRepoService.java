package com.example.cou_project_tm.services;

import com.example.cou_project_tm.RequestInterceptor;
import com.example.cou_project_tm.config.Configuration;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.repository.PlaceRepo;

import java.util.List;

import okhttp3.OkHttpClient;
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

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor()) // This is used to add ApplicationInterceptor.
                .addNetworkInterceptor(new RequestInterceptor()) //This is used to add NetworkInterceptor.
                .build();
        //Defining the Retrofit using Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.URL_API) //This is the onlt mandatory call on Builder object.
                .client(okHttpClient) //The Htttp client to be used for requests
                .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                .build();

    }

    public static Call<List<PlaceAndAddress>> getPlacesAndAddress(){return ourInstance.repository.getPlacesAndAddress();}

    public static Call<List<Place>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Place> post(Place place){
        return ourInstance.repository.post(place);
    }

    public static Call<PlaceAndAddress> post(PlaceAndAddress placeAndAddress){
        return ourInstance.repository.post(placeAndAddress);
    }


    public static Call<Void> delete(int id){
        return ourInstance.repository.delete(id);
    }

    public static Call<Void> put(Place place){
        return ourInstance.repository.put(place);
    }
}
