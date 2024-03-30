package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RangeTask extends AppCompatActivity {

    Button back7, variant_A1, variant_B1, variant_C1, variant_D1, variant_A2, variant_B2, variant_C2, variant_D2, variant_A3, variant_B3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_task);

        back7 = findViewById(R.id.back7);
        variant_A1 = findViewById(R.id.variant_A1);
        variant_B1 = findViewById(R.id.variant_B1);
        variant_C1 = findViewById(R.id.variant_C1);
        variant_D1 = findViewById(R.id.variant_D1);
        variant_A2 = findViewById(R.id.variant_A2);
        variant_B2 = findViewById(R.id.variant_B2);
        variant_C2 = findViewById(R.id.variant_C2);
        variant_D2 = findViewById(R.id.variant_D2);
        variant_A3 = findViewById(R.id.variant_A3);
        variant_B3 = findViewById(R.id.variant_B3);

        back7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RangeTask.this, NumRange.class);
                startActivity(intent);
            }
        });

        variant_A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_A1, "A) 1");
            }
        });
        variant_B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_B1, "B) 3");
            }
        });

        variant_C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_C1, "C) 9");
            }
        });

        variant_D1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_D1, "D) -4");
            }
        });

        variant_A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_A2, "A) 10");
            }
        });
        variant_B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_B2, "B) 3");
            }
        });
        variant_C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_C2, "C) -13");
            }
        });
        variant_D2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_D2, "D) 4");
            }
        });
        variant_A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_A3, "A) True");
            }
        });

        variant_B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(variant_B3, "B) False");
            }
        });
    }

    private void checkAnswer(Button Option, String right_answer) {
        String correct1 = "A) 1";
        String correct2 = "C) -13";
        String correct3 = "B) False";

        if (Option.getText().toString().equals(correct1) ||
                Option.getText().toString().equals(correct2) ||
                Option.getText().toString().equals(correct3)) {
            Option.setBackgroundColor(Color.GREEN);
            Toast.makeText(RangeTask.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
        } else {
            Option.setBackgroundColor(Color.RED);
            Toast.makeText(RangeTask.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
        }
        Option.setEnabled(false);
    }
}
