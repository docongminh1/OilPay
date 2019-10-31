package com.example.oilpay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView  txtnameDetail,txtPriceDetail,txtDetail;
    ImageView imageDetail;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        data = new Data(DetailActivity.this);

        imageDetail = findViewById(R.id.imageDetail);
        txtnameDetail = findViewById(R.id.txtnameDetail);
        txtPriceDetail = findViewById(R.id.txtPriceDetail);
        txtDetail = findViewById(R.id.txtDetail);

        Intent intent = getIntent();
        Oil oil = (Oil) intent.getSerializableExtra("oil");
        txtnameDetail.setText(oil.getNameOil());
        txtPriceDetail.setText(oil.getPriceOil() + "");
        txtDetail.setText(oil.getNameOil());
        Picasso.get().load(oil.getImageOil()).into(imageDetail);

        getSupportActionBar().setTitle(oil.getNameOil());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
