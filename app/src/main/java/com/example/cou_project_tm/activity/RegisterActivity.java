package com.example.cou_project_tm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.Address;
import com.example.cou_project_tm.models.User;
import com.example.cou_project_tm.models.UserAndAddress;
import com.example.cou_project_tm.services.AuthentificationService;
import com.example.cou_project_tm.services.UserRepoService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etName, etSurname, etPseudo, etPwd, etStreet, etNum, etCp, etCity, etVerifPwd;
    private Button btnRegister;
    AlertDialog.Builder builder;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        List<EditText> listEdit = new ArrayList<EditText>();

        etEmail = findViewById(R.id.et_email);
        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_surname);
        etPseudo = findViewById(R.id.et_pseudo);
        etPwd = findViewById(R.id.et_passwordRegister);
        etVerifPwd = findViewById(R.id.et_verifPasswordRegister);
        etStreet = findViewById(R.id.et_userStreet);
        etNum = findViewById(R.id.et_userNum);
        etCp = findViewById(R.id.et_userCp);
        etCity = findViewById(R.id.et_userCity);
        btnRegister = findViewById(R.id.btn_register);

        listEdit.add(etEmail);
        listEdit.add(etName);
        listEdit.add(etSurname);
        listEdit.add(etPseudo);
        listEdit.add(etPwd);
        listEdit.add(etVerifPwd);
        listEdit.add(etStreet);
        listEdit.add(etNum);
        listEdit.add(etCp);
        listEdit.add(etCity);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCompleted(listEdit)) {
                    if (verifPwd() == true) {
                        UserRepoService.post(buildUserAndAddress()).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Log.i("UserAdd", response.body().toString());

                                AuthentificationService.login(String.valueOf(etPseudo.getText()),String.valueOf(etVerifPwd.getText()));
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.i("fail", "fail");
                            }
                        });

                        Intent nextIntent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(nextIntent);
                    } else {
                        String msg = "Vous n'avez pas encodé les mêmes mot de passe !";
                        alert(msg);
                    }
                }else{
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


    public boolean verifPwd(){
        return etPwd.getText().toString().equals(etVerifPwd.getText().toString());
    }

    public void alert(String msg){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention !");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void editTextCheck(List<EditText> list){
        for(EditText edit : list){
            if( edit.getText().toString().trim().equals("")) {
                edit.setError("Le champ est requis.");
            }
        }
    }

    public Address buildAddress(){
        Address address = new Address();
        address.setCity(String.valueOf(etCity.getText()));
        address.setNum(String.valueOf(etNum.getText()));
        address.setStraat(String.valueOf(etStreet.getText()));
        address.setPostalCode(Integer.parseInt(String.valueOf(etCp.getText())));
        return address;
    }

    public User buildUser(){
        User user = new User();
        user.setEmail(String.valueOf(etEmail.getText()));
        user.setName(String.valueOf(etName.getText()));
        user.setSurname(String.valueOf(etSurname.getText()));
        user.setPseudo(String.valueOf((etPseudo.getText())));
        user.setHashpwd(String.valueOf(etPwd.getText()));
        user.setType("0");
        return user;
    }

    public UserAndAddress buildUserAndAddress(){
        UserAndAddress userAndAddress = new UserAndAddress();
        userAndAddress.setAddress(buildAddress());
        userAndAddress.setUser(buildUser());
        return userAndAddress;
    }


}
