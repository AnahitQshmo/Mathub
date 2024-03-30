package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProgressionTask extends AppCompatActivity {
    Button back9, tarberakA1,tarberakB1,tarberakC1, tarberakD1,tarberakA2, tarberakB2, tarberakD2,tarberakC2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression_task);

        Button back9 = findViewById(R.id.back9);

        tarberakA1 = findViewById(R.id.tarberakA1);
        tarberakB1 = findViewById(R.id.tarberakB1);
        tarberakC1 = findViewById(R.id.tarberakC1);
        tarberakD1 = findViewById(R.id.tarberakD1);
        tarberakA2 = findViewById(R.id.tarberakA2);
        tarberakB2 = findViewById(R.id.tarberakB2);
        tarberakC2 = findViewById(R.id.tarberakC2);
        tarberakD2 = findViewById(R.id.tarberakD2);


        back9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgressionTask.this, Progression.class);
                startActivity(intent);
            }
        });



        tarberakA1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakB1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakC1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakD1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakA2.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakB2.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakC2.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakD2.setBackgroundColor(Color.parseColor("#4f258a"));


        tarberakA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakA1, "A) 5");
            }
        });

        tarberakB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakB1, "B) 13");
            }
        });

        tarberakC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakC1, "C) -4");
            }
        });

        tarberakD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakD1, "D) 28");
            }
        });


        tarberakA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakA2, "A) 10");
            }
        });

        tarberakB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakB2, "B) 2");
            }
        });

        tarberakC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakC2, "C) -13");
            }
        });

        tarberakD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(tarberakD2, "D) 80");
            }
        });



    }

    private void checkAnswer(Button Option1, String right_answer1) {
        String correct1_ = "D) 28";
        String correct2_ = "B) 2";

        tarberakA1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakB1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakC1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakD1.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakA2.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakB2.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakC2.setBackgroundColor(Color.parseColor("#4f258a"));
        tarberakD2.setBackgroundColor(Color.parseColor("#4f258a"));


        tarberakA1.setEnabled(true);
        tarberakB1.setEnabled(true);
        tarberakC1.setEnabled(true);
        tarberakD1.setEnabled(true);
        tarberakA2.setEnabled(true);
        tarberakB2.setEnabled(true);
        tarberakC2.setEnabled(true);
        tarberakD2.setEnabled(true);


        if (Option1 == tarberakA1 || Option1 == tarberakB1 || Option1 == tarberakC1 || Option1 == tarberakD1) {
            if (right_answer1.equals(correct1_)) {
                Option1.setBackgroundColor(Color.GREEN);
            } else {
                Option1.setBackgroundColor(Color.RED);
                tarberakD1.setBackgroundColor(Color.GREEN);
            }
        } else if (Option1 == tarberakA2 || Option1 == tarberakB2 || Option1 == tarberakC2 || Option1 == tarberakD2) {
            if (right_answer1.equals(correct2_)) {
                Option1.setBackgroundColor(Color.GREEN);
            } else {
                Option1.setBackgroundColor(Color.RED);
                tarberakB2.setBackgroundColor(Color.GREEN);
            }
        }


        disableButtonsForCurrentQuestion(Option1);
    }

    private void disableButtonsForCurrentQuestion(Button selectedOption) {
        if (selectedOption == tarberakA1 || selectedOption == tarberakB1 || selectedOption == tarberakC1 || selectedOption == tarberakD1) {
            tarberakA1.setEnabled(false);
            tarberakB1.setEnabled(false);
            tarberakC1.setEnabled(false);
            tarberakD1.setEnabled(false);
        } else if (selectedOption == tarberakA2 || selectedOption == tarberakB2 || selectedOption == tarberakC2 || selectedOption == tarberakD2) {
            tarberakA2.setEnabled(false);
            tarberakB2.setEnabled(false);
            tarberakC2.setEnabled(false);
            tarberakD2.setEnabled(false);
        }
    }
}