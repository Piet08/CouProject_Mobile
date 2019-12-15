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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConnectionActivity extends AppCompatActivity {

    private EditText etLogin, etPassword;
    private CheckBox cbRemember;
    private Button btnLogin;
    private User currentUser;
    private

    SharedPreferences sharedpreferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        List<EditText> listEdit = new ArrayList<EditText>();
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);

        listEdit.add(etLogin);
        listEdit.add(etPassword);

        sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getBaseContext(), MainActivity.class);
                if (isCompleted(listEdit)) {
                    AuthentificationService.login(String.valueOf(etLogin.getText()), String.valueOf(etPassword.getText())).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Gson gson = new GsonBuilder().serializeNulls().create();
                            currentUser = response.body();
                            AuthentificationService.setCurrentUser(currentUser);

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            if (cbRemember.isChecked()) {
                                editor.putString("currentUser", gson.toJson(currentUser));
                                editor.apply();
                                Log.i("connection", sharedpreferences.getString("currentUser", null));
                            }
                            startActivity(nextIntent);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.i("connection", "fail");
                            startActivity(nextIntent);

                        }
                    });
                } else {
                    editTextCheck(listEdit);
                }
            }
        });
    }

    private boolean isCompleted(List<EditText> listEdit) {
        for(EditText edit : listEdit){
            if(edit.getText().toString().equals("")){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    public void editTextCheck(List<EditText> list){
        for(EditText edit : list){
            if( edit.getText().toString().trim().equals("")) {
                edit.setError("Le champ est requis.");
            }
        }
    }

    public boolean isChecked (View view){
        if(cbRemember.isChecked()) {
            return true;
        }else if(!cbRemember.isChecked()){
            return false;
        }
        return false;
    }


}
