package com.example.cou_project_tm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.Address;
import com.example.cou_project_tm.models.PlaceAndAddress;
import com.example.cou_project_tm.services.AuthentificationService;
import com.example.cou_project_tm.services.PlaceRepoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceAndAddressAdapter extends ArrayAdapter<PlaceAndAddress> {

    Button btnAdd,btnDelete,btnCancel;

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

        btnDelete = v.findViewById(R.id.item_place_bt_delete);
        btnCancel = v.findViewById(R.id.item_place_bt_cancel);
        btnAdd = v.findViewById(R.id.item_place_bt_add);

        PlaceAndAddress place = getItem(position);
        Address adr = place.getAddress();

        type.setText(place.getPlace().getType());
        placeName.setText(place.getPlace().getName());
        description.setText(place.getPlace().getDescription());
        address.setText(adr.getPostalCode()+", " +adr.getCity()+"\n"+adr.getStraat()+", n° "+adr.getNum());
        ratingbar.setRating((float)place.getAvgRate()/2);

        if(AuthentificationService.getCurrentUser().getType().equals("1")){

            swapBtn(false);

            btnDelete.setOnClickListener(v1 -> {
                swapBtn(true);

                btnAdd.setOnClickListener(v3 -> {
                    PlaceRepoService.deletePlaceCascade(place.getPlace().getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            swapBtn(false);
                            remove(place);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                });

                btnCancel.setOnClickListener(v2 -> {
                    swapBtn(false);
                });
            });
        }
        return v;
    }

    private void swapBtn(boolean b){
        if(!b){
            btnDelete.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);
        }else{
            btnDelete.setVisibility(View.GONE);
            btnCancel.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
        }

    }
}
