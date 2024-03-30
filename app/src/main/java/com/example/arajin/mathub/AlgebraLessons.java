package com.example.arajin.mathub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlgebraLessons extends AppCompatActivity {
    Button back1;
    TextView numeric_range;
    TextView equal;

    TextView progression;

    TextView percent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebra_lessons);


        equal = findViewById(R.id.equal0);
        numeric_range = findViewById(R.id.numeric_range);
        back1 = findViewById(R.id.back1);
        progression = findViewById(R.id.progression);
        percent = findViewById(R.id.percent);


        back1.setOnClickListener(v -> {
            Intent intent = new Intent(AlgebraLessons.this, MainActivity.class);
            startActivity(intent);
        });

        equal.setOnClickListener(v -> {
            Intent intent = new Intent(AlgebraLessons.this, Equals1.class);
            startActivity(intent);
        });

        numeric_range.setOnClickListener(v -> {
            Intent intent = new Intent(AlgebraLessons.this, NumRange.class);
            startActivity(intent);
        });

        progression.setOnClickListener(v -> {
            Intent intent = new Intent(AlgebraLessons.this, Progression.class);
            startActivity(intent);
        });

        percent.setOnClickListener(v -> {
            Intent intent = new Intent(AlgebraLessons.this, Percent.class);
            startActivity(intent);
        });
    }
}