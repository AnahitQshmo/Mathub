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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.temporal.Temporal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    View view;
    TextView register;

    TextView gmailTextView;
    TextView passwordTextView;

    Button logOut;
    TextView logIn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile2, container, false);
        String ret = "";

        try {
            InputStream inputStream = view.getContext().openFileInput("accInfo.txt");

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
        catch (Exception e) {
            Toast.makeText(view.getContext(),"File has mistake",Toast.LENGTH_SHORT).show();
            Log.e("login activity", e.toString());
        }
        Toast.makeText(view.getContext(),ret,Toast.LENGTH_SHORT).show();

       ////////////////////////////////////////

        if (ret.length() == 0){
            view = inflater.inflate(R.layout.fragment_profile, container, false);
            gmailTextView = view.findViewById(R.id.text5);
            passwordTextView = view.findViewById(R.id.text7);
            register = view.findViewById(R.id.textSign);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity() , Register.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("counter","2");
                    intent.putExtras(bundle2);
                    startActivity(intent);
                }
            });

            logIn = view.findViewById(R.id.click);
            logIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),MainActivity.class));
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String userId = snapshot.getKey(); // Assuming each user has a unique ID
                                String name = snapshot.child("name").getValue(String.class);
                                String gmail = snapshot.child("gmail").getValue(String.class);
                                String password = snapshot.child("password").getValue(String.class);
                                if(gmail.equals(gmailTextView.getText().toString()) && password.equals(passwordTextView.getText().toString())) {

                                    try {
                                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("accInfo.txt", Context.MODE_PRIVATE));
                                        outputStreamWriter.write(name+","+gmail+","+password+",");
                                        outputStreamWriter.close();
                                    }
                                    catch (IOException e) {
                                        Log.e("Exception", "File write failed: " + e.toString());
                                    }
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            });


        }else{
            view = inflater.inflate(R.layout.fragment_profile2, container, false);
            logOut = view.findViewById(R.id.log_out);
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput("accInfo.txt", Context.MODE_PRIVATE));
                        outputStreamWriter.write("");
                        outputStreamWriter.close();
                    }
                    catch (IOException e) {
                        Log.e("Exception", "File write failed: " + e.toString());
                    }
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("counter","2");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        return view;
    }
}