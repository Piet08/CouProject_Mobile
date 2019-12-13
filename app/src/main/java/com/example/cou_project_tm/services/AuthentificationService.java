package com.example.cou_project_tm.services;


import com.example.cou_project_tm.RequestInterceptor;
import com.example.cou_project_tm.config.Configuration;
import com.example.cou_project_tm.models.AuthenticateModel;
import com.example.cou_project_tm.models.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

public class AuthentificationService {
    private static final AuthentificationService ourInstance = new AuthentificationService();

    public static Call<User> getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Call<User> currentUser) {
        AuthentificationService.currentUser = currentUser;
    }

    private static Call<User> currentUser;
    private static UserRepoService repository;
    private static AuthenticateModel auth;

    public static AuthentificationService getInstance() {
        return ourInstance;
    }



    public AuthentificationService(){
        init();
    }

    private void init() {
        repository = UserRepoService.getInstance();
    }

    public static Call<User> login(String username, String password, boolean remember){
        AuthenticateModel auth = new AuthenticateModel(username,password,remember);
        currentUser = repository.post(auth);
        return currentUser;
    }


}
