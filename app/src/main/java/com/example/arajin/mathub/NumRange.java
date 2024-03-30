package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NumRange extends AppCompatActivity {
    Button back6,task_button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_range);

        Button back6 = findViewById(R.id.back6);
        Button task_button2 = findViewById(R.id.task_button2);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumRange.this, AlgebraLessons.class);
                startActivity(intent);
            }
        });

        task_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumRange.this, RangeTask.class);
                startActivity(intent);
            }
        });


    }
}