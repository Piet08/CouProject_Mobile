package com.example.cou_project_tm;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectionActivity extends AppCompatActivity {

    private EditText etLogin, etPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);

    }
}
