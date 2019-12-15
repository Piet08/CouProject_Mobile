package com.example.cou_project_tm.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.Review;
import com.example.cou_project_tm.models.ReviewAndUser;
import com.example.cou_project_tm.models.User;
import com.example.cou_project_tm.services.AuthentificationService;
import com.example.cou_project_tm.services.ReviewRepoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewAndUserAdapter extends ArrayAdapter<ReviewAndUser> {
    public ReviewAndUserAdapter(@NonNull Context context, int resource, @NonNull List<ReviewAndUser> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.item_review,null);
        }

        TextView pseudo = v.findViewById(R.id.item_review_tv_pseudo);
        TextView name = v.findViewById(R.id.item_review_tv_name);
        TextView rate = v.findViewById(R.id.item_review_tv_rate);
        TextView comment = v.findViewById(R.id.item_review_tv_comment);
        TextView date = v.findViewById(R.id.item_review_tv_date);
        Button btnRemove = v.findViewById(R.id.item_review_bt_remove);
        Button btnUpdate = v.findViewById(R.id.item_review_bt_update);

        ConstraintLayout clPrint = v.findViewById(R.id.item_review_constraint_print);
        ConstraintLayout clBtn = v.findViewById(R.id.item_review_constraint_btn);
        View vUpdate = v.findViewById(R.id.item_review_update_review);
        RatingBar rateUpdate = vUpdate.findViewById(R.id.update_review_rate);
        EditText commentUpdate = vUpdate.findViewById(R.id.update_review_comment);
        Button btnCancel = vUpdate.findViewById(R.id.update_review_btn_cancel);
        Button btnAdd = vUpdate.findViewById(R.id.update_review_btn_add);

        ReviewAndUser review = getItem(position);
        User user = review.getUser();
        Review review1 = review.getReview();

        pseudo.setText(user.getPseudo());
        name.setText(user.getName() + " " + user.getSurname());
        rate.setText(review.getReview().getStar()/2.+"/5");
        comment.setText(review.getReview().getComment());
        date.setText(review.getReview().getDate());

        if(AuthentificationService.getCurrentUser().getId() == user.getId() || AuthentificationService.getCurrentUser().getType().equals("1")){
            btnRemove.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);

            btnRemove.setOnClickListener(v1 -> {
                ReviewRepoService.delete(review.getReview().getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        remove(review);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            });

            btnUpdate.setOnClickListener(v1 -> {
                clPrint.setVisibility(View.GONE);
                clBtn.setVisibility(View.GONE);
                vUpdate.setVisibility(View.VISIBLE);
                btnAdd.setOnClickListener(v2 -> {
                    review1.setStar((int) rateUpdate.getRating()*2);
                    review1.setComment(String.valueOf(commentUpdate.getText()));
                    ReviewRepoService.put(review1).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            notifyDataSetChanged();
                            clPrint.setVisibility(View.VISIBLE);
                            clBtn.setVisibility(View.VISIBLE);
                            vUpdate.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                });
                btnCancel.setOnClickListener(v3 -> {
                    clPrint.setVisibility(View.VISIBLE);
                    clBtn.setVisibility(View.VISIBLE);
                    vUpdate.setVisibility(View.GONE);
                });
            });
        }else{
            btnRemove.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
        }

        return v;
    }


}
