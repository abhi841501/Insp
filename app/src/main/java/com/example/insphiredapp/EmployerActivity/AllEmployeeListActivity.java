package com.example.insphiredapp.EmployerActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insphiredapp.Adapter.EmployeeListAdapter;
import com.example.insphiredapp.Api_Model.AllEmployeeDataList;
import com.example.insphiredapp.Api_Model.AllEmployeeListModel;
import com.example.insphiredapp.Api_Model.FilterModel;
import com.example.insphiredapp.Api_Model.FilterModelListData;
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

public class AllEmployeeListActivity extends AppCompatActivity {
    ImageView arrowEmployeeList,filterImage,crossBtn;
    EditText catFilter,locFilter;
    RecyclerView recyclerEmployeeList;
    SwitchCompat switchbtnPerHoursFilter,switchbtnPerDayFilter;
   // List<AllEmployeeListModel> AllEmployeeDataList = new ArrayList<>();
    List<AllEmployeeDataList>allEmployeeDataLists = new ArrayList<>();
    List<FilterModelListData> filterModelListData = new ArrayList<>();
    EmployeeListAdapter employeeListAdapter;
    AppCompatButton applyBtnPopUp;
    SeekBar seekBar;
    TextView seekbarValue;
   private String strCat="1",StringBtnDay="0",user_id;
    private final String location = "India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_emploee_list);

        arrowEmployeeList = findViewById(R.id.arrowEmployeeList);
        recyclerEmployeeList = findViewById(R.id.recyclerEmployeeList);
        filterImage = findViewById(R.id.filterImage);

        arrowEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AllEmployeeListActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerEmployeeList.setLayoutManager(layoutManager);
        filterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog addDetailsFilter = new Dialog(AllEmployeeListActivity.this);
                addDetailsFilter.requestWindowFeature(Window.FEATURE_NO_TITLE);
                addDetailsFilter.setContentView(R.layout.filterpopup);
                 crossBtn = addDetailsFilter.findViewById(R.id.crossBtn);
                 catFilter = addDetailsFilter.findViewById(R.id.catFilter);
                 locFilter = addDetailsFilter.findViewById(R.id.locFilter);
                switchbtnPerDayFilter = addDetailsFilter.findViewById(R.id.switchbtnPerDayFilter);
                applyBtnPopUp = addDetailsFilter.findViewById(R.id.applyBtnPopUp);

                addDetailsFilter.show();
                Window window = addDetailsFilter.getWindow();
                if (window == null) return;
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = 950;
                params.height = 1700;
                window.setAttributes(params);
                //    dialog.getWindow().setLayout(100, 100);
                addDetailsFilter.getWindow().setBackgroundDrawableResource(R.drawable.popup_background);


                if(switchbtnPerDayFilter.isClickable());
                {

                    {
                        StringBtnDay = "1";
                    }
                }
                crossBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addDetailsFilter.dismiss();
                    }
                });
                seekBar = addDetailsFilter.findViewById(R.id.seekBar);
                seekBar.setProgress(0);
                seekBar.incrementProgressBy(10);
                seekBar.setMax(200);
                seekbarValue = addDetailsFilter.findViewById(R.id.seekbarValue);
                seekbarValue.setText(seekbarValue.getText().toString().trim());

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress = progress / 10;
                        progress = progress * 10;
                        seekbarValue.setText(String.valueOf(progress));
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                applyBtnPopUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validation())
                        {
                            FilterApi();
                        }

                    }
                });

                SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", MODE_PRIVATE);
                user_id= getUserIdData.getString("Id", "");
                Log.e("feedback", "change" + user_id);
            }
        });



       GetAllEmployeeList();
    }

    private boolean validation() {
        if (catFilter.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please provide category", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (locFilter.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please provide location", Toast.LENGTH_SHORT).show();
            return true;
        }


        return false;
    }

    private void  FilterApi() {

        final ProgressDialog pd = new ProgressDialog(AllEmployeeListActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<FilterModel> call = service.FILTER_MODEL_CALL(strCat,location,StringBtnDay,seekbarValue.getText().toString());

        call.enqueue(new Callback<FilterModel>() {
            @Override
            public void onResponse(Call<FilterModel> call, Response<FilterModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        FilterModel filterModel = response.body();
                        String success = filterModel.getSuccess();
                        String msg = filterModel.getMessage();
                        Log.e("hello", "success: " +success );

                        if (success.equals("true")|| (success.equals("True"))) {
                            filterModelListData = filterModel.getData();

                            Toast.makeText(AllEmployeeListActivity.this, msg, Toast.LENGTH_SHORT).show();

                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId();




                            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            // Calling another activity

                        } else {
                            Toast.makeText(AllEmployeeListActivity.this, msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(AllEmployeeListActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(AllEmployeeListActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(AllEmployeeListActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(AllEmployeeListActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(AllEmployeeListActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(AllEmployeeListActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(AllEmployeeListActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(AllEmployeeListActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(AllEmployeeListActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(AllEmployeeListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FilterModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(AllEmployeeListActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(AllEmployeeListActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }

    private void  GetAllEmployeeList() {

        final ProgressDialog pd = new ProgressDialog(AllEmployeeListActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<AllEmployeeListModel> call = service.ALL_EMPLOYEE_LIST_MODEL_CALL("all_user_data?user_id="+user_id);

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

                            employeeListAdapter = new EmployeeListAdapter(AllEmployeeListActivity.this, allEmployeeDataLists);
                            recyclerEmployeeList.setAdapter(employeeListAdapter);

                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId();


                            Toast.makeText(AllEmployeeListActivity.this, msg, Toast.LENGTH_SHORT).show();


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


    }
}