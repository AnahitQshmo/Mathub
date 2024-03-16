package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class HighSchool extends AppCompatActivity {
    String counter;
    TextView schoolName;
    Button back;
    TextView textViewOne;
    TextView textViewTwo;
    String text1;
    String text2;
    String text;
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
        text = " ";
        textViewOne = findViewById(R.id.text_one);
        textViewTwo = findViewById(R.id.text_two);
        try {
            InputStream is = getAssets().open("high_school.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);    is.close();
            text = new String(buffer);
        } catch (Exception e) {    e.printStackTrace();
            text+=e;}
        String[] contain= text.split("///");
        switch (counter){
            case "polytech":
                text1 = contain[0];
                text2 = contain[1];
                imageView.setImageDrawable(getDrawable(R.drawable.polytech));
                schoolName.setText("Polytech high school");
                break;
            case "physmath":
                text1 = contain[2];
                text2 = contain[3];
                imageView.setImageDrawable(getDrawable(R.drawable.physmath));
                schoolName.setText("Physmath high school");
                break;
            case "quantum":
                text1 = contain[4];
                text2 = contain[5];
                imageView.setImageDrawable(getDrawable(R.drawable.quantum));
                schoolName.setText("Quantum high school");
                break;
            case "ayb":
                text1 = contain[6];
                text2 = contain[7];
                imageView.setImageDrawable(getDrawable(R.drawable.ayb));
                schoolName.setText("Ayb high school");
                break;
        }
        textViewOne.setText(text1);
        textViewTwo.setText(text2);
    }
}