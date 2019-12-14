package com.example.cou_project_tm.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.User;
import com.example.cou_project_tm.services.AuthentificationService;
import com.example.cou_project_tm.services.UserRepoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConnectionActivity extends AppCompatActivity {

    private EditText etLogin, etPassword;
    private CheckBox cbRemember;
    private Button btnLogin;
    private User currentUser;

    SharedPreferences sharedpreferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);

        sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthentificationService.login(String.valueOf(etLogin.getText()),String.valueOf(etPassword.getText())).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Gson gson = new GsonBuilder().serializeNulls().create();
                        currentUser = response.body();
                        AuthentificationService.setCurrentUser(currentUser);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        if(cbRemember.isChecked())
                            editor.putString("currentUser",gson.toJson(currentUser));
                            editor.apply();

                        Log.i("connection",sharedpreferences.getString("currentUser",null));

                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("connection","fail");
                    }
                });
                Intent nextIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(nextIntent);
            }

        });

        //fetchUser();
    }



    public boolean isChecked (View view){
        if(cbRemember.isChecked()) {
            return true;
        }else if(!cbRemember.isChecked()){
            return false;
        }
        return false;
    }

    private void fetchUser() {
        UserRepoService.query().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("User",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("fail","fail");
            }
        });

    }


}
