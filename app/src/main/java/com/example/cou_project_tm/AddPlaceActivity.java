package com.example.cou_project_tm;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlaceActivity extends AppCompatActivity {

    private EditText etName, etDesc, etNum, etStreet, etCity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        etCity = findViewById(R.id.et_city);
        etStreet = findViewById(R.id.et_street);
        etNum = findViewById(R.id.et_num);
        etDesc = findViewById(R.id.et_desc);
        etName = findViewById(R.id.et_name);
    }
}
