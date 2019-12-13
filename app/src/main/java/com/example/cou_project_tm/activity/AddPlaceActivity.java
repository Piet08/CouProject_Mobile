package com.example.cou_project_tm.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.EnumTypePlace;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceActivity extends AppCompatActivity {

    private EditText etName, etDesc, etNum, etStreet, etCity;
    private Spinner spType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        etCity = findViewById(R.id.et_city);
        etStreet = findViewById(R.id.et_street);
        etNum = findViewById(R.id.et_num);
        etDesc = findViewById(R.id.et_desc);
        etName = findViewById(R.id.et_name);

        addItemsOnspinner();
    }

    public void addItemsOnspinner(){
        spType = findViewById(R.id.sp_type);

        List<String> listItems = new ArrayList<String>();
        for( int i=0;i<EnumTypePlace.values().length;i++)
            listItems.add(EnumTypePlace.values()[i].toString());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(dataAdapter);
    }
}
