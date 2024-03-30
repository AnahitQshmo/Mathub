package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Equals1 extends AppCompatActivity {
    Button back2, task_button1, next1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equals);

        back2 = findViewById(R.id.back2);
        next1 = findViewById(R.id.next1);
        task_button1 = findViewById(R.id.task_button1);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equals1.this, AlgebraLessons.class);
                startActivity(intent);
            }
        });


        task_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equals1.this, EquationTask.class);
                startActivity(intent);
            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equals1.this, Equals2.class);
                startActivity(intent);
            }
        });
    }

}