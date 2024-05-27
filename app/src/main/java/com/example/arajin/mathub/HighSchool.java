package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HighSchool extends AppCompatActivity {
    String counter;
    TextView schoolName;
    Button back;
    TextView textViewOne;
    TextView textViewTwo;
    String text1;
    String text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_school);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication() , MainActivity.class);
                startActivity(intent);
            }
        });


        Bundle bundle = getIntent().getExtras();
        counter = bundle.getString("counter");
        schoolName = (TextView) findViewById(R.id.school_name);
        ImageView imageView = findViewById(R.id.school_photo);
        text1 = " ";
        text2 = " ";
        textViewOne = findViewById(R.id.text_one);
        textViewTwo = findViewById(R.id.text_two);

        switch (counter){
            case "polytech":
                text1 = getResources().getString(R.string.polytech1);
                text2 = getResources().getString(R.string.polytech2);
                imageView.setImageDrawable(getDrawable(R.drawable.polytech));
                schoolName.setText(getResources().getString(R.string.polytech_name));
                break;
            case "physmath":
                text1 = getResources().getString(R.string.physmath1);
                text2 = getResources().getString(R.string.physmath2);
                imageView.setImageDrawable(getDrawable(R.drawable.physmath));
                schoolName.setText(getResources().getString(R.string.physmath_name));
                break;
            case "quantum":
                text1 = getResources().getString(R.string.quantum1);
                text2 = getResources().getString(R.string.quantum2);
                imageView.setImageDrawable(getDrawable(R.drawable.quantum));
                schoolName.setText(getResources().getString(R.string.quantum_name));
                break;
            case "ayb":
                text1 = getResources().getString(R.string.ayb1);
                text2 = getResources().getString(R.string.ayb2);
                imageView.setImageDrawable(getDrawable(R.drawable.ayb));
                schoolName.setText(getResources().getString(R.string.ayb_name));
                break;
        }
        textViewOne.setText(text1);
        textViewTwo.setText(text2);
    }
}