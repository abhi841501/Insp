package com.example.insphiredapp.EmployerActivity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.insphiredapp.Api_Model.CreateJobModel;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api;
import com.example.insphiredapp.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateJobActivity extends AppCompatActivity {
    ImageView backArrowCJob;
    AppCompatButton createBtn;
    TextView startDateEmp,endDateEmp1;
            EditText hrText,drText,jobProfileEdit;
    private int year,month,day;
    LinearLayout linearStartTimeEmp,linearEndTimeEmp,linearHrEmp,linearDrEmp;

    RelativeLayout relativeCategory,relativeProfile;
    TextView jobDescriptionTxt;
    private String UserId,cat = "1",StrJobProfile,StrHourly,StrDailyRate,StrStartDate,StrEndDate,StrJobDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        backArrowCJob = findViewById(R.id.backArrowCJob);
        createBtn = findViewById(R.id.createBtn);
        startDateEmp = findViewById(R.id.startDateEmp);
        endDateEmp1 = findViewById(R.id.endDateEmp1);
        linearStartTimeEmp = findViewById(R.id.linearStartTimeEmp);
        linearEndTimeEmp = findViewById(R.id.linearEndTimeEmp);
        linearHrEmp = findViewById(R.id.linearHrEmp);
        linearDrEmp = findViewById(R.id.linearDrEmp);
        hrText = findViewById(R.id.hrText);
        drText = findViewById(R.id.drText);
        relativeCategory = findViewById(R.id.relativeCategory);
        relativeProfile = findViewById(R.id.relativeProfile);
        jobDescriptionTxt = findViewById(R.id.jobDescriptionTxt);
        jobProfileEdit = findViewById(R.id.jobProfileEdit);

        backArrowCJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        linearStartTimeEmp.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                Log.e("month","kkk.." +month);

                //calendar.add(Calendar.MONTH, 1);

                startDateEmp.setOnClickListener(view -> {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateJobActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month+ 1;

                            String formattedMonth = String.format("%02d", month);
                            String date = day + "-" + formattedMonth + "-" + year;

                            startDateEmp.setText(date);
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                });

            }
        });

        linearEndTimeEmp.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                Log.e("month","kkk.." +month);

                //calendar.add(Calendar.MONTH, 1);

                endDateEmp1.setOnClickListener(view -> {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateJobActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month+ 1;

                            String formattedMonth = String.format("%02d", month);
                            String date = day + "-" + formattedMonth + "-" + year;

                            endDateEmp1.setText(date);
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                });

            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrJobProfile = jobProfileEdit.getText().toString();
                StrHourly = hrText.getText().toString();
                StrDailyRate = drText.getText().toString();
                StrStartDate = startDateEmp.getText().toString();
                StrEndDate = endDateEmp1.getText().toString();
                StrJobDesc = jobDescriptionTxt.getText().toString();




                if (validation())
                {
                    createJobApi();
                }
                Toast.makeText(CreateJobActivity.this, "jobs created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        UserId = getUserIdData.getString("Id", "");
        Log.e("changePassword", "change" + UserId);


    }
    private void createJobApi() {

        final ProgressDialog pd = new ProgressDialog(CreateJobActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();

        Log.e("changePassword", "changePasswordApi: ");


        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<CreateJobModel> call = service.CREATE_JOB_MODEL_CALL(UserId,
                cat,
                StrJobProfile,
                StrJobDesc,
                StrHourly,
                StrDailyRate);

        call.enqueue(new Callback<CreateJobModel>() {
            @Override
            public void onResponse(Call<CreateJobModel> call, Response<CreateJobModel> response) {
                pd.dismiss();
                Log.e(
                        "changePassword", "loading");
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {

                        String success = response.body().getSuccess();
                        String message = response.body().getMessage();

                        Log.e("changePassword", "Success" + success);

                        if (success.equals("true")|| success.equals("True")) {

                            Log.e("changePassword", "response" + success);

                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                            Toast.makeText(CreateJobActivity.this, message, Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(CreateJobActivity.this, message, Toast.LENGTH_LONG).show();

                            Log.e("Checking", "Success");

                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(CreateJobActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(CreateJobActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(CreateJobActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(CreateJobActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(CreateJobActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(CreateJobActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(CreateJobActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(CreateJobActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(CreateJobActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(CreateJobActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CreateJobModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(CreateJobActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(CreateJobActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });
    }

    private boolean validation() {
        if (relativeCategory.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter category Id", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (jobProfileEdit.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter job title", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (hrText.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter hourly charge", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (drText.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter daily charge", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (startDateEmp.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter start date", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (endDateEmp1.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter end date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;

    }

}