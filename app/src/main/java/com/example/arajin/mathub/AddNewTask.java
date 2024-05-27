package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddNewTask extends AppCompatActivity {
    EditText question, variant1, variant3, variant2, variant4;
    Spinner number;
    Button button;
    Button backButton;

    TextView questNum;

    FirebaseDatabase firebaseDatabaseTask ;
    DatabaseReference databaseReferenceTask;

    String num , highSchool , testNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        Bundle bundle = getIntent().getExtras();
        num = bundle.getString("num");
        highSchool = bundle.getString("school");
        testNum = bundle.getString("testNum");

        question = findViewById(R.id.edit_question);
        variant1 = findViewById(R.id.edit_variant1);
        variant2 = findViewById(R.id.edit_variant2);
        variant3 = findViewById(R.id.edit_variant3);
        variant4 = findViewById(R.id.edit_variant4);
        questNum = findViewById(R.id.question_number);
        questNum.setText("Question number : "+ num);
        number = findViewById(R.id.spinner_variant);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.variants, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        number.setAdapter(adapter);

        button = findViewById(R.id.add_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(question.getText().toString().isEmpty() ||
                        variant1.getText().toString().isEmpty() || variant2.getText().toString().isEmpty() ||
                        variant3.getText().toString().isEmpty() || variant4.getText().toString().isEmpty() ||
                        variant4.getText().toString().isEmpty()){
                    Toast.makeText(AddNewTask.this, "Fill each field",Toast.LENGTH_LONG).show();
                } else{
                    if(num.equals("10")){
                        firebaseFunction();
                        Intent intent = new Intent(AddNewTask.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("counter","2");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        firebaseFunction();
                        num = Integer.toString(Integer.parseInt(num)+1);
                        questNum.setText("Question number : "+ num);
                        question.setText(null);
                        variant1.setText(null);
                        variant2.setText(null);
                        variant3.setText(null);
                        variant4.setText(null);
                        number.setSelection(0);

                    }
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
        databaseReferenceTask = firebaseDatabaseTask.getReference().child("task").child(highSchool).child(testNum).child(num);
        HashMap<String,String > userMap = new HashMap<>();
        userMap.put("question", question.getText().toString());
        userMap.put("variant1", variant1.getText().toString());
        userMap.put("variant2", variant2.getText().toString());
        userMap.put("variant3", variant3.getText().toString());
        userMap.put("variant4", variant4.getText().toString());
        userMap.put("number", number.getSelectedItem().toString());
        databaseReferenceTask.push().setValue(userMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Log.e("Firebase", "Data could not be saved: " + databaseError.getMessage());
                } else {
                    Toast.makeText(getApplicationContext(), "Data saved successfully.",Toast.LENGTH_SHORT).show();
                    Log.d("Firebase", "Data saved successfully.");
                }
            }
        });

    }
}