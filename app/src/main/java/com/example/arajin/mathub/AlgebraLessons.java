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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebra_lessons);


        TextView equal = findViewById(R.id.equal0);
        TextView numeric_range = findViewById(R.id.numeric_range);
        Button back1 = findViewById(R.id.back1);
        TextView progression = findViewById(R.id.progression);


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

    }
}