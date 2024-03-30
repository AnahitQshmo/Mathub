package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Equals2 extends AppCompatActivity {
    Button task_button2, back9;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equals2);
        back9 = findViewById(R.id.back9);
        task_button2 = findViewById(R.id.task_button2);


        back9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equals2.this, Equals1.class);
                startActivity(intent);
            }
        });

        task_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equals2.this, Equation2Task.class);
                startActivity(intent);
            }
        });
    }
}