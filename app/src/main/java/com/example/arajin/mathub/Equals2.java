package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Equals2 extends AppCompatActivity {
    Button task_button2, back5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equals2);
        back5 = findViewById(R.id.back5);
        task_button2 = findViewById(R.id.task_button2);


        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equals2.this, Equals1.class);
                startActivity(intent);
            }
        });

        task_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equals2.this, AlgebraLessons.class);
                startActivity(intent);
            }
        });
    }
}