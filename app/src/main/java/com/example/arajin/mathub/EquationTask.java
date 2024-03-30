package com.example.arajin.mathub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EquationTask extends AppCompatActivity {

    Button back3, optionA, optionB, optionC, optionD, optionA1, optionB1, optionC1, optionD1, optionA2, optionB2, optionC2, optionD2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_task);

        Button back3 = findViewById(R.id.back3);

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

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquationTask.this, Equals1.class);
                startActivity(intent);
            }
        });

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA, "A) 1");
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB, "B) 3");
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
                checkAnswer(optionD, "D) 0");
            }
        });

        optionA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA1, "A) 10");
            }
        });

        optionB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionB1, "B) 3");
            }
        });

        optionC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC1, "C) -13");
            }
        });

        optionD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD1, "D) 8");
            }
        });
        optionA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA2, "A) 0");
            }
        });

        optionB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionA, "B) 5");
            }
        });

        optionC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionC2, "C) 12");
            }
        });

        optionD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(optionD2, "D) 9");
            }
        });
    }

    private void checkAnswer(Button selectedOption, String answer) {
        String correctAnswer1 = "C) 4, -4";
        String correctAnswer2 = "A) 10";
        String correctAnswer3 = "B) 5";
        if (selectedOption == optionA || selectedOption == optionB || selectedOption == optionC || selectedOption == optionD) {
            if (answer.equals(correctAnswer1)) {
                selectedOption.setBackgroundColor(Color.GREEN);
                Toast.makeText(EquationTask.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                Toast.makeText(EquationTask.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
            }


            optionA.setEnabled(false);
            optionB.setEnabled(false);
            optionC.setEnabled(false);
            optionD.setEnabled(false);
        } else if (selectedOption == optionA1 || selectedOption == optionB1 || selectedOption == optionC1 || selectedOption == optionD1) {
            if (answer.equals(correctAnswer2)) {
                selectedOption.setBackgroundColor(Color.GREEN);
                Toast.makeText(EquationTask.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                Toast.makeText(EquationTask.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
            }

            optionA1.setEnabled(false);
            optionB1.setEnabled(false);
            optionC1.setEnabled(false);
            optionD1.setEnabled(false);


        } else if (selectedOption == optionA2 || selectedOption == optionB2 || selectedOption == optionC2 || selectedOption == optionD2) {
            if (answer.equals(correctAnswer3)) {
                selectedOption.setBackgroundColor(Color.GREEN);
                Toast.makeText(EquationTask.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
            } else {
                selectedOption.setBackgroundColor(Color.RED);
                Toast.makeText(EquationTask.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
            }

            optionA2.setEnabled(false);
            optionB2.setEnabled(false);
            optionC2.setEnabled(false);
            optionD2.setEnabled(false);

        }

    }
}