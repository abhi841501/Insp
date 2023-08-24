package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.insphiredapp.Api_Model.UpcomingJobModelData;
import com.example.insphiredapp.R;

import java.util.List;

public class MyAppointmentAdapter extends RecyclerView.Adapter<MyAppointmentAdapter.ViewHolder> {
    Context context;
    List<UpcomingJobModelData> upcomingJobModelDataList;

    public MyAppointmentAdapter(Context context, List<UpcomingJobModelData> upcomingJobModelDataList) {
        this.context = context;
        this.upcomingJobModelDataList = upcomingJobModelDataList;
    }

    @NonNull
    @Override
    public MyAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myappointments,parent,Boolean.parseBoolean("false"));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAppointmentAdapter.ViewHolder holder, int position) {
        holder.CNameFF.setText(upcomingJobModelDataList.get(position).getCompanyName());
        holder.CatNameFF.setText(upcomingJobModelDataList.get(position).getCatName());
        holder.startDateFF.setText(upcomingJobModelDataList.get(position).getStartDate());
        holder.endDateFF.setText(upcomingJobModelDataList.get(position).getEndDate());


    }

    @Override
    public int getItemCount() {
        return upcomingJobModelDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CNameFF,CatNameFF,DailyAmountFF,startDateFF,endDateFF;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CNameFF = itemView.findViewById(R.id.CNameFF);
            CatNameFF = itemView.findViewById(R.id.CatNameFF);
            DailyAmountFF = itemView.findViewById(R.id.DailyAmountFF);
            startDateFF = itemView.findViewById(R.id.startDateFF);
            endDateFF = itemView.findViewById(R.id.endDateFF);
        }
    }
}
