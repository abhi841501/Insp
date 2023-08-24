package com.example.insphiredapp.EmployerFragment;

import static com.example.insphiredapp.R.color.skyBlue;
import static com.example.insphiredapp.R.color.white;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.insphiredapp.EmployeeActivity.ChatCompanyActivity;
import com.example.insphiredapp.EmployeeActivity.NotificationActivity;
import com.example.insphiredapp.EmployerActivity.AllEmployeeListActivity;
import com.example.insphiredapp.EmployerActivity.DashboardActivity;
import com.example.insphiredapp.EmployerActivity.EmployerHistoryActivity;
import com.example.insphiredapp.EmployerActivity.SettingsActivity;
import com.example.insphiredapp.EmployerActivity.OnGoingEmployeeActivity;
import com.example.insphiredapp.R;

public class HomeFragment extends Fragment {



    ImageView notificationEmployer,chatImgEmployer;
    LinearLayout linearOnGoingEmployee,linearPaymentsHome,linearSettings,linearEmployeeHistory;
    RelativeLayout relativeSearchEmployer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        linearOnGoingEmployee = view.findViewById(R.id.linearOnGoingEmployee);
        linearPaymentsHome = view.findViewById(R.id.linearPaymentsHome);
        linearSettings = view.findViewById(R.id.linearSettings);
        notificationEmployer = view.findViewById(R.id.notificationEmployer);
        linearEmployeeHistory = view.findViewById(R.id.linearEmployeeHistory);
        chatImgEmployer = view.findViewById(R.id.chatImgEmployer);
        relativeSearchEmployer = view.findViewById(R.id.relativeSearchEmployer);

        relativeSearchEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllEmployeeListActivity.class);
                startActivity(intent);
            }
        });

        notificationEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        chatImgEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ChatCompanyActivity.class);
                startActivity(intent);
            }
        });


        linearOnGoingEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OnGoingEmployeeActivity.class);
                startActivity(intent);
            }
        });
        linearEmployeeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployerHistoryActivity.class);
                startActivity(intent);
            }
        });
        linearPaymentsHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentsFragment paymentsFragment =new PaymentsFragment();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.container, paymentsFragment);
                fr.commit();
            }
        });



        linearSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);

            }
        });



       return view;
    }



}