package com.example.insphiredapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Api_Model.EmployeeReviewModel;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api_Client;

import java.util.List;

public class ReviewsModelAdapter extends RecyclerView.Adapter<ReviewsModelAdapter.ViewHolder> {
    Context context;
    List<EmployeeReviewModel>employeeReviewModelList;

    public ReviewsModelAdapter(Context context, List<EmployeeReviewModel> employeeReviewModelList) {
        this.context = context;
        this.employeeReviewModelList = employeeReviewModelList;
    }

    @NonNull
    @Override
    public ReviewsModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycyclerreviews1,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsModelAdapter.ViewHolder holder, int position) {


        Glide.with(context).load(Api_Client.BASE_URL_IMAGES + employeeReviewModelList.get(position).getImage()).into(holder.companyImagesReview);
        Log.e("images", " " + employeeReviewModelList.get(position).getImage());
        holder.CompanyNameReviewM.setText((CharSequence) employeeReviewModelList.get(position).getCompanyName());
        holder.reviewMessageget.setText(employeeReviewModelList.get(position).getComment());



    }

    @Override
    public int getItemCount() {
        return employeeReviewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView companyImagesReview;
        TextView CompanyNameReviewM,reviewMessageget;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyImagesReview = itemView.findViewById(R.id.companyImagesReview);
            CompanyNameReviewM = itemView.findViewById(R.id.CompanyNameReviewM);
            reviewMessageget = itemView.findViewById(R.id.reviewMessageget);
        }



    }
}
