package com.example.arajin.mathub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProfileFragment extends Fragment {

    View view;
    TextView register;

    TextView gmailTextView;
    TextView passwordTextView;

    Button logOut;
    Button addTask;
    TextView logIn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile2, container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            view = inflater.inflate(R.layout.fragment_profile, container, false);
            // Your existing login UI setup goes here
            setupLoginUI();
        } else {
            // User is logged in
            view = inflater.inflate(R.layout.fragment_profile2, container, false);
            // Your existing logged-in UI setup goes here
            setupLoggedInUI();
        }

        return view;
    }

    private void setupLoginUI() {
        gmailTextView = view.findViewById(R.id.text5);
        passwordTextView = view.findViewById(R.id.text7);
        register = view.findViewById(R.id.textSign);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Register.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("counter", "2");
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });

        logIn = view.findViewById(R.id.click);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void setupLoggedInUI() {
        logOut = view.findViewById(R.id.log_out);
        addTask = view.findViewById(R.id.add_tasks);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                // Clear saved user data
                clearUserData();
                // Redirect to MainActivity or login screen
                // Adjust this intent based on your app's flow
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNewTask.class);
                startActivity(intent);
            }
        });

        // Check and show "Add Task" button based on user email
        showAddTaskButton();
    }

    private void loginUser() {
        String email = gmailTextView.getText().toString().trim();
        String password = passwordTextView.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        // Reload the fragment to show logged-in UI
                        getActivity().getSupportFragmentManager().beginTransaction().detach(ProfileFragment.this).attach(ProfileFragment.this).commit();
                    } else {
                        Toast.makeText(getContext(), "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearUserData() {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("accInfo.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void saveUserDataToFile() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String name = currentUser.getDisplayName();
            String email = currentUser.getEmail();

            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("userData.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write(name + "," + email);
                outputStreamWriter.close();
            } catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }
    }

    private void showAddTaskButton() {
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if (userEmail != null && userEmail.equals("anahitqishmiryan49@gmail.com")) {
            addTask.setVisibility(View.VISIBLE);
        } else {
            addTask.setVisibility(View.GONE);
        }
    }
}
