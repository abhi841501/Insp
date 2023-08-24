package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.insphiredapp.Api_Model.Favourite_employee_model;
import com.example.insphiredapp.R;

import java.util.List;

public class FavJobAdapter extends RecyclerView.Adapter<FavJobAdapter.ViewHolder> {
    Context context;
    List<Favourite_employee_model> favouriteemployeemodelList;

    public FavJobAdapter(Context context, List<Favourite_employee_model> favouriteemployeemodelList) {

        this.context = context;
        this.favouriteemployeemodelList = favouriteemployeemodelList;
    }

    @NonNull
    @Override
    public FavJobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerfavjobs,parent,Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavJobAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
