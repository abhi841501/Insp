package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Api_Model.MsgEmployee;
import com.example.insphiredapp.Api_Model.MsgEmployeeData;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api_Client;

import java.util.List;

public class EmployeeMsgAdapter extends RecyclerView.Adapter<EmployeeMsgAdapter.ViewHolder> {
    Context context;
    List<MsgEmployeeData> msgEmployeeDataList;

    public EmployeeMsgAdapter(Context context, List<MsgEmployeeData> msgEmployeeDataList) {
        this.context = context;
        this.msgEmployeeDataList = msgEmployeeDataList;
    }

    @NonNull
    @Override
    public EmployeeMsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_send_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeMsgAdapter.ViewHolder holder, int position) {
        holder.chatCMsg.setText(msgEmployeeDataList.get(position).getChatMessage());


    }

    @Override
    public int getItemCount() {
        return msgEmployeeDataList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView chatImggg;
        TextView chatCNamee,chatCMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chatCMsg = itemView.findViewById(R.id.chatCMsg);

        }
    }
}
