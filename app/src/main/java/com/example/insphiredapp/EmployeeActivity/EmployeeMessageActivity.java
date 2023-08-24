package com.example.insphiredapp.EmployeeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.insphiredapp.Adapter.EmployeeListAdapter;
import com.example.insphiredapp.Adapter.EmployeeMsgAdapter;
import com.example.insphiredapp.Api_Model.AllEmployeeListModel;
import com.example.insphiredapp.Api_Model.MsgEmployee;
import com.example.insphiredapp.Api_Model.MsgEmployeeData;
import com.example.insphiredapp.Api_Model.UserChatModel;
import com.example.insphiredapp.Api_Model.UserChatModelData;
import com.example.insphiredapp.EmployerActivity.AllEmployeeListActivity;
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

public class EmployeeMessageActivity extends AppCompatActivity {
    ImageView backArrowME;
    AppCompatButton sendBtnEmployee;
    List<MsgEmployeeData> msgEmployeeDataList = new ArrayList<>();
    UserChatModelData userChatModelData;
    EmployeeMsgAdapter employeeMsgAdapter;
    RecyclerView recyclerSendMsg;
    EditText SendMsgEdittt;
    private String employer_id="2",employee_id="3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_message);

        backArrowME = findViewById(R.id.backArrowME);
        sendBtnEmployee = findViewById(R.id.sendBtnEmployee);
        recyclerSendMsg = findViewById(R.id.recyclerSendMsg);
        SendMsgEdittt = findViewById(R.id.SendMsgEdittt);

        backArrowME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendBtnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetMessagePostApi();
                SendMsgEdittt.setText("");

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EmployeeMessageActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerSendMsg.setLayoutManager(layoutManager);


        GetMessageList();



    }

    private void  GetMessagePostApi() {

        final ProgressDialog pd = new ProgressDialog(EmployeeMessageActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<UserChatModel> call = service.USER_CHAT_MODEL_CALL(employer_id,employee_id,SendMsgEdittt.getText().toString());

        call.enqueue(new Callback<UserChatModel>() {
            @Override
            public void onResponse(Call<UserChatModel> call, Response<UserChatModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        String success = (response.body().getSuccess());
                        String msg = (response.body().getMessage());
                        Log.e("hello", "success: " +success );

                        if (success.equals("true")|| (success.equals("True"))) {
                            UserChatModel userChatModel = response.body();
                            userChatModelData = userChatModel.getData();
                            GetMessageList();
                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId();




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
            public void onFailure(Call<UserChatModel> call, Throwable t) {
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


    }


    private void  GetMessageList() {

        final ProgressDialog pd = new ProgressDialog(EmployeeMessageActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<MsgEmployee> call = service.MSG_EMPLOYEE_CALL("get_chat?"+"employee_id="+3+"&employer_id="+2);

        call.enqueue(new Callback<MsgEmployee>() {
            @Override
            public void onResponse(Call<MsgEmployee> call, Response<MsgEmployee> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        String success = (response.body().getSuccess());
                        String msg = (response.body().getMessage());
                        Log.e("hello", "success: " +success );

                        if (success.equals("true")|| (success.equals("True"))) {
                            MsgEmployee msgEmployee = response.body();
                            msgEmployeeDataList = msgEmployee.getData();
                            employeeMsgAdapter = new EmployeeMsgAdapter(EmployeeMessageActivity.this,msgEmployeeDataList);
                            recyclerSendMsg.setAdapter(employeeMsgAdapter);
                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId();



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
            public void onFailure(Call<MsgEmployee> call, Throwable t) {
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


    }
}