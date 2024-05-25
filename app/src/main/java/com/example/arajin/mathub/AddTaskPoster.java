package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTaskPoster extends AppCompatActivity {
    FirebaseDatabase firebaseDatabaseTask ;
    DatabaseReference databaseReferenceTask;
    TextView t;
    Button create;
    long numberOfRoots;
    String previousSelectedValue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_poster);


        t = findViewById(R.id.here_test);
        Spinner spinnerLanguages=findViewById(R.id.spinner_languages);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);


        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = (String) parent.getItemAtPosition(position);
                if (!selectedValue.equals(previousSelectedValue)) {
                    firebaseDatabaseTask = FirebaseDatabase.getInstance();
                    databaseReferenceTask = firebaseDatabaseTask.getReference().child("task").child(spinnerLanguages.getSelectedItem().toString());
                    databaseReferenceTask.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            numberOfRoots = dataSnapshot.getChildrenCount() + 1;
                            t.setText("Test number " + numberOfRoots);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    previousSelectedValue = selectedValue;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        firebaseDatabaseTask = FirebaseDatabase.getInstance();
        databaseReferenceTask = firebaseDatabaseTask.getReference().child("task").child(spinnerLanguages.getSelectedItem().toString());
        databaseReferenceTask.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                numberOfRoots = dataSnapshot.getChildrenCount() + 1;
                t.setText("Test number " + numberOfRoots);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        create = findViewById(R.id.create_test);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext()  , AddNewTask.class);
                Bundle bundle = new Bundle();
                bundle.putString("num","1");
                bundle.putString("testNum",Long.toString(numberOfRoots));
                bundle.putString("school",spinnerLanguages.getSelectedItem().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}