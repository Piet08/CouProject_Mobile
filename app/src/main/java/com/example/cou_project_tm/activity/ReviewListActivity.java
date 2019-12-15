package com.example.cou_project_tm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.adapter.ReviewAndUserAdapter;
import com.example.cou_project_tm.models.Place;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.models.ReviewAndUser;
import com.example.cou_project_tm.services.AuthentificationService;
import com.example.cou_project_tm.services.PlaceRepoService;
import com.example.cou_project_tm.services.ReviewRepoService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvReviews;
    private List<ReviewAndUser> reviews;
    private PlaceAndAddress place;
    private ReviewAndUserAdapter reviewAndUserAdapter;
    private static int id = 4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            setId(bundle.getInt("id"));
            place = (PlaceAndAddress)bundle.getSerializable("place");
            setSimplePlaceView();
        }


        reviews = new ArrayList<>();
        lvReviews = findViewById(R.id.tv_reviews_lv_reviews);
        reviewAndUserAdapter = new ReviewAndUserAdapter(this,R.id.tv_reviews_lv_reviews,reviews);
        lvReviews.setAdapter(reviewAndUserAdapter);
        lvReviews.setOnItemClickListener(this);
        loadReviews();

        FloatingActionButton button = findViewById(R.id.reviews_bt_add);
        button.setOnClickListener(v -> {
            if(AuthentificationService.getCurrentUser().getType() == "-1"){
                Toast.makeText(this,"You have to be logged !",Toast.LENGTH_LONG).show();
            }else{
                Intent intentAddReview = new Intent(this,AddReviewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("place",place);
                intentAddReview.putExtras(b);
                startActivity(intentAddReview);
            }
        });

    }

    private void setSimplePlaceView(){
        View simplePlace =findViewById(R.id.simple_place_review);
        TextView tvName = simplePlace.findViewById(R.id.simple_place_tv_name);
        TextView tvType = simplePlace.findViewById(R.id.simple_place_tv_type);
        TextView tvDescription = simplePlace.findViewById(R.id.simple_place_tv_description);
        TextView tvNote = simplePlace.findViewById(R.id.simple_place_tv_note);
        tvName.setText(place.getPlace().getName());
        tvType.setText(place.getPlace().getType());
        tvDescription.setText(place.getPlace().getDescription());
        tvNote.setText("Note : "+place.getAvgRate()/2 + "/5");
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