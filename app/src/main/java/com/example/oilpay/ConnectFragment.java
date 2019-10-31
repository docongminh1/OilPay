package com.example.oilpay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ConnectFragment extends Fragment implements View.OnClickListener{

    Button btnCall,btnSms,btnYoutube,btnmapView,btnMess;



    public ConnectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connect, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        YouTubePlayerView youTubePlayerView =
//                view.findViewById(R.id.viewPlayer);
//
//        youTubePlayerView.initialize(getResources().getString(R.string.key_youtube),
//                new YouTubePlayer.OnInitializedListener() {
//                    @Override
//                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
//                                                        YouTubePlayer youTubePlayer, boolean b) {
//                        Log.i("CSC", "CSC success");
//                        // do any work here to cue video, play video, etc.
//                        //tai ve bam play moi phat
//                        youTubePlayer.loadVideo("6L-pwD21yb4");
//                    }
//
//                    @Override
//                    public void onInitializationFailure(YouTubePlayer.Provider provider,
//                                                        YouTubeInitializationResult youTubeInitializationResult) {
//                        Log.i("CSC", "CSC failure");
//                    }
//                });

        btnCall = view.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);
        btnSms = view.findViewById(R.id.btnSms);
        btnSms.setOnClickListener(this);
        btnMess = view.findViewById(R.id.btnMess);
        btnMess.setOnClickListener(this);
        btnYoutube = view.findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(this);
        btnmapView = view.findViewById(R.id.mapView);
        btnmapView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.mapView){
            String uriLocation = "geo:10.759350,106.666397";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriLocation));
            startActivity(intent);
        }
        if(id == R.id.btnCall){
            String uriPhone = "tel:0902384255";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uriPhone));
            startActivity(intent);
        }
        if(id == R.id.btnSms){
            String uriPhone = "smsto:0902384255";
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uriPhone));
            intent.putExtra("sms_body","'Nhập tên sản phẩm'  'Số lượng'");
            startActivity(intent);
        }
        if(id == R.id.btnMess){
            String uriFbMess = "https://m.me/tobibi.1604";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriFbMess));
            startActivity(intent);
        }
        if(id == R.id.btnYoutube){
            String uriYoutube = "https://www.youtube.com/channel/UC3w6_vT0GY4yw1Hd_g_iNSg";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriYoutube));
            startActivity(intent);
        }
    }
}
