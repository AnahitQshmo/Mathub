package com.example.arajin.mathub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {




        BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView
                = findViewById(R.id.bottom_navigation);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        String x;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            x = bundle.getString("counter");
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

        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput("accInfo.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"File is not found",Toast.LENGTH_SHORT).show();
            Log.e("login activity", "File not found: " + e.toString());
        }

        if(ret.length()>0){
            Toast.makeText(getApplicationContext(),"File contains",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"File is empty",Toast.LENGTH_SHORT).show();
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("accInfo.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write("");
                outputStreamWriter.close();
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
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
        } else if (itemId == R.id.n_school) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, schoolFragment)
                    .commit();
            return true;
        }

        return false;
    }

}
