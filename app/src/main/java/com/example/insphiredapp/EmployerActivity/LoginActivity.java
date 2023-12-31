package com.example.insphiredapp.EmployerActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.insphiredapp.Api_Model.LoginModel;
import com.example.insphiredapp.Api_Model.LoginModelData;
import com.example.insphiredapp.EmployeeActivity.DashboardActivityEmployee;
import com.example.insphiredapp.EmployeeActivity.EmployeeProfileActivity;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api;
import com.example.insphiredapp.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    TextView SignUpTxtEmployee, forgetPasswordEmployee;
    RadioButton employeeLogBtn, employerLogBtn;
    AppCompatButton loginBtnEmployee, loginBtnEmployer;
    ImageView hiddenPasswordLogin, showPasswordLogin;
    EditText loginEmployeePassword, loginEmployeeEmail;
    LinearLayout linearEmployeeLogin;
    String strEmail, strPassword, Device_token, Id, userType, strAdmin="employer", strDefault, strEmpyeeId,strEmpyrrID,strCheck = "0";
    CheckBox rememberCheck;
    String termCondition;


    RadioGroup radioGroupBtnLogin;

     LoginModelData loginModelData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inits();
        SignUpTxtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        radioGroupBtnLogin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (employerLogBtn.isChecked()) {

                    strAdmin = "employer";
                } else if (employeeLogBtn.isChecked()) {
                    strAdmin = "employee";
                }

            }
        });

        loginBtnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                strEmail = loginEmployeeEmail.getText().toString();
                strPassword = loginEmployeePassword.getText().toString();

                if (rememberCheck.isChecked())
                {
                    strCheck = "1";
                }

                if (validation()) {
                    loginEmployee_Api();

                }
            }
        });


        hiddenPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordLogin.setVisibility(View.VISIBLE);
                hiddenPasswordLogin.setVisibility(View.GONE);
                loginEmployeePassword.setTransformationMethod(null);
                loginEmployeePassword.setSelection(loginEmployeePassword.getText().length());
            }
        });

        showPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiddenPasswordLogin.setVisibility(View.VISIBLE);
                showPasswordLogin.setVisibility(View.GONE);
                loginEmployeePassword.setTransformationMethod(new PasswordTransformationMethod());
                loginEmployeePassword.setSelection(loginEmployeePassword.getText().length());


            }
        });

        forgetPasswordEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

       /* SharedPreferences sharedPreferences;
        sharedPreferences = LoginActivity.this.getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        userTypeRegister =sharedPreferences.getString("userTypeReg"," userTypeReg");
*/


    }


    private void loginEmployee_Api() {

        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<LoginModel> call = service.LOGIN_MODEL_CALL(strEmail, strPassword, "abcde",strAdmin);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(retrofit2.Call<LoginModel> call, retrofit2.Response<LoginModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        LoginModel loginModel = response.body();
                        String success = loginModel.getSuccess();
                        String message = loginModel.getMessage();

                        if (success.equals("true") || success.equals("True")) {
                            loginModelData = response.body().getData();
                            Id = String.valueOf(loginModelData.getId());
                            userType = loginModelData.getUserType();
                            Device_token = loginModelData.getDeviceToken();
                            termCondition = String.valueOf(loginModelData.getTermCondition());
                            Log.e("test", "Id" +strAdmin);
                            Toast.makeText(LoginActivity.this,Id,Toast.LENGTH_LONG).show();

                            SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = getUserIdData.edit();
                            editor.putString("Id", String.valueOf(Id));
                            editor.putString("userType", String.valueOf(userType));
                            Log.e("Login","provides types of user" +   userType );
                            editor.apply();
                            if (strAdmin.equalsIgnoreCase("employer"))

                            {
                                if (termCondition.equals("1"))
                                {
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                    intent.putExtra("strEmpyrrID",strEmpyrrID);
                                    intent.putExtra("userType",userType);
                                    intent.putExtra("strDefault",strDefault);
                                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }

                                else
                                {
                                    Intent intent = new Intent(LoginActivity.this, ProfileEmployerFirstActivity.class);
                                    startActivity(intent);
                                }


                            }

                             if (strAdmin.equals("employee"))
                            {
                                if (termCondition.equals("1"))
                                {
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivityEmployee.class);
                                    intent.putExtra("strEmpyeeId",strEmpyeeId);
                                    intent.putExtra("userType",userType);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this,Id,Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, EmployeeProfileActivity.class);
                                    startActivity(intent);
                                }


                            }


                        } else {
                           Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            pd.dismiss();

                            Log.e("user_id", "    False");
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Log.e("user_id", "    Message");
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

                            Log.e("user_id", "    Exception");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    Log.e("user_id", "    Exception  " + e.getMessage() + "  " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
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

    private boolean validation() {
        if (loginEmployeeEmail.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (loginEmployeePassword.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void inits() {
        SignUpTxtEmployee = findViewById(R.id.SignUpTxtEmployee);
        loginBtnEmployee = findViewById(R.id.loginBtnEmployee);
        radioGroupBtnLogin = findViewById(R.id.radioGroupBtnLogin);
        forgetPasswordEmployee = findViewById(R.id.forgetPasswordEmployee);
        employeeLogBtn = findViewById(R.id.employeeLogBtn);
        employerLogBtn = findViewById(R.id.employerLogBtn);
        hiddenPasswordLogin = findViewById(R.id.hiddenPasswordLogin);
        showPasswordLogin = findViewById(R.id.showPasswordLogin);
        loginEmployeePassword = findViewById(R.id.loginEmployeePassword);
        loginEmployeeEmail = findViewById(R.id.loginEmployeeEmail);
        rememberCheck = findViewById(R.id.rememberCheck);
        linearEmployeeLogin = findViewById(R.id.linearEmployeeLogin);

    }
}