package com.example.oilpay;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PayAdapter extends ArrayAdapter<Oil> {
    Activity context;
    int resource;

    public PayAdapter(Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = this.context.getLayoutInflater().inflate(this.resource,null);

        ImageView imgOilpay = view.findViewById(R.id.imgOilpay);
        TextView txtOilpay = view.findViewById(R.id.nameOilpay);
        TextView txtpriceOilpay = view.findViewById(R.id.priceOilpay);
        TextView txtquantitypay = view.findViewById(R.id.quantityOilpay);

        Oil oil = getItem(position);
        imgOilpay.setImageResource(oil.getImageOil());
        txtOilpay.setText(oil.getNameOil());
        txtpriceOilpay.setText(oil.getPriceOil() + "");
        txtquantitypay.setText(oil.getQuantity() + "");

        return view;
    }
}
