package com.example.cou_project_tm.repository;

import com.example.cou_project_tm.models.AuthenticateModel;
import com.example.cou_project_tm.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserRepo {
    public static final String URL_USER = "user";

    @GET(URL_USER)
    Call<List<User>> query();

    @POST(URL_USER+"/authenticate")
    Call<AuthenticateModel> post(@Body AuthenticateModel auth);

    @POST(URL_USER)
    Call<User> post(@Body User user);

    @DELETE(URL_USER)
    Call<Void> delete(@Path("id") int id);

    @PUT(URL_USER)
    Call<Void> put(@Body User user);
}
