package com.example.arajin.mathub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {
        BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.n_school);
        String x;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
            x = bundle.getString("counter");
            if(Objects.nonNull(x)){
                if(x.equals("1")){
                    bottomNavigationView.setSelectedItemId(R.id.n_home);
                }else if(x.equals("2")){
                    bottomNavigationView.setSelectedItemId(R.id.n_profile);
                }else{
                    bottomNavigationView.setSelectedItemId(R.id.n_school);
                }
            }else{
                bottomNavigationView.setSelectedItemId(R.id.n_school);
            }

        }else{
            bottomNavigationView.setSelectedItemId(R.id.n_school);
        }
    }

    HomeFragment homeFragment = new HomeFragment();
    SchoolFragment schoolFragment = new SchoolFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.n_home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.n_profile) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, profileFragment)
                    .commit();
            return true;
        } else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, schoolFragment)
                    .commit();
            return true;
        }
    }
}
