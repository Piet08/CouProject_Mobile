package com.example.cou_project_tm.repository;

import com.example.cou_project_tm.models.Address;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AddressRepo {
    public static final String URL_ADDRESS = "address";

    @GET(URL_ADDRESS)
    Call<List<Address>> query();

    @POST(URL_ADDRESS)
    Call<Address> post(@Body Address address);

    @DELETE(URL_ADDRESS)
    Call<Void> delete(@Path("id") int id);

    @PUT(URL_ADDRESS)
    Call<Void> put(@Body Address address);
}
