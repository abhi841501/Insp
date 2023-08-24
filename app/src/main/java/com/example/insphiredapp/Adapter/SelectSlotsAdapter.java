package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.insphiredapp.Api_Model.GetCreateSlotsModelData;
import com.example.insphiredapp.R;

import java.util.List;

public class SelectSlotsAdapter extends RecyclerView.Adapter<SelectSlotsAdapter.ViewHolder> {
    Context context;
    List<GetCreateSlotsModelData> getCreateSlotsModelDataList;
    public SelectSlotsAdapter(Context context, List<GetCreateSlotsModelData> getCreateSlotsModelDataList) {
        this.context = context;
        this.getCreateSlotsModelDataList = getCreateSlotsModelDataList;
    }

    @NonNull
    @Override
    public SelectSlotsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.recyclerselectslots,viewGroup,Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSlotsAdapter.ViewHolder holder, int position) {
        holder.StartDateSelectSlots.setText(getCreateSlotsModelDataList.get(position).getStartDate());
        holder.EndDateSelectSlots.setText(getCreateSlotsModelDataList.get(position).getEndDate());
        holder.startTimeSelectSlot.setText(getCreateSlotsModelDataList.get(position).getStartTime());
        holder.endTimeSelectSlot.setText(getCreateSlotsModelDataList.get(position).getEndTime());


    }

    @Override
    public int getItemCount() {
        return getCreateSlotsModelDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView StartDateSelectSlots,EndDateSelectSlots,startTimeSelectSlot,endTimeSelectSlot;
        RadioGroup radioGroupBtnSlots;
        RadioButton SelectSlotRadioBtn;
        public ViewHolder(View itemView) {
            super(itemView);

            StartDateSelectSlots = itemView.findViewById(R.id.StartDateSelectSlots);
            EndDateSelectSlots = itemView.findViewById(R.id.EndDateSelectSlots);
            startTimeSelectSlot = itemView.findViewById(R.id.startTimeSelectSlot);
            endTimeSelectSlot = itemView.findViewById(R.id.endTimeSelectSlot);
            radioGroupBtnSlots = itemView.findViewById(R.id.radioGroupBtnSlots);
            SelectSlotRadioBtn = itemView.findViewById(R.id.SelectSlotRadioBtn);


        }
    }
}
