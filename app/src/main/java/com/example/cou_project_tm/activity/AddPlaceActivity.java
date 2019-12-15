package com.example.cou_project_tm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.models.Address;
import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.EnumTypePlace;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.services.PlaceRepoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlaceActivity extends AppCompatActivity {

    private EditText etName, etCp, etNum, etStreet, etCity, etDesc;
    private Spinner spType;
    private Button btnAdd;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        List<EditText> listEdit = new ArrayList<EditText>();

        etCity = findViewById(R.id.et_city);
        etStreet = findViewById(R.id.et_street);
        etNum = findViewById(R.id.et_num);
        etCp = findViewById(R.id.et_cp);
        etName = findViewById(R.id.et_name);
        etDesc = findViewById(R.id.et_desc);
        btnAdd = findViewById(R.id.btn_add);

        listEdit.add(etCity);
        listEdit.add(etStreet);
        listEdit.add(etNum);
        listEdit.add(etCp);
        listEdit.add(etName);
        listEdit.add(etDesc);
        addItemsOnspinner();





        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCompleted(listEdit)) {
                    PlaceRepoService.post(buildPlaceAndAddress()).enqueue(new Callback<Place>() {
                        @Override
                        public void onResponse(Call<Place> call, Response<Place> response) {
                            Log.i("PlaceAdd", response.body().toString());
                            Intent nextIntent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(nextIntent);
                        }

                        @Override
                        public void onFailure(Call<Place> call, Throwable t) {
                            Log.i("fail", "fail");
                        }
                    });

                }else {
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

    public void addItemsOnspinner(){
        spType = findViewById(R.id.sp_type);
        List<String> listItems = new ArrayList<String>();
        for( int i=0;i<EnumTypePlace.values().length;i++)
            listItems.add(EnumTypePlace.values()[i].toString());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(dataAdapter);
    }

    public Address buildAddress(){
        Address address = new Address();
        address.setCity(String.valueOf(etCity.getText()));
        address.setNum(String.valueOf(etNum.getText()));
        address.setStraat(String.valueOf(etStreet.getText()));
        address.setPostalCode(Integer.parseInt(String.valueOf(etCp.getText())));
        return address;
    }

    public Place buildPlace(){
        Place place = new Place();
        place.setName(String.valueOf(etName.getText()));
        place.setType(spType.getSelectedItem().toString());
        place.setDescription(String.valueOf(etDesc.getText()));
        return place;
    }

    public PlaceAndAddress buildPlaceAndAddress(){
        PlaceAndAddress placeAndAddress = new PlaceAndAddress();
        placeAndAddress.setAddress(buildAddress());
        placeAndAddress.setPlace(buildPlace());
        return placeAndAddress;
    }
}
