package com.example.cou_project_tm.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.User;
import com.example.cou_project_tm.services.AuthentificationService;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

//        TextView helloUser = findViewById(R.id.activity_main_name_user_current);

        sharedpreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        this.setCurrentUser();



    }

    private void setCurrentUser() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Log.i("user shared preference", String.valueOf(gson.fromJson(sharedpreferences.getString("currentUser",null), User.class)));
        AuthentificationService.setCurrentUser(gson.fromJson(sharedpreferences.getString("currentUser",null), User.class));
    }


    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Tous les intents doivent se trouver dans le switch
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();
        Intent nextIntent = null;

        switch (id){
            case R.id.activity_main_drawer_con :
                nextIntent = new Intent(this, ConnectionActivity.class);
                break;
            case R.id.activity_main_drawer_map :
                nextIntent = new Intent (this, MapsActivity.class);
                break;
            case R.id.activity_main_drawer_places :
                nextIntent = new Intent(this,PlacesListActivity.class);
                break;
            case R.id.activity_main_drawer_addPlace :
                nextIntent = new Intent(this, AddPlaceActivity.class);
                break;
            case R.id.activity_main_drawer_logOut:
                logOut();
                nextIntent = new Intent(this, MainActivity.class);
                break;
            default:
                break;
        }
        startActivity(nextIntent);
        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void logOut(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
        //Log.i("UserContains", sharedpreferences.getString("currentUser",null));
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}