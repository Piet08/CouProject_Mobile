package com.example.cou_project_tm.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.cou_project_tm.models.AuthenticateModel;
import com.example.cou_project_tm.models.User;

import io.reactivex.Observable;
import retrofit2.Call;

public class AuthentificationService {
    private static final AuthentificationService ourInstance = new AuthentificationService();
    private static User currentUser = new User();
    private static Observable<User> userObservable = Observable.just(currentUser);

    private static UserRepoService repository;

    public static AuthentificationService getInstance() {
        return ourInstance;
    }

    private AuthentificationService(){
        init();
    }

    private void init() {
        repository = UserRepoService.getInstance();

    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
//        Log.i("subscribeBefore",currentUser == null ? "null":currentUser.toString());
        if(currentUser == null)
            currentUser = new User();
        AuthentificationService.currentUser = currentUser;
        if(currentUser==null) {
            currentUser = new User();
        }
    }

    public static Observable<User> getUserObservable() {
        return userObservable;
    }

    public static void setUserObservable(Observable<User> userObservable) {
        AuthentificationService.userObservable = userObservable;
    }

    public static Call<User> login(String username, String password){
        AuthenticateModel auth = new AuthenticateModel(username,password);
        return UserRepoService.post(auth);
    }



}
