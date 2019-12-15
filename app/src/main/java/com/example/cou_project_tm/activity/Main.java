package com.example.cou_project_tm.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.cou_project_tm.models.User;
import com.example.cou_project_tm.services.AuthentificationService;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

public class Main extends Application {
    public Main(){

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder().serializeNulls().create();

        SharedPreferences sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//        Log.i("user shared preference", String.valueOf(gson.fromJson(sharedpreferences.getString("currentUser",null), User.class)));
        AuthentificationService.setCurrentUser(gson.fromJson(sharedpreferences.getString("currentUser",null), User.class));

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
