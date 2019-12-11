package com.example.cou_project_tm.services;

import com.example.cou_project_tm.config.Configuration;
import com.example.cou_project_tm.models.User;
import com.example.cou_project_tm.repository.UserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepoService {
    private static final UserRepoService ourInstance = new UserRepoService();
    private UserRepo repository;

    public static UserRepoService getInstance() {
        return ourInstance;
    }

    private UserRepoService() {
        init();
    }

    private void init(){
        repository = new Retrofit.Builder()
                .baseUrl(Configuration.URL_API) //Localhost du PC et non du simulateur (5000 http, 5001 https)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserRepo.class);

    }

    public static Call<List<User>> query(){
        return ourInstance.repository.query();
    }

    public static Call<User> post(User user){
        return ourInstance.repository.post(user);
    }

    public static Call<Void> delete(int id){
        return ourInstance.repository.delete(id);
    }

    public static Call<Void> put(User user){
        return ourInstance.repository.put(user);
    }
}
