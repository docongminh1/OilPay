package com.example.oilpay;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class OilFragment extends Fragment implements View.OnClickListener {
    ListView listView;
    ImageView imageThanhtoan;
    ArrayList<Oil> arrayList = new ArrayList<Oil>();
    OilAdapter oilAdapter;
    Data data;

    EditText searchView;


    public OilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = new Data(getContext());
        return inflater.inflate(R.layout.fragment_oil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

        searchView = view.findViewById(R.id.search_view);
        searchView.setOnClickListener(this);

        imageThanhtoan = view.findViewById(R.id.imageThanhtoan);
        imageThanhtoan.setOnClickListener(this);

        //chuyen dong quay
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageThanhtoan,View.ROTATION_X,90);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageThanhtoan,View.ROTATION_Y,0,90);
        objectAnimator1.setDuration(1000);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator1.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator1.setInterpolator(new AccelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator,objectAnimator1);
        animatorSet.start();

        arrayList = data.getData();
        oilAdapter = new OilAdapter(getContext(),arrayList);
        listView.setAdapter(oilAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Data.oilHistory.add(arrayList.get(i));
                Toast.makeText(getContext(), i + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("oil", arrayList.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.imageThanhtoan){
            Intent intent = new Intent(getActivity(), PayActivity.class);
            intent.putExtra( "listDetail",arrayList);
//            for (Oil oil:arrayList){
//                intent.putExtra(oil.getId() + "",oil);
//            }
            startActivity(intent);
        }
        if(id == R.id.search_view){
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        data.WriteToFileInternal(arrayList);
    }
}
