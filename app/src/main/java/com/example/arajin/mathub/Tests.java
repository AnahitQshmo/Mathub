package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Tests extends AppCompatActivity {
    TextView  questionNumber , questionText , variant1Text, variant2Text, variant3Text, variant4Text ;
    Button next,back;
    int n = 1;
    int number;
    String school , test;
    String rightNum = "";
    int[] arr = new int[10];
    String[] arrR = new String[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        questionNumber = findViewById(R.id.question_num);
        questionText = findViewById(R.id.question_text);
        variant1Text = findViewById(R.id.variant_a);
        variant2Text = findViewById(R.id.variant_b);
        variant3Text = findViewById(R.id.variant_c);
        variant4Text = findViewById(R.id.variant_d);



        Bundle bundle = getIntent().getExtras();
        school = bundle.getString("school");
        test = bundle.getString("testNumber");

        databaseFunc();


        next = findViewById(R.id.button_next);
        back = findViewById(R.id.back);
        back.setVisibility(View.GONE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n == 10){
                    saveData();
                    String mess = "";

                    for(int i : arr){
                        mess += i;
                    }

                    for(String i : arrR){
                        rightNum+=i;
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultOfTest.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("school",school);
                    bundle.putString("test",test);
                    bundle.putString("result",mess);
                    bundle.putString("right",rightNum);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    }else{
                    n++;
                    databaseFunc();
                }
                back.setVisibility(View.VISIBLE);

                variant4Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant2Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant3Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant1Text.setBackground(getDrawable(R.drawable.unclicked_variant));

                try{
                    if(arr[n-1] == 1){
                        variant1Text.setBackground(getDrawable(R.drawable.clicked_variant));
                    }else if(arr[n-1] == 2){
                        variant2Text.setBackground(getDrawable(R.drawable.clicked_variant));
                    }else if(arr[n-1] == 3){
                        variant3Text.setBackground(getDrawable(R.drawable.clicked_variant));
                    }else if(arr[n-1] == 4){
                        variant4Text.setBackground(getDrawable(R.drawable.clicked_variant));
                    }
                }catch (Exception e){

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n == 2){
                    back.setVisibility(View.GONE);
                }else{
                    next.setText("Next");
                    back.setVisibility(View.VISIBLE);
                }
                n--;
                databaseFunc();
                variant4Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant2Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant3Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant1Text.setBackground(getDrawable(R.drawable.unclicked_variant));

                if(arr[n-1] == 1){
                    variant1Text.setBackground(getDrawable(R.drawable.clicked_variant));
                }else if(arr[n-1] == 2){
                    variant2Text.setBackground(getDrawable(R.drawable.clicked_variant));
                }else if(arr[n-1] == 3){
                    variant3Text.setBackground(getDrawable(R.drawable.clicked_variant));
                }else if(arr[n-1] == 4){
                    variant4Text.setBackground(getDrawable(R.drawable.clicked_variant));
                }
            }
        });

        variant1Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                variant1Text.setBackground(getDrawable(R.drawable.clicked_variant));
                variant2Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant3Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant4Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                arr[n-1] = 1;
            }
        });
        variant2Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                variant2Text.setBackground(getDrawable(R.drawable.clicked_variant));
                variant1Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant3Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant4Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                arr[n-1] = 2;
            }
        });
        variant3Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                variant3Text.setBackground(getDrawable(R.drawable.clicked_variant));
                variant2Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant1Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant4Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                arr[n-1] = 3;
            }
        });
        variant4Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                variant4Text.setBackground(getDrawable(R.drawable.clicked_variant));
                variant2Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant3Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                variant1Text.setBackground(getDrawable(R.drawable.unclicked_variant));
                arr[n-1] = 4;
            }
        });

    }
    public void databaseFunc() {
        questionNumber.setText("Question " + n);
        if(n == 10){
            next.setText("Finish");
        }

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child("task").child(school).child(test).child(Integer.toString(n));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot itemSnapshot : childSnapshot.getChildren()) {
                        String key = itemSnapshot.getKey();
                        String value = itemSnapshot.getValue(String.class);
                        switch (key){
                            case "question":
                                questionText.setText(value);
                                break;
                            case "variant1":
                                variant1Text.setText(value);
                                break;
                            case "variant2":
                                variant2Text.setText(value);
                                break;
                            case "variant3":
                                variant3Text.setText(value);
                                break;
                            case "variant4":
                                variant4Text.setText(value);
                                break;
                            case "number":
                                arrR[n-1] = value;
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void saveData() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = database.getReference().child("users").child(user.getUid().toString()).child("answer").child(school).child(test);

        HashMap<String, String> answerData = new HashMap<>();
        answerData.put("1", Integer.toString(arr[0]));
        answerData.put("2", Integer.toString(arr[1]));
        answerData.put("3", Integer.toString(arr[2]));
        answerData.put("4", Integer.toString(arr[3]));
        answerData.put("5", Integer.toString(arr[4]));
        answerData.put("6", Integer.toString(arr[5]));
        answerData.put("7", Integer.toString(arr[6]));
        answerData.put("8", Integer.toString(arr[7]));
        answerData.put("9", Integer.toString(arr[8]));
        answerData.put("10", Integer.toString(arr[9]));

        reference.setValue(answerData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }
}