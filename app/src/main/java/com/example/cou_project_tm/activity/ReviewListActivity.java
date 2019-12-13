package com.example.cou_project_tm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.adapter.ReviewAndUserAdapter;
import com.example.cou_project_tm.models.ReviewAndUser;
import com.example.cou_project_tm.services.ReviewRepoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvReviews;
    private List<ReviewAndUser> reviews;
    private ReviewAndUserAdapter reviewAndUserAdapter;
    private static int id = 4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);

        reviews = new ArrayList<>();
        lvReviews = findViewById(R.id.tv_reviews_lv_reviews);
        reviewAndUserAdapter = new ReviewAndUserAdapter(this,R.id.tv_reviews_lv_reviews,reviews);
        lvReviews.setAdapter(reviewAndUserAdapter);
        lvReviews.setOnItemClickListener(this);
        loadReviews();

    }

    public static void setId(int id){
        ReviewListActivity.id = id;
    }

    private void loadReviews() {
        ReviewRepoService.getReviewsAndUsers(id).enqueue(new Callback<List<ReviewAndUser>>() {
            @Override
            public void onResponse(Call<List<ReviewAndUser>> call, Response<List<ReviewAndUser>> response) {
                Log.i("reviews",response.body().toString());
                reviews.addAll(response.body());
                reviewAndUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ReviewAndUser>> call, Throwable t) {
                Log.i("failReviews","failReviews");
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("click","test");
        final ReviewAndUser review = reviews.get(position);
        Toast.makeText(this,review.toString(),Toast.LENGTH_LONG).show();

    }
}