package com.example.arajin.mathub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchoolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView polytech;
    TextView physmath;
    TextView ayb;
    TextView quantum;

    public SchoolFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchoolFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchoolFragment newInstance(String param1, String param2) {
        SchoolFragment fragment = new SchoolFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school, container, false);
        polytech = view.findViewById(R.id.polytech);
        polytech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickB(1);
            }
        });
        physmath = view.findViewById(R.id.physmath);
        physmath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickB(2);
            }
        });
        ayb = view.findViewById(R.id.ayb);
        ayb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickB(2);
            }
        });
        quantum = view.findViewById(R.id.quantum);
        quantum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickB(2);
            }
        });

        return view;
    }
    public void onClickB(int i ){
        String a = "";
        switch (i){
            case 1:
                a = "polytech";
            case 2:
                a = "physmath";
            case 3:
                a = "ayb";
            case 4:
                a = "quantum";
            default:
                a= "quantum";

        }

        Intent intent = new Intent(getActivity(), HighSchool.class);
        Bundle bundle = new Bundle();
        bundle.putString("counter",a);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}