package com.example.cou_project_tm.repository;

import com.example.cou_project_tm.models.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReviewRepo {
    public static final String URL_REVIEW = "review";

    @GET(URL_REVIEW)
    Call<List<Review>> query();

    @POST(URL_REVIEW)
    Call<Review> post(@Body Review review);

    @DELETE(URL_REVIEW)
    Call<Void> delete(@Path("id") int id);

    @PUT(URL_REVIEW)
    Call<Void> put(@Body Review review);
}
