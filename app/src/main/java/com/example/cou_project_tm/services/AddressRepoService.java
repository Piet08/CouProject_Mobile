package com.example.cou_project_tm.services;

import com.example.cou_project_tm.config.Configuration;
import com.example.cou_project_tm.models.Address;
import com.example.cou_project_tm.repository.AddressRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressRepoService {
    private static final AddressRepoService ourInstance = new AddressRepoService();
    private AddressRepo repository;

    public static AddressRepoService getInstance() {
        return ourInstance;
    }

    private AddressRepoService() {
        init();
    }

    private void init(){
        repository = new Retrofit.Builder()
                .baseUrl(Configuration.URL_API) //Localhost du PC et non du simulateur (5000 http, 5001 https)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AddressRepo.class);

    }

    public static Call<List<Address>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Address> post(Address address){
        return ourInstance.repository.post(address);
    }

    public static Call<Void> delete(int id){
        return ourInstance.repository.delete(id);
    }

    public static Call<Void> put(Address address){
        return ourInstance.repository.put(address);
    }
}
