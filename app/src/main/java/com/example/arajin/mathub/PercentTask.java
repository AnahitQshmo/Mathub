package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PercentTask extends AppCompatActivity {
    Button back12, optionA, optionB, optionC, optionD, optionA1, optionB1, optionC1, optionD1, optionA2, optionB2, optionC2, optionD2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_task);

        back12 = findViewById(R.id.back12);
        optionA = findViewById(R.id.option_a);
        optionB = findViewById(R.id.option_b);
        optionC = findViewById(R.id.option_c);
        optionD = findViewById(R.id.option_d);
        optionA1 = findViewById(R.id.option_a1);
        optionB1 = findViewById(R.id.option_b1);
        optionC1 = findViewById(R.id.option_c1);
        optionD1 = findViewById(R.id.option_d1);
        optionA2 = findViewById(R.id.option_a2);
        optionB2 = findViewById(R.id.option_b2);
        optionC2 = findViewById(R.id.option_c2);
        optionD2 = findViewById(R.id.option_d2);
        back12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PercentTask.this, Percent.class);
                startActivity(intent);
            }
        });

        optionA.setBackgroundColor(Color.parseColor("#4f258a"));
        optionB.setBackgroundColor(Color.parseColor("#4f258a"));
        optionC.setBackgroundColor(Color.parseColor("#4f258a"));
        optionD.setBackgroundColor(Color.parseColor("#4f258a"));
        optionA1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionB1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionC1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionD1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionA2.setBackgroundColor(Color.parseColor("#4f258a"));
        optionB2.setBackgroundColor(Color.parseColor("#4f258a"));
        optionC2.setBackgroundColor(Color.parseColor("#4f258a"));
        optionD2.setBackgroundColor(Color.parseColor("#4f258a"));

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA, "A) 40%");
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB, "B) 35%");
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC, "C) 19%");
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD, "D) 90%");
            }
        });

        optionA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA1, "A) 10%");
            }
        });

        optionB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB1, "B) 15%");
            }
        });

        optionC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC1, "C) 5%");
            }
        });

        optionD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD1, "D) 8%");
            }
        });
        optionA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA2, "A) 100%");
            }
        });

        optionB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB2, "B) 50%");
            }
        });

        optionC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC2, "C) 10.5%");
            }
        });

        optionD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD2, "D) 12%");
            }
        });
    }

    private void checkAnswer(Button selectedOption, String answer) {
        String correctAnswer1 = "A) 40%";
        String correctAnswer2 = "B) 15%";
        String correctAnswer3 = "C) 10.5%";

        optionA.setBackgroundColor(Color.parseColor("#4f258a"));
        optionB.setBackgroundColor(Color.parseColor("#4f258a"));
        optionC.setBackgroundColor(Color.parseColor("#4f258a"));
        optionD.setBackgroundColor(Color.parseColor("#4f258a"));
        optionA1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionB1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionC1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionD1.setBackgroundColor(Color.parseColor("#4f258a"));
        optionA2.setBackgroundColor(Color.parseColor("#4f258a"));
        optionB2.setBackgroundColor(Color.parseColor("#4f258a"));
        optionC2.setBackgroundColor(Color.parseColor("#4f258a"));
        optionD2.setBackgroundColor(Color.parseColor("#4f258a"));

        optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);
        optionA1.setEnabled(true);
        optionB1.setEnabled(true);
        optionC1.setEnabled(true);
        optionD1.setEnabled(true);
        optionA2.setEnabled(true);
        optionB2.setEnabled(true);
        optionC2.setEnabled(true);
        optionD2.setEnabled(true);

        if (selectedOption == optionA || selectedOption == optionB || selectedOption == optionC || selectedOption == optionD) {
            if (answer.equals(correctAnswer1)) {
                selectedOption.setBackgroundColor(Color.GREEN);
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                optionA.setBackgroundColor(Color.GREEN);
            }
        } else if (selectedOption == optionA1 || selectedOption == optionB1 || selectedOption == optionC1 || selectedOption == optionD1) {
            if (answer.equals(correctAnswer2)) {
                selectedOption.setBackgroundColor(Color.GREEN);
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                optionB1.setBackgroundColor(Color.GREEN);
            }
        } else if (selectedOption == optionA2 || selectedOption == optionB2 || selectedOption == optionC2 || selectedOption == optionD2) {
            if (answer.equals(correctAnswer3)) {
                selectedOption.setBackgroundColor(Color.GREEN);
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                optionC2.setBackgroundColor(Color.GREEN);
            }
        }


        disableButtonsForCurrentQuestion(selectedOption);
    }

    private void disableButtonsForCurrentQuestion(Button selectedOption) {
        if (selectedOption == optionA || selectedOption == optionB || selectedOption == optionC || selectedOption == optionD) {
            optionA.setEnabled(false);
            optionB.setEnabled(false);
            optionC.setEnabled(false);
            optionD.setEnabled(false);
        } else if (selectedOption == optionA1 || selectedOption == optionB1 || selectedOption == optionC1 || selectedOption == optionD1) {
            optionA1.setEnabled(false);
            optionB1.setEnabled(false);
            optionC1.setEnabled(false);
            optionD1.setEnabled(false);
        } else if (selectedOption == optionA2 || selectedOption == optionB2 || selectedOption == optionC2 || selectedOption == optionD2) {
            optionA2.setEnabled(false);
            optionB2.setEnabled(false);
            optionC2.setEnabled(false);
            optionD2.setEnabled(false);
        }
    }
}