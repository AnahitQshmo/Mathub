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
        switch (counter){
            case "polytech":
                imageView.setImageDrawable(getDrawable(R.drawable.polytech));
                schoolName.setText("Polytech high school");
            case "physmath":
                imageView.setImageDrawable(getDrawable(R.drawable.physmath));
                schoolName.setText("Physmath high school");
            case "ayb":
                imageView.setImageDrawable(getDrawable(R.drawable.ayb));
                schoolName.setText("Ayb high school");
            case "quantum":
                imageView.setImageDrawable(getDrawable(R.drawable.polytech));
                schoolName.setText("Quantum high school");
        }
    }
}