package com.example.cou_project_tm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.ReviewAndUser;
import com.example.cou_project_tm.models.User;

import java.util.List;

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

        ReviewAndUser review = getItem(position);
        User user = review.getUser();

        pseudo.setText(user.getPseudo());
        name.setText(user.getName() + " " + user.getSurname());
        rate.setText(review.getReview().getStar()/2.+"/5");
        comment.setText(review.getReview().getComment());
        date.setText(review.getReview().getDate());

        return v;
    }
}
