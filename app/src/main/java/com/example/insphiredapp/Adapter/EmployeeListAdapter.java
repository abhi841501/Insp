package com.example.insphiredapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Api_Model.AllEmployeeDataList;
import com.example.insphiredapp.EmployerActivity.SelectSlotsActivity;
import com.example.insphiredapp.EmployerActivity.EmpDetailsActivity;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api_Client;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {
    Context context;
    List<AllEmployeeDataList> allEmployeeDataLists;
    String strFirstName, EmpId;

    public EmployeeListAdapter(Context context, List<AllEmployeeDataList> allEmployeeDataLists) {
        this.context = context;
        this.allEmployeeDataLists = allEmployeeDataLists;
    }

    @NonNull
    @Override
    public EmployeeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employeelistrecycler, viewGroup, Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(Api_Client.BASE_URL_IMAGES + allEmployeeDataLists.get(position).getEmpImage()).into(holder.circleImageAllEmpList);
        Log.e("images", " " + allEmployeeDataLists.get(position).getEmpImage());
        strFirstName = allEmployeeDataLists.get(position).getFirstName();
        holder.nameEmployeeee.setText(strFirstName);

        holder.desigNameEmployeeee.setText(allEmployeeDataLists.get(position).getCatName());
        holder.startDateAllEmployee.setText(allEmployeeDataLists.get(position).getStartDate());
        holder.endDateAllEmployee.setText(allEmployeeDataLists.get(position).getEndDate());
        holder.amountAllEmployee.setText(allEmployeeDataLists.get(position).getDailyRate());
        holder.addressAllEmployee.setText(allEmployeeDataLists.get(position).getAddress());


        holder.linearItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EmpDetailsActivity.class);
                String EmpDId = String.valueOf(allEmployeeDataLists.get(position).getId());
                String img = allEmployeeDataLists.get(position).getEmpImage();
                String strFirstName = allEmployeeDataLists.get(position).getFirstName();
                Float Rating = Float.valueOf(allEmployeeDataLists.get(position).getRating());
                intent.putExtra("BookingIdd", EmpDId);
                intent.putExtra("imgg", img);
                intent.putExtra("FullName", strFirstName);
                intent.putExtra("Designation", allEmployeeDataLists.get(position).getCatName());
                intent.putExtra("Rating", Rating);
                context.startActivity(intent);
            }
        });

        holder.BookBtnAllemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectSlotsActivity.class);
                EmpId = String.valueOf(allEmployeeDataLists.get(position).getId());
                intent.putExtra("EmpId",EmpId);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return allEmployeeDataLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageAllEmpList;
        TextView nameEmployeeee, desigNameEmployeeee, startDateAllEmployee, endDateAllEmployee, amountAllEmployee, addressAllEmployee;
        LinearLayout linearItemView;
        AppCompatButton BookBtnAllemp;

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageAllEmpList = itemView.findViewById(R.id.circleImageAllEmpList);
            nameEmployeeee = itemView.findViewById(R.id.nameEmployeeee);
            desigNameEmployeeee = itemView.findViewById(R.id.desigNameEmployeeee);
            startDateAllEmployee = itemView.findViewById(R.id.startDateAllEmployee);
            endDateAllEmployee = itemView.findViewById(R.id.endDateAllEmployee);
            amountAllEmployee = itemView.findViewById(R.id.amountAllEmployee);
            addressAllEmployee = itemView.findViewById(R.id.addressAllEmployee);
            linearItemView = itemView.findViewById(R.id.linearItemView);
            BookBtnAllemp = itemView.findViewById(R.id.BookBtnAllemp);


        }
    }


}
