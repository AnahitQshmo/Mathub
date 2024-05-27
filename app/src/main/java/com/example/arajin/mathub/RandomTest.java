package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class RandomTest extends AppCompatActivity {
    TextView textView;
    Button button;
    FirebaseDatabase firebaseDatabaseTask;
    DatabaseReference databaseReferenceTask;
    String school;
    int randomNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_test);

        Bundle bundle = getIntent().getExtras();
        school = bundle.getString("school");
        button = findViewById(R.id.go_button);
        button.setVisibility(View.VISIBLE);
        textView = findViewById(R.id.test_num);

        databaseFindFunc();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tests.class);
                Bundle bundle = new Bundle();
                bundle.putString("school",school);
                bundle.putString("testNumber",Integer.toString(randomNumber));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




    }
    public void databaseFunc(char[] array){

        firebaseDatabaseTask = FirebaseDatabase.getInstance();
        databaseReferenceTask = firebaseDatabaseTask.getReference().child("task").child(school);
        databaseReferenceTask.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String message = "";
                int numberOfRoots = (int)dataSnapshot.getChildrenCount();
                if(numberOfRoots == array.length){
                    message = getResources().getString(R.string.alldone);
                    button.setVisibility(View.GONE);
                }else{
                    randomNumber = randomNum(numberOfRoots,array);
                    message ="Test number " + randomNumber;
                }
                textView.setText(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }
    public void databaseFindFunc(){
        firebaseDatabaseTask = FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = firebaseDatabaseTask.getReference().child("users")
                .child(firebaseUser.getUid().toString()).child("answer")
                .child(school);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String test = "";
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String key = childSnapshot.getKey();
                        test = test + key;

                }
                char[] arr = test.toCharArray();
                databaseFunc(arr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public int randomNum(int numOfRoots,char[] array){
        Random rand = new Random();
        int randomNum = 0;
        if(numOfRoots - array.length == 1){
            for(int i = 1 ; i<=numOfRoots;i++){
                try{
                    if(i != Integer.parseInt(Character.toString(array[i-1]))){
                        randomNum = i;
                        break;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    randomNum = i;
                    break;
                }

            }
        }else{
            randomNum = rand.nextInt((int)numOfRoots) + 1;
            for (char i: array) {
                if(Integer.parseInt(Character.toString(i)) == randomNum){
                    randomNum(randomNumber,array);
                }
            }

        }
        return randomNum;
    }
}
