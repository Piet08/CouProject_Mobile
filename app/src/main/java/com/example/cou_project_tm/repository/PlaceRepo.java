package com.example.cou_project_tm.repository;

import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlaceRepo {
    public static final String URL_PLACE = "place";

    @GET(URL_PLACE)
    Call<List<Place>> query();

    @GET(URL_PLACE+"/addresses")
    Call<List<PlaceAndAddress>> getPlacesAndAddress();

    @POST(URL_PLACE)
    Call<Place> post(@Body Place place);

    @POST(URL_PLACE+"/forms")
    Call<Place> post(@Body PlaceAndAddress placeAndAddress);

    @DELETE(URL_PLACE+"/address/reviews/{id}")
    Call<Void> deletePlaceCascade(@Path("id") int id);

    @DELETE(URL_PLACE+"/{id}")
    Call<Void> delete(@Path("id")int id);

    @PUT(URL_PLACE)
    Call<Void> put(@Body Place place);

    @GET(URL_PLACE+"/address/{id}")
    Call<PlaceAndAddress> getPlaceAndAddress(@Path("id") int id);
}
