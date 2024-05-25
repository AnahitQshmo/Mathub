package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ResultOfTest extends AppCompatActivity {
    String school, test , result , right , testSend;
    String[] arr = {"1","1","1","1","1","1","1","1","1","1"};
    String[] arrR = {"1","1","1","1","1","1","1","1","1","1"};
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_of_test);

        Button back = findViewById(R.id.back_but);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("counter","1");
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            school = bundle.getString("school");
            test = bundle.getString("test");
            result = bundle.getString("result");
            right = bundle.getString("right");

        }
        for(int i = 0; i< 10 ; i++){
            arr[i] = "" + result.toCharArray()[i];
            arrR[i] ="" + right.toCharArray()[i];
            if(arr[i].equals(arrR[i])){
                score++;
            }
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userPath = "users/" + currentUser.getUid();

            mDatabase.child(userPath).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String scoreData = dataSnapshot.child("score").getValue(String.class);
                        String testsData = dataSnapshot.child("test").getValue(String.class);
                        score += Integer.parseInt(scoreData);
                        testSend = testsData + school + "<,>" + test + "/]";

                        Map<String, Object> updates = new HashMap<>();
                        updates.put("score", Integer.toString(score));
                        updates.put("test", testSend);

                        mDatabase.child(userPath).updateChildren(updates)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        updateListView();

    }

    private void updateListView() {
        for(int i = 0; i<10;i++){
            arr[i] ="Your answer is " +arr[i] + " | Right answer is "+ arrR[i];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        ListView listView = findViewById(R.id.resuld_lv);
        listView.setAdapter(adapter);

        TextView textScore = findViewById(R.id.score);
        textScore.setText("Your score: "+score+"/10");
    }
}