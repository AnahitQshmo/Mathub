package com.example.arajin.mathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText nameView ,gmailView, passwordView,passwordCheckView;
    TextView register;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nameView = findViewById(R.id.text3);
        gmailView = findViewById(R.id.text5);
        passwordView = findViewById(R.id.text7);
        passwordCheckView = findViewById(R.id.check_password_enter);
        register = findViewById(R.id.click);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    Toast.makeText(getApplicationContext(),"input fields are correct",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"input fields are incorrect",Toast.LENGTH_SHORT).show();
                }
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("accInfo.txt", Context.MODE_PRIVATE));
                    outputStreamWriter.write(nameView.getText().toString()+","+
                            gmailView.getText().toString()+","+
                            passwordView.getText().toString()+ ","+
                            passwordCheckView.getText().toString()+",");
                    outputStreamWriter.close();
                }
                catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                String ret = "";

                try {
                    InputStream inputStream = getApplicationContext().openFileInput("accInfo.txt");

                    if ( inputStream != null ) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";
                        StringBuilder stringBuilder = new StringBuilder();

                        while ( (receiveString = bufferedReader.readLine()) != null ) {
                            stringBuilder.append("\n").append(receiveString);
                        }

                        inputStream.close();
                        ret = stringBuilder.toString();
                    }
                }
                catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"File is not found",Toast.LENGTH_SHORT).show();
                    Log.e("login activity", "File not found: " + e.toString());
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"io exception",Toast.LENGTH_SHORT).show();
                    Log.e("login activity", "Can not read file: " + e.toString());
                }

                if(ret.length()>0){
                    Toast.makeText(getApplicationContext(),"File contains",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"File is empty",Toast.LENGTH_SHORT).show();

                }

                HashMap<String,String > userMap = new HashMap<>();

                userMap.put("name",nameView.getText().toString());
                userMap.put("gmail",gmailView.getText().toString());
                userMap.put("password",passwordView.getText().toString());

                databaseReference.push().setValue(userMap, new DatabaseReference.CompletionListener() {
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
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("counter","2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    public boolean check(){
        int counter = 0;
        int let = 0;
        char[] letters = gmailView.getText().toString().toCharArray();
        for(int i = 0; i < letters.length; i++){
            counter++;
            char k = letters[i];
            if(letters[i] == '@'){
                break;
            }
            if(Character.isLetter(k)){
                let++;
            }
        }
        String after = "";
        for(int i = counter; i < letters.length; i++){
            after += letters[i];
        }
        if(let>0 && counter != letters.length && (after.equals("gmail.com") && letters.length > 10) || (after.equals("mail.ru") && letters.length > 8)){
              if(passwordView.getText().toString().equals(passwordCheckView.getText().toString())){
                  return true;
              }else{
                  Toast.makeText(this,"password doesn't match",Toast.LENGTH_SHORT).show();
              }
        }else {
            Toast.makeText(this,"enter in correct form",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}