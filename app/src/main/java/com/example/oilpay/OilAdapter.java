package com.example.oilpay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OilAdapter extends ArrayAdapter<Oil> {
//    Activity context;
//    int resource;

    Data data ;

    ArrayList<Oil> arrayList;

    public OilAdapter(@NonNull Context context, @NonNull ArrayList<Oil> objects) {
        super(context, 0,objects);
        arrayList = objects;
        data = new Data(getContext());
    }

//    public OilAdapter(Activity context, int resource) {
//        super(context, resource);
//        this.context = context;
//        this.resource = resource;
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = this.context.getLayoutInflater().inflate(this.resource,null);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.layout_oil,null);

        ImageView imageOil = convertView.findViewById(R.id.imageOil);
        TextView txtnameOil = convertView.findViewById(R.id.txtnameOil);
        TextView txtpriceOil = convertView.findViewById(R.id.txtpriceOil);
        ImageView imgThem = convertView.findViewById(R.id.imgThem);
        ImageView imgBot = convertView.findViewById(R.id.imgBot);
        final TextView txtQuantity = convertView.findViewById(R.id.txtQuantity);

        final Oil oil = arrayList.get(position);
        Picasso.get().load(oil.getImageOil()).into(imageOil);
        txtnameOil.setText(oil.getNameOil());
        txtpriceOil.setText(oil.getPriceOil() + "");
        txtQuantity.setText(oil.getQuantity() + "");
        imgBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Da Bot 1 "+ oil.getNameOil(), Toast.LENGTH_LONG).show();
                oil.setQuantity(oil.getQuantity() - 1);
                txtQuantity.setText(oil.getQuantity() + "");
            }
        });
        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Da them 1 "+oil.getNameOil(),Toast.LENGTH_LONG).show();
                oil.setQuantity(oil.getQuantity() +1);
                txtQuantity.setText(oil.getQuantity() + "");
            }
        });
        return convertView;
    }
}
