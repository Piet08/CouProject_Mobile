package com.example.cou_project_tm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cou_project_tm.R;
import com.example.cou_project_tm.models.Address;
import com.example.cou_project_tm.models.PlaceAndAddress;

import java.util.List;

public class PlaceAndAddressAdapter extends ArrayAdapter<PlaceAndAddress> {

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


        PlaceAndAddress place = getItem(position);
        Address adr = place.getAddress();

        type.setText(place.getPlace().getType());
        placeName.setText(place.getPlace().getName());
        description.setText(place.getPlace().getDescription());
        address.setText(adr.getPostalCode()+", " +adr.getCity()+"\n"+adr.getStraat()+", n° "+adr.getNum());
        ratingbar.setRating((float)place.getAvgRate()/2);

        return v;
    }
}
