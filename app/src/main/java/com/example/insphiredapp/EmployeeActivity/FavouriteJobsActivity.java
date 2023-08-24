package com.example.insphiredapp.EmployeeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.insphiredapp.Adapter.FavJobAdapter;
import com.example.insphiredapp.Api_Model.Favourite_employee_model;
import com.example.insphiredapp.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteJobsActivity extends AppCompatActivity {

    RecyclerView recyclerFavJobs;
    ImageView backArrowFavJobs;
    List<Favourite_employee_model> favouriteemployeemodelList = new ArrayList<>();
    FavJobAdapter favJobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_jobs);

        recyclerFavJobs = findViewById(R.id.recyclerFavJobs);
        backArrowFavJobs = findViewById(R.id.backArrowFavJobs);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavouriteJobsActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerFavJobs.setLayoutManager(layoutManager);
        favJobAdapter = new FavJobAdapter(FavouriteJobsActivity.this, favouriteemployeemodelList);
        recyclerFavJobs.setAdapter(favJobAdapter);
        backArrowFavJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}