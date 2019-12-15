package com.example.cou_project_tm.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.activity.ReviewListActivity;
import com.example.cou_project_tm.models.Address;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.services.AuthentificationService;
import com.example.cou_project_tm.services.PlaceRepoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceAndAddressAdapter extends ArrayAdapter<PlaceAndAddress> {

//    Button btnAdd,btnDelete,btnCancel;

    public PlaceAndAddressAdapter(@NonNull Context context, int resource, @NonNull List<PlaceAndAddress> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.item_place,null);
        }

        TextView placeName = v.findViewById(R.id.tv_place_name);
        TextView description = v.findViewById(R.id.tv_description);
        TextView type = v.findViewById(R.id.tv_type);
        RatingBar ratingbar = v.findViewById(R.id.tv_rating_bar);
        TextView address = v.findViewById(R.id.tv_address);

        ConstraintLayout cl = v.findViewById(R.id.item_place_constraint);

        Button btnDelete = cl.findViewById(R.id.item_place_bt_delete);
        Button btnCancel = cl.findViewById(R.id.item_place_bt_cancel);
        Button btnAdd = cl.findViewById(R.id.item_place_bt_add);

        PlaceAndAddress place = getItem(position);
        Address adr = place.getAddress();

        type.setText(place.getPlace().getType());
        placeName.setText(place.getPlace().getName());
        description.setText(place.getPlace().getDescription());
        address.setText(adr.getPostalCode()+", " +adr.getCity()+"\n"+adr.getStraat()+", n° "+adr.getNum());
        ratingbar.setRating((float)place.getAvgRate()/2);

        ConstraintLayout constraintLayout = v.findViewById(R.id.item_place_constraint_global);
        constraintLayout.setOnClickListener(v1 -> {
            Intent reviewIntent = new Intent(getContext(), ReviewListActivity.class);
            reviewIntent.putExtra("id",place.getPlace().getId());
            Bundle bundle = new Bundle();
            bundle.putSerializable("place",place);
            reviewIntent.putExtras(bundle);
            getContext().startActivity(reviewIntent);
        });

        if(AuthentificationService.getCurrentUser().getType().equals("1")){

            btnDelete.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);

            btnDelete.setOnClickListener(v1 -> {

                btnDelete.setVisibility(View.GONE);
                btnCancel.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.VISIBLE);

                btnAdd.setOnClickListener(v3 -> {
                    PlaceRepoService.deletePlaceCascade(place.getPlace().getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            remove(place);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                });

                btnCancel.setOnClickListener(v2 -> {
                    btnDelete.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.GONE);
                    btnAdd.setVisibility(View.GONE);
                });
            });
        }
        return v;
    }

}
