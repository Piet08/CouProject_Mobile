package com.example.cou_project_tm.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cou_project_tm.models.AuthenticateModel;
import com.example.cou_project_tm.models.User;

import retrofit2.Call;

public class AuthentificationService {
    private static final AuthentificationService ourInstance = new AuthentificationService();
    private static Call<User> currentUser2;
    private static User currentUser;
    private static UserRepoService repository;

    public static AuthentificationService getInstance() {
        return ourInstance;
    }

    private AuthentificationService(){
        init();
    }

    private void init() {
        if(currentUser == null)
            currentUser = new User();
        repository = UserRepoService.getInstance();

    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        AuthentificationService.currentUser = currentUser;
    }

    public static Call<User> login(String username, String password){
        AuthenticateModel auth = new AuthenticateModel(username,password);
        return UserRepoService.post(auth);
    }



}
