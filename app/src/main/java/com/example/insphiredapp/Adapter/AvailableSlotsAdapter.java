package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insphiredapp.Api_Model.GetCreateSlotsModelData;
import com.example.insphiredapp.R;

import java.util.List;

public class AvailableSlotsAdapter extends RecyclerView.Adapter<AvailableSlotsAdapter.AvailHolder> {
    Context context;
    List<GetCreateSlotsModelData> getCreateSlotsModelDataList;

    public AvailableSlotsAdapter(Context context, List<GetCreateSlotsModelData> getCreateSlotsModelDataList) {
        this.context = context;
        this.getCreateSlotsModelDataList = getCreateSlotsModelDataList;
    }

    @Override
    public AvailableSlotsAdapter.AvailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.recycleremployeeslots,parent,Boolean.parseBoolean("false"));
        return new  AvailHolder(view);
    }

    @Override
    public void onBindViewHolder(AvailableSlotsAdapter.AvailHolder holder, int position) {
        holder.StartDateAvailSlots.setText(getCreateSlotsModelDataList.get(position).getStartDate());
        holder.EndDateAvailSlots.setText(getCreateSlotsModelDataList.get(position).getEndDate());
        holder.startTimeAvailSlot.setText(getCreateSlotsModelDataList.get(position).getStartTime());
        holder.endTimeAvailSlot.setText(getCreateSlotsModelDataList.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return getCreateSlotsModelDataList.size();
    }

    public class AvailHolder extends RecyclerView.ViewHolder {
        TextView StartDateAvailSlots,EndDateAvailSlots,startTimeAvailSlot,endTimeAvailSlot;
        ImageView deleteAvailSlots;
        AppCompatButton BookBtnSlotsAvail;
        public AvailHolder(View itemView) {
            super(itemView);

            StartDateAvailSlots = itemView.findViewById(R.id.StartDateAvailSlots);
            EndDateAvailSlots = itemView.findViewById(R.id.EndDateAvailSlots);
            startTimeAvailSlot = itemView.findViewById(R.id.startTimeAvailSlot);
            endTimeAvailSlot = itemView.findViewById(R.id.endTimeAvailSlot);
            deleteAvailSlots = itemView.findViewById(R.id.deleteAvailSlots);


        }
    }


}
