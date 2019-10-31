package com.example.oilpay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbarMain;
    ImageView imageMenu,imageClose;
    NavigationView navigationView;
    View menuView;
//    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbarMain = findViewById(R.id.toolbarMain);
        navigationView = findViewById(R.id.nav_menu);

//        searchView = findViewById(R.id.search_view);
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
//            }
//        });

        menuView = navigationView.getHeaderView(0);
        imageMenu = toolbarMain.findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        imageClose = menuView.findViewById(R.id.imageClose);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        displayFragment(R.id.dsGioithieu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                displayFragment(menuItem.getItemId());
                return false;
            }
        });
    }

    private void displayFragment(int id) {
        Fragment fragment ;

        switch (id){
            case R.id.dsGioithieu:
                //getSupportActionBar().setTitle("Gioi thieu cua hang");
                fragment = new GioithieuFragment();
                loadFragment(fragment);
                Toast.makeText(MainActivity.this,"Gioi thieu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.dsSanpham:
                //getSupportActionBar().setTitle("Gioi thieu cua hang");
                fragment = new OilFragment();
                loadFragment(fragment);
                Toast.makeText(MainActivity.this,"Gioi thieu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.dsLienhe:
                //getSupportActionBar().setTitle("Thong tin lien he");
                fragment = new ConnectFragment();
                loadFragment(fragment);
                Toast.makeText(MainActivity.this,"Lien he",Toast.LENGTH_SHORT).show();
                break;
        }
//        if(id == R.id.dsGioithieu){
//            getSupportActionBar().setTitle("Gioi thieu cua hang");
//            fragment = new GioithieuFragment();
//            loadFragment(fragment);
//            Toast.makeText(MainActivity.this,"Gioi thieu",Toast.LENGTH_SHORT).show();
//        }
//        if(id == R.id.dsSanpham){
//            getSupportActionBar().setTitle("San pham cua hang");
//            fragment = new OilFragment();
//            loadFragment(fragment);
//            Toast.makeText(MainActivity.this,"Oil", Toast.LENGTH_SHORT).show();
//        }
//
//        if(id == R.id.dsLienhe){
//            getSupportActionBar().setTitle("Thong tin lien he");
//            fragment = new ConnectFragment();
//            loadFragment(fragment);
//            Toast.makeText(MainActivity.this,"Lien he",Toast.LENGTH_SHORT).show();
//        }

//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.content,fragment);
//        fragmentTransaction.commit();

        drawerLayout.closeDrawers();
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();
    }

}
