package com.example.cou_project_tm.services;

import com.example.cou_project_tm.config.Configuration;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.models.Review;
import com.example.cou_project_tm.models.ReviewAndUser;
import com.example.cou_project_tm.repository.ReviewRepo;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewRepoService {
    private static final ReviewRepoService ourInstance = new ReviewRepoService();
    private ReviewRepo repository;

    public static ReviewRepoService getInstance() {
        return ourInstance;
    }

    private ReviewRepoService() {
        init();
    }

    private void init(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor( (chain) -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization","Bearer "+AuthentificationService.getCurrentUser().getToken())
                    .build();
            return  chain.proceed(newRequest);
        }).build();

        repository = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Configuration.URL_API) //Localhost du PC et non du simulateur (5000 http, 5001 https)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ReviewRepo.class);

    }



    public static Call<List<Review>> query(){
        return ourInstance.repository.query();
    }

    public static Call<List<ReviewAndUser>> getReviewsAndUsers(int id){
        return ourInstance.repository.getReviewsAndUsers(id);
    }

    public static Call<Review> post(Review review){
        return ourInstance.repository.post(review);
    }

    public static Call<Void> delete(int id){
        return ourInstance.repository.delete(id);
    }

    public static Call<Void> put(Review review){
        return ourInstance.repository.put(review);
    }
}
