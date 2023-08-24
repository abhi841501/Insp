package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Api_Model.MyJobModelData;
import com.example.insphiredapp.EmployeeActivity.JobDiscriptionActivity;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api_Client;

import java.util.List;

public class MyJobAdapter extends RecyclerView.Adapter<MyJobAdapter.ViewHolder> {
    Context context;
    List<MyJobModelData> myJobModelDataList;

    public MyJobAdapter(Context context, List<MyJobModelData> myJobModelDataList) {
        this.context = context;
        this.myJobModelDataList = myJobModelDataList;
    }

    @NonNull
    @Override
    public MyJobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.recyclermyjob,parent,Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyJobAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(Api_Client.BASE_URL_IMAGES1+myJobModelDataList.get(position).getCompanyImage()).into(holder.imgMyjobss);
        Log.e("images", " " + myJobModelDataList.get(position).getCompanyImage());
        holder.nameMyjobss.setText(myJobModelDataList.get(position).getCatName());
        holder.profileNamejobss.setText(myJobModelDataList.get(position).getCompanyName());
        holder.joiningDateHJobss.setText(myJobModelDataList.get(position).getJoiningDate());
        holder.endDateHJobss.setText(myJobModelDataList.get(position).getEndDate());
        holder.amountMyjobss.setText(myJobModelDataList.get(position).getDailyRate());
        holder.locationJobss.setText(myJobModelDataList.get(position).getCompanyAddress());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,JobDiscriptionActivity.class);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return myJobModelDataList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder {
        LinearLayout linearFindJobs;
        ImageView imgMyjobss;
        TextView nameMyjobss,profileNamejobss,joiningDateHJobss,endDateHJobss,amountMyjobss,locationJobss,statusMyJobss,paymentMyJobss;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMyjobss = itemView.findViewById(R.id.imgMyjobss);
            nameMyjobss = itemView.findViewById(R.id.nameMyjobss);
            profileNamejobss = itemView.findViewById(R.id.profileNamejobss);
            joiningDateHJobss = itemView.findViewById(R.id.joiningDateHJobss);
            endDateHJobss = itemView.findViewById(R.id.endDateHJobss);
            amountMyjobss = itemView.findViewById(R.id.amountMyjobss);
            locationJobss = itemView.findViewById(R.id.locationJobss);
            statusMyJobss = itemView.findViewById(R.id.statusMyJobss);
            paymentMyJobss = itemView.findViewById(R.id.paymentMyJobss);

        }
    }
}
