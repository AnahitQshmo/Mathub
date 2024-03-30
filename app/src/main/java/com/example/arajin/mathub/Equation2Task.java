package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Equation2Task extends AppCompatActivity {
    Button back10, optionA, optionB, optionC, optionD, optionA1, optionB1, optionC1, optionD1, optionA2, optionB2, optionC2, optionD2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation2_task);

        back10 = findViewById(R.id.back10);

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

        back10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equation2Task.this, Equals2.class);
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
                checkAnswer(optionA, "A) -5;6");
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB, "B) 4; 2,5");
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC, "C) 4, -4");
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD, "D) 0; -7");
            }
        });

        optionA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA1, "A) -10; -25");
            }
        });

        optionB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB1, "B) 3; 0");
            }
        });

        optionC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC1, "C) -9; 7");
            }
        });

        optionD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD1, "D) 8; 0");
            }
        });
        optionA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA2, "A) 2; 4");
            }
        });

        optionB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB2, "B) 5; -10");
            }
        });

        optionC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC2, "C) 12; 14");
            }
        });

        optionD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD2, "D) -1; 9/4");
            }
        });
    }

    private void checkAnswer(Button selectedOption, String answer) {
        String correctAnswer1 = "B) 4; 2,5";
        String correctAnswer2 = "A) -10; -25";
        String correctAnswer3 = "D) -1; 9/4";

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
                optionB.setBackgroundColor(Color.GREEN);
            }
        } else if (selectedOption == optionA1 || selectedOption == optionB1 || selectedOption == optionC1 || selectedOption == optionD1) {
            if (answer.equals(correctAnswer2)) {
                selectedOption.setBackgroundColor(Color.GREEN);
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                optionA1.setBackgroundColor(Color.GREEN);
            }
        } else if (selectedOption == optionA2 || selectedOption == optionB2 || selectedOption == optionC2 || selectedOption == optionD2) {
            if (answer.equals(correctAnswer3)) {
                selectedOption.setBackgroundColor(Color.GREEN);
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                optionD2.setBackgroundColor(Color.GREEN);
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