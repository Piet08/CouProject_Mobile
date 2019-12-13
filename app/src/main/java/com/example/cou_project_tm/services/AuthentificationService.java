package com.example.cou_project_tm.services;


import com.example.cou_project_tm.models.AuthenticateModel;
import com.example.cou_project_tm.models.User;

import retrofit2.Call;

public class AuthentificationService {
    private static final AuthentificationService ourInstance = new AuthentificationService();
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
