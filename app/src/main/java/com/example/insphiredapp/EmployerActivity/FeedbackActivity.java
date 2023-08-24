package com.example.insphiredapp.EmployerActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Api_Model.AllEmployeeDataList;
import com.example.insphiredapp.Api_Model.GiveRatingModel;
import com.example.insphiredapp.Api_Model.GiveRatingModelData;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api;
import com.example.insphiredapp.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {
    ImageView backArrowFeedback;
    AppCompatButton leaveFeedbackBtn;
    CircleImageView spareImageFeedBack;
    TextView nameFeedback,profileNameFeedback;
    RatingBar rating_barFeedback;
    EditText leaveCommentEdit;
    private String empId,strRatingBar,strComment,UserId,StrName,StrImg,StrCatName;
    List<AllEmployeeDataList> allEmployeeDataLists = new ArrayList<>();
    GiveRatingModelData giveRatingModelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        backArrowFeedback = findViewById(R.id.backArrowFeedback);
        leaveFeedbackBtn = findViewById(R.id.leaveFeedbackBtn);
        spareImageFeedBack = findViewById(R.id.spareImageFeedBack);
        nameFeedback = findViewById(R.id.nameFeedback);
        profileNameFeedback = findViewById(R.id.profileNameFeedback);
        rating_barFeedback = findViewById(R.id.rating_barFeedback);
        leaveCommentEdit = findViewById(R.id.leaveCommentEdit);

        empId = getIntent().getStringExtra("EmployeeIdFeedback");
        StrName = getIntent().getStringExtra("FirstName");
        StrCatName = getIntent().getStringExtra("CTName");
        StrImg = getIntent().getStringExtra("img");

        nameFeedback.setText(StrName);
        profileNameFeedback.setText(StrCatName);
        Glide.with(getApplicationContext()).load(Api_Client.BASE_URL_IMAGES+StrImg).placeholder(R.drawable.employee).into(spareImageFeedBack);



        backArrowFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        UserId = getUserIdData.getString("Id", "");
        Log.e("feedback", "change" + UserId);
      //  GetAllEmployeeListEmD();

        leaveFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strRatingBar = String.valueOf(rating_barFeedback.getRating());
                strComment = leaveCommentEdit.getText().toString();

               if(validation())
               {
                   GiveRatingApi();
               }
               finish();

            }
        });


    }

    private boolean validation() {
        if (leaveCommentEdit.getText().toString().equals(""))

        {
            Toast.makeText(getApplicationContext(), "Please provide your feedback", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

    private void  GiveRatingApi() {

        final ProgressDialog pd = new ProgressDialog(FeedbackActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<GiveRatingModel> call = service.GIVE_RATING_MODEL_CALL(UserId,empId,strRatingBar,strComment);

        call.enqueue(new Callback<GiveRatingModel>() {
            @Override
            public void onResponse(Call<GiveRatingModel> call, Response<GiveRatingModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        GiveRatingModel giveRatingModel = response.body();
                        String success = giveRatingModel.getSuccess();
                        String msg = giveRatingModel.getMessage();
                        Log.e("hello", "success: " +success );

                        if (success.equals("true")|| (success.equals("True"))) {
                            giveRatingModelData = response.body().getData();
                            Toast.makeText(FeedbackActivity.this, msg, Toast.LENGTH_SHORT).show();
                            leaveCommentEdit.setText("");
                           /* Intent intent = new Intent(FeedbackActivity.this,EmployerHistoryActivity.class);
                            startActivity(intent);*/

                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId();




                            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            // Calling another activity

                        } else {
                            Toast.makeText(FeedbackActivity.this, msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(FeedbackActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(FeedbackActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(FeedbackActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(FeedbackActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(FeedbackActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(FeedbackActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(FeedbackActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(FeedbackActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(FeedbackActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(FeedbackActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GiveRatingModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(FeedbackActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(FeedbackActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }

/*    private void  GetAllEmployeeListEmD() {

        final ProgressDialog pd = new ProgressDialog(FeedbackActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<AllEmployeeListModel> call = service.ALL_EMPLOYEE_LIST_MODEL_CALL();

        call.enqueue(new Callback<AllEmployeeListModel>() {
            @Override
            public void onResponse(Call<AllEmployeeListModel> call, Response<AllEmployeeListModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        String success = (response.body().getSuccess());
                        String msg = (response.body().getMessage());
                        Log.e("hello", "success: " +success );

                        if (success.equals("true")|| (success.equals("True"))) {
                            AllEmployeeListModel allEmployeeListModel = response.body();
                            allEmployeeDataLists = allEmployeeListModel.getData();

                            for (int i =0; i<allEmployeeDataLists.size(); i++)
                            {
                                CatIdd = String.valueOf(allEmployeeDataLists.get(i).getId());
                                Glide.with(getApplicationContext()).load(Api_Client.BASE_URL_IMAGES + allEmployeeDataLists.get(i).getEmpImage()).placeholder(R.drawable.ic_launcher_foreground).into(spareImageFeedBack);
                                strFname = allEmployeeDataLists.get(i).getFirstName();
                                strLName = allEmployeeDataLists.get(i).getLastName();
                                strData = (strFname +"  "+ strLName);
                                strempdDesig = allEmployeeDataLists.get(i).getCatName();
                            }

                            nameFeedback.setText(strData);
                            profileNameFeedback.setText(strempdDesig);

                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId();


                            Toast.makeText(FeedbackActivity.this, msg, Toast.LENGTH_SHORT).show();


                            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            // Calling another activity

                        } else {
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(getApplicationContext(), "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(getApplicationContext(), "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(getApplicationContext(), "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(getApplicationContext(), "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(getApplicationContext(), "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(getApplicationContext(), "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(getApplicationContext(), "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AllEmployeeListModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(getApplicationContext(), "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }*/
}