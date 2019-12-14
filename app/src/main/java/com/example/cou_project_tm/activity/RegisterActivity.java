package com.example.cou_project_tm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etName, etSurname, etPseudo, etPwd, etStreet, etNum, etCp, etCity;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);

        etEmail = findViewById(R.id.et_email);
        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_surname);
        etPseudo = findViewById(R.id.et_pseudo);
        etPwd = findViewById(R.id.et_passwordRegister);
        etStreet = findViewById(R.id.et_userStreet);
        etNum = findViewById(R.id.et_userNum);
        etCp = findViewById(R.id.et_userCp);
        etCity = findViewById(R.id.et_userCity);


    }
}
