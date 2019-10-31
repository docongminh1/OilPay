package com.example.oilpay;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    ArrayList<Oil> arrayList;
    ListView listView;
    OilAdapter oilAdapter;
    TagGroup tagGroup;
    Data database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        database = new Data(SearchActivity.this);

        arrayList = new ArrayList<>();
        listView = findViewById(R.id.listViewSearch);
        oilAdapter = new OilAdapter(SearchActivity.this,arrayList);
        listView.setAdapter(oilAdapter);

        Log.d("OilPayApp", database.LoadFileInternal().size() + "");

        searchView = findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchOil(newText);
                return false;
            }
        });

        tagGroup = findViewById(R.id.tag_transition_group);
        tagGroup.setTags(new String[]{"Motul","Mobil","Extra","Amiloil"});
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void searchOil(String newText) {
        ArrayList<Oil> listFind = new ArrayList<>();
        for(Oil oil : database.LoadFileInternal()){
            if(oil.getNameOil().toLowerCase().contains(newText.toLowerCase())){
                listFind.add(oil);
            }
        }
        Toast.makeText(this, listFind.size()+"", Toast.LENGTH_SHORT).show();
        if(listFind.size() > 0){
            oilAdapter.clear();
            oilAdapter.addAll(listFind);
            oilAdapter.notifyDataSetChanged();
            listView.setVisibility(View.VISIBLE);
        }

        if(newText.isEmpty()){
            listView.setVisibility(View.GONE);
        }
    }
}