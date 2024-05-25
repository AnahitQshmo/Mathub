package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Progression extends AppCompatActivity {
    Button back8;
    TextView task_button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);


        back8 = findViewById(R.id.back8);
        task_button4 = findViewById(R.id.task_button4);
        back8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Progression.this, AlgebraLessons.class);
                startActivity(intent);
            }
        });

        task_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Progression.this, ProgressionTask.class);
                startActivity(intent);
            }
        });
    }
}
