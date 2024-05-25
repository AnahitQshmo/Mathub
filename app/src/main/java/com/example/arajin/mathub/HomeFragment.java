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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView geometry;

    public HomeFragment() {
    }



    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void setContentView(int fragmentHome) {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView polytechTestTextView = view.findViewById(R.id.polytech_test);
        TextView mathLessonsTextView = view.findViewById(R.id.algebra);
        TextView aybTestTextView = view.findViewById(R.id.ayb_test);
        TextView physmathTestTextView = view.findViewById(R.id.physmath_test);
        TextView quantumTestTextView = view.findViewById(R.id.quantum_test);


        polytechTestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), RandomTest.class);
                Bundle bundle = new Bundle();
                bundle.putString("school","Polytech");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        physmathTestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), RandomTest.class);
                Bundle bundle = new Bundle();
                bundle.putString("school","Physmath");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        quantumTestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), RandomTest.class);
                Bundle bundle = new Bundle();
                bundle.putString("school","Quantum");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        aybTestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), RandomTest.class);
                Bundle bundle = new Bundle();
                bundle.putString("school","Ayb");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mathLessonsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AlgebraLessons.class);
                startActivity(intent);
            }
        });
        return view;
    }
}