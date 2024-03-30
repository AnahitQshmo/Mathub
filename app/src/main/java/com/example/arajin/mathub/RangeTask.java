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

        variant_A1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_B1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_C1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_D1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_A2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_B2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_C2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_D2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_A3.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_B3.setBackgroundColor(Color.parseColor("#4f258a"));


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

        variant_A1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_B1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_C1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_D1.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_A2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_B2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_C2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_D2.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_A3.setBackgroundColor(Color.parseColor("#4f258a"));
        variant_B3.setBackgroundColor(Color.parseColor("#4f258a"));




        variant_A1.setEnabled(true);
        variant_B1.setEnabled(true);
        variant_C1.setEnabled(true);
        variant_D1.setEnabled(true);
        variant_A2.setEnabled(true);
        variant_B2.setEnabled(true);
        variant_C2.setEnabled(true);
        variant_D2.setEnabled(true);
        variant_A3.setEnabled(true);
        variant_B3.setEnabled(true);



        if (Option == variant_A1 || Option == variant_B1 || Option == variant_C1 || Option == variant_D1) {
            if ( right_answer.equals(correct1)) {
                Option.setBackgroundColor(Color.GREEN);
            } else {
                Option.setBackgroundColor(Color.RED);
                variant_A1.setBackgroundColor(Color.GREEN);
            }
        } else if (Option == variant_A2 || Option == variant_B2 || Option == variant_C2 || Option == variant_D2) {
            if (right_answer.equals(correct2)) {
                Option.setBackgroundColor(Color.GREEN);
            } else {
                Option.setBackgroundColor(Color.RED);
                variant_C2.setBackgroundColor(Color.GREEN);
            }
        } else if (Option == variant_A3 || Option == variant_B3) {
            if (right_answer.equals(correct3)) {
                Option.setBackgroundColor(Color.GREEN);
            } else {
                Option.setBackgroundColor(Color.RED);
                variant_B3.setBackgroundColor(Color.GREEN);
            }
        }


        disableButtonsForCurrentQuestion(Option);
    }

    private void disableButtonsForCurrentQuestion(Button selectedOption) {
        if (selectedOption == variant_A1 || selectedOption == variant_B1 || selectedOption == variant_C1 || selectedOption == variant_C1 || selectedOption == variant_D1) {
            variant_A1.setEnabled(false);
            variant_B1.setEnabled(false);
            variant_C1.setEnabled(false);
            variant_D1.setEnabled(false);
        } else if (selectedOption == variant_A2 || selectedOption == variant_B2 || selectedOption == variant_C2 || selectedOption == variant_D2) {
            variant_A2.setEnabled(false);
            variant_B2.setEnabled(false);
            variant_C2.setEnabled(false);
            variant_D2.setEnabled(false);
        } else if (selectedOption == variant_A3 || selectedOption == variant_B3) {
            variant_A3.setEnabled(false);
            variant_B3.setEnabled(false);

        }
    }
}
