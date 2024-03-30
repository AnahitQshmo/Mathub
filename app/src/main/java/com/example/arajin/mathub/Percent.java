package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Percent extends AppCompatActivity {

    Button back11, task_button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);


        back11 = findViewById(R.id.back11);
        task_button5 = findViewById(R.id.task_button5);
        back11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Percent.this, AlgebraLessons.class);
                startActivity(intent);
            }
        });
//
        task_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Percent.this, PercentTask.class);
                startActivity(intent);
            }
        });
    }
}