package com.example.insphiredapp.EmployerActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insphiredapp.Adapter.BookedEmployeeListAdapter;
import com.example.insphiredapp.Api_Model.EmployeeBookedListModel;
import com.example.insphiredapp.Api_Model.GetEmployeeBookedListModelData;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api;
import com.example.insphiredapp.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnGoingEmployeeActivity extends AppCompatActivity {
    RecyclerView recylerHiredList;
    ImageView arrowHired;
    List<GetEmployeeBookedListModelData> getEmployeeBookedListModelData = new ArrayList<>();
    BookedEmployeeListAdapter bookedEmployeeListAdapter;
    private String  user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going_employee);

        recylerHiredList = findViewById(R.id.recylerHiredList);
        arrowHired = findViewById(R.id.arrowHired);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OnGoingEmployeeActivity.this, LinearLayoutManager.VERTICAL, false);
        recylerHiredList.setLayoutManager(layoutManager);

        arrowHired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", MODE_PRIVATE);
        user_id= getUserIdData.getString("Id", "");
        Log.e("feedback", "change" + user_id);
        GetBookedEmployee();

    }

    private void  GetBookedEmployee() {

        final ProgressDialog pd = new ProgressDialog(OnGoingEmployeeActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<EmployeeBookedListModel> call = service.EMPLOYEE_BOOKED_LIST_MODEL_CALL("my_booked_employer?+user_id="+user_id);


        call.enqueue(new Callback<EmployeeBookedListModel>() {
            @Override
            public void onResponse(Call<EmployeeBookedListModel> call, Response<EmployeeBookedListModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        EmployeeBookedListModel employeeBookedListModel = response.body();
                        String success = employeeBookedListModel.getSuccess();
                        String msg =employeeBookedListModel.getMessage();
                        Log.e("hello", "success: " +success );


                        if (success.equals("true")|| (success.equals("True"))) {

                            String size  = String.valueOf(getEmployeeBookedListModelData.size());

                            Log.e("size", "size: " +size );
                            getEmployeeBookedListModelData=response.body().getData();
                           // getEmployeeBookedListModelData = employeeBookedListModel.getData();
                            bookedEmployeeListAdapter = new BookedEmployeeListAdapter(OnGoingEmployeeActivity.this, getEmployeeBookedListModelData);
                            recylerHiredList.setAdapter(bookedEmployeeListAdapter);

                            Log.e("hello", "getData: " + getEmployeeBookedListModelData );
                            // Id  = profileGetData.getId();


                            Toast.makeText(OnGoingEmployeeActivity.this, msg, Toast.LENGTH_SHORT).show();


                            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            // Calling another activity

                        } else {
                            Toast.makeText(OnGoingEmployeeActivity.this, msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(OnGoingEmployeeActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(OnGoingEmployeeActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(OnGoingEmployeeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<EmployeeBookedListModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(OnGoingEmployeeActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(OnGoingEmployeeActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }


}