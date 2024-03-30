package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddNewTask extends AppCompatActivity {
    EditText highSchool,testNumber,questionNumber,question, example, variant1, variant3, variant2, variant4, number;
    Button button;
    Button backButton;


    FirebaseDatabase firebaseDatabaseTask ;
    DatabaseReference databaseReferenceTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        highSchool = findViewById(R.id.edit_high_school);
        testNumber = findViewById(R.id.edit_test_number);
        questionNumber = findViewById(R.id.edit_question_number);
        question = findViewById(R.id.edit_question);
        example = findViewById(R.id.edit_example);
        variant1 = findViewById(R.id.edit_variant1);
        variant2 = findViewById(R.id.edit_variant2);
        variant3 = findViewById(R.id.edit_variant3);
        variant4 = findViewById(R.id.edit_variant4);
        number = findViewById(R.id.edit_number);

        button = findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(question.getText().toString().isEmpty() || example.getText().toString().isEmpty() ||
                        variant1.getText().toString().isEmpty() || variant2.getText().toString().isEmpty() ||
                        variant3.getText().toString().isEmpty() || variant4.getText().toString().isEmpty() ||
                        number.getText().toString().isEmpty() || highSchool.getText().toString().isEmpty() ||
                        testNumber.getText().toString().isEmpty() || variant4.getText().toString().isEmpty()){
                    Toast.makeText(AddNewTask.this, "Fill each field",Toast.LENGTH_SHORT).show();
                } else{
                    firebaseFunction();
                }

            }
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewTask.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("counter","2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
    public void firebaseFunction(){
        firebaseDatabaseTask = FirebaseDatabase.getInstance();
        databaseReferenceTask = firebaseDatabaseTask.getReference().child("task");
        HashMap<String,String > userMap = new HashMap<>();
        userMap.put("highSchool", highSchool.getText().toString());
        userMap.put("questionNumber", questionNumber.getText().toString());
        userMap.put("testNumber", testNumber.getText().toString());
        userMap.put("question", question.getText().toString());
        userMap.put("example", example.getText().toString());
        userMap.put("variant1", variant1.getText().toString());
        userMap.put("variant2", variant2.getText().toString());
        userMap.put("variant3", variant3.getText().toString());
        userMap.put("variant4", variant4.getText().toString());
        userMap.put("number", number.getText().toString());
        databaseReferenceTask.push().setValue(userMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(), "Data could not be saved",Toast.LENGTH_SHORT).show();
                    Log.e("Firebase", "Data could not be saved: " + databaseError.getMessage());
                } else {
                    Toast.makeText(getApplicationContext(), "Data saved successfully.",Toast.LENGTH_SHORT).show();
                    Log.d("Firebase", "Data saved successfully.");
                }
            }
        });

    }
}