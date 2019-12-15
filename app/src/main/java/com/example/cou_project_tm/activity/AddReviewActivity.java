package com.example.cou_project_tm.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.models.Review;
import com.example.cou_project_tm.services.AuthentificationService;
import com.example.cou_project_tm.services.ReviewRepoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReviewActivity extends AppCompatActivity {

    private EditText etComment;
    private RatingBar rbNote;
    private Button btAdd;
    private PlaceAndAddress place;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            place = (PlaceAndAddress)bundle.getSerializable("place");
            setSimplePlaceView();
        }

        etComment = findViewById(R.id.add_review_et_comment);
        rbNote = findViewById(R.id.add_review_note);
        btAdd = findViewById(R.id.add_review_bt_add);



        btAdd.setOnClickListener(v -> {
            if(String.valueOf(etComment.getText()) != "" && rbNote.getRating() != 0)
                ReviewRepoService.post(buildReview()).enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {
                        Log.i("reviewAdded",buildReview().toString());
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Review> call, Throwable t) {

                    }
                });
            else{
                Toast.makeText(this,"Le formulaire n'est pas complet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setSimplePlaceView(){
        View simplePlace =findViewById(R.id.simple_place_add_review);
        TextView tvName = simplePlace.findViewById(R.id.simple_place_tv_name);
        TextView tvType = simplePlace.findViewById(R.id.simple_place_tv_type);
        TextView tvDescription = simplePlace.findViewById(R.id.simple_place_tv_description);
        TextView tvNote = simplePlace.findViewById(R.id.simple_place_tv_note);
        tvName.setText(place.getPlace().getName());
        tvType.setText(place.getPlace().getType());
        tvDescription.setText(place.getPlace().getDescription());
        tvNote.setText("Note : "+place.getAvgRate()/2 + "/5");
    }

    public Review buildReview(){
        Review review = new Review();
        review.setComment(String.valueOf(etComment.getText()));
        review.setStar((int) rbNote.getRating()*2);
        review.setIdPlace(place.getPlace().getId());
        review.setIdUser(AuthentificationService.getCurrentUser().getId());
        return review;
    }


}
