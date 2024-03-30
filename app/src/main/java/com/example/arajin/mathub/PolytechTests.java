package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class PolytechTests extends AppCompatActivity {
    int tNum , qNum;
    TextView questionText , exampleText, variant1Text, variant2Text, variant3Text, variant4Text ,numberText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polytech_tests);

        // num must be random
        tNum = 1;

        qNum = 0;


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("test");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String highSchool = snapshot.child("highSchool").getValue(String.class);
                    String questionNumber = snapshot.child("questionNumber").getValue(String.class);
                    String testNumber = snapshot.child("testNumber").getValue(String.class);
                    String question = snapshot.child("question").getValue(String.class);
                    String example = snapshot.child("example").getValue(String.class);
                    String variant1 = snapshot.child("variant1").getValue(String.class);
                    String variant2 = snapshot.child("variant2").getValue(String.class);
                    String variant3 = snapshot.child("variant3").getValue(String.class);
                    String variant4 = snapshot.child("variant4").getValue(String.class);
                    String number = snapshot.child("number").getValue(String.class);
                    if (highSchool.equals("polytech") && testNumber.equals(Integer.toString(tNum)) && questionNumber.equals(Integer.toString(qNum))) {

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}