package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Api_Model.CancelEmployeeHistoryList;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api_Client;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class CancelHistoryAdapter extends RecyclerView.Adapter<CancelHistoryAdapter.ViewHolder> {
    Context context;
    List<CancelEmployeeHistoryList> cancelEmployeeHistoryList;

    public CancelHistoryAdapter(Context context, List<CancelEmployeeHistoryList> cancelEmployeeHistoryList) {
        this.context = context;
        this.cancelEmployeeHistoryList = cancelEmployeeHistoryList;
    }

    @Override
    public CancelHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_cancel_adapter,parent,Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CancelHistoryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(Api_Client.BASE_URL_IMAGES + cancelEmployeeHistoryList.get(position).getImage()).into(holder.imgCancel);
        holder.catNameCancel.setText(cancelEmployeeHistoryList.get(position).getCatName());
        holder.companyNameCancel.setText(cancelEmployeeHistoryList.get(position).getCompanyName());
        holder.startDateCancel.setText(cancelEmployeeHistoryList.get(position).getStartDate());
        holder.endDateCancel.setText(cancelEmployeeHistoryList.get(position).getEndDate());
        holder.DailyAmountCancel.setText(cancelEmployeeHistoryList.get(position).getAmount());
        holder.AddressCancel.setText(cancelEmployeeHistoryList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return cancelEmployeeHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCancel;
        TextView catNameCancel, companyNameCancel,startDateCancel,endDateCancel,DailyAmountCancel,AddressCancel,StatusCancel,paymentCancel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCancel = itemView.findViewById(R.id.imgCancel);
            catNameCancel = itemView.findViewById(R.id.catNameCancel);
            companyNameCancel = itemView.findViewById(R.id.companyNameCancel);
            startDateCancel = itemView.findViewById(R.id.startDateCancel);
            endDateCancel = itemView.findViewById(R.id.endDateCancel);
            DailyAmountCancel = itemView.findViewById(R.id.DailyAmountCancel);
            AddressCancel = itemView.findViewById(R.id.AddressCancel);
            StatusCancel = itemView.findViewById(R.id.StatusCancel);
            paymentCancel = itemView.findViewById(R.id.paymentCancel);
        }
    }

}
