package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Api_Model.ChatCompanyModel;
import com.example.insphiredapp.Api_Model.ChatCompanyModelData;
import com.example.insphiredapp.EmployeeActivity.EmployeeMessageActivity;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api_Client;

import java.util.List;

public class ChatCompanyAdapter extends RecyclerView.Adapter<ChatCompanyAdapter.ViewHolder> {

    Context context;
    List<ChatCompanyModelData> chatCompanyModelList;
    public ChatCompanyAdapter(Context context, List<ChatCompanyModelData> chatCompanyModelList) {
        this.context = context;
        this.chatCompanyModelList = chatCompanyModelList;
    }

    @Override
    public ChatCompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.relative_chat_employee,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatCompanyAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(Api_Client.BASE_URL_IMAGES1 + chatCompanyModelList.get(position).getCompanyImage()).into(holder.companyImgcc);
        holder.cnameee.setText(chatCompanyModelList.get(position).getCompanyName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EmployeeMessageActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatCompanyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView companyImgcc;
        TextView cnameee;
        public ViewHolder(View itemView) {
            super(itemView);
            companyImgcc = itemView.findViewById(R.id.companyImgcc);
            cnameee = itemView.findViewById(R.id.cnameee);

        }
    }
}
