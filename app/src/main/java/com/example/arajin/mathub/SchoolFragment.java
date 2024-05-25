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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView polytech;
    TextView physmath;
    TextView ayb;
    TextView quantum;

    public SchoolFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchoolFragment.
     */
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
        quantum = view.findViewById(R.id.quantum);
        quantum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickB(3);
            }
        });
        ayb = view.findViewById(R.id.ayb);
        ayb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickB(4);
            }
        });

        return view;
    }
    public void onClickB(int i ){
        String a = "";
        if(i == 1){
            a = "polytech";
        } else if (i == 2) {
            a = "physmath";
        } else if (i == 3) {
            a = "quantum";
        }else{
            a = "ayb";
        }

        Intent intent = new Intent(getActivity(), HighSchool.class);
        Bundle bundle = new Bundle();
        bundle.putString("counter",a);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}