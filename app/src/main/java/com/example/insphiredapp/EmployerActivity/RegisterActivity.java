package com.example.insphiredapp.EmployerActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.insphiredapp.Api_Model.RegisterModel;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api;
import com.example.insphiredapp.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView SignInEmployeeReg,companyNametext;
    RadioButton employeeRegBtn,employerRegBtn;
    RelativeLayout relativeCompanyNamereg;
    RadioGroup radioGroupBtn;
    AppCompatButton regSignUpButton;
    EditText firstNameRegEmpee,lastNameRegEmpee,emailRegEmpee, editPasswordRegEmpee,editConfirmPasswordRegEmpee,phoneEditEmpee;
    ImageView regPasswordHidden,regPasswordShow,regConfirmPHidden,regConfirmPShow;
    LinearLayout linearRegisterEmployee;
    String strAdmin = "employer",userTypeReg;



    String strFirstName,strLastName,strEmail, strPassword, strPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inits();

        SignInEmployeeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        regPasswordHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regPasswordShow.setVisibility(View.VISIBLE);
                regPasswordHidden.setVisibility(View.GONE);
                editPasswordRegEmpee.setTransformationMethod(null);
                editPasswordRegEmpee.setSelection(editPasswordRegEmpee.getText().length());
            }
        });

        regPasswordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regPasswordHidden.setVisibility(View.VISIBLE);
                regPasswordShow.setVisibility(View.GONE);
                editPasswordRegEmpee.setTransformationMethod(new PasswordTransformationMethod());
                editPasswordRegEmpee.setSelection(editPasswordRegEmpee.getText().length());


            }
        });


        regConfirmPShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regConfirmPHidden.setVisibility(View.VISIBLE);
                regConfirmPShow.setVisibility(View.GONE);
                editConfirmPasswordRegEmpee.setTransformationMethod(new PasswordTransformationMethod());
                editConfirmPasswordRegEmpee.setSelection(editConfirmPasswordRegEmpee.getText().length());


            }
        });

        regConfirmPHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regConfirmPHidden.setVisibility(View.GONE);
                regConfirmPShow.setVisibility(View.VISIBLE);
                editConfirmPasswordRegEmpee.setTransformationMethod(null);
                editConfirmPasswordRegEmpee.setSelection(editConfirmPasswordRegEmpee.getText().length());
            }
        });


        radioGroupBtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (employerRegBtn.isChecked()) {

                    strAdmin = "employer";
                    Log.e("emplr", "onCheckedChanged:" + strAdmin  );

                }

                else if (employeeRegBtn.isChecked())
                {
                    strAdmin = "employee";
                    Log.e("emple", "onCheckedChanged:" + strAdmin  );

                }
            }
        });


        regSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                strFirstName = firstNameRegEmpee.getText().toString();
                strEmail = emailRegEmpee.getText().toString();
                strPassword = editPasswordRegEmpee.getText().toString();
                strPhone = phoneEditEmpee.getText().toString();
                if (validation())
                {
                 registerEmployee_Api();

                }
            }
        });

    }

    private boolean validation() {
        if (firstNameRegEmpee.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Please enter first name", Toast.LENGTH_SHORT).show();
            return false;

        }

        else if (emailRegEmpee.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (editPasswordRegEmpee.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Please enter  password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (editConfirmPasswordRegEmpee.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if (phoneEditEmpee.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!editPasswordRegEmpee.getText().toString().equals(editConfirmPasswordRegEmpee.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Password did not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void registerEmployee_Api() {


        final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
       // Call<RegisterModel> call = Api_Client1.getClient().REGISTER_MODEL_CALL(strFirstName,strLastName,strEmail,strPassword,strPhone);
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<RegisterModel> call = service.REGISTER_MODEL_CALL(strFirstName,strEmail,strPassword,strPhone,strAdmin);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                pd.dismiss();

                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {

                       String success = response.body().getSuccess();
                       String message = response.body().getMessage();
                        Log.e("Register", "Success" + success);

                        if (success.equals("true")|| success.equals("True")) {


                            if ( strAdmin.equals("employer"))

                            {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);


                            }

                            else if (strAdmin.equals("employee"));
                            {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                            Log.e("Register", "response" + success);
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                        } else {


                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                           // Toast.makeText(RegisterActivity.this,"This Email is Already Exist",Toast.LENGTH_SHORT).show();

                            Log.e("Checking", "Success");

                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(RegisterActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(RegisterActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(RegisterActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(RegisterActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(RegisterActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(RegisterActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(RegisterActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(RegisterActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(RegisterActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {

                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(RegisterActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(RegisterActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }

            }

        });
    }

    private void inits() {
        firstNameRegEmpee = findViewById(R.id.firstNameRegEmpee);
      //  lastNameRegEmpee = findViewById(R.id.lastNameRegEmpee);
        emailRegEmpee = findViewById(R.id.emailRegEmpee);
        phoneEditEmpee = findViewById(R.id.editProfilePhone);
        SignInEmployeeReg = findViewById(R.id.SignInEmployeeReg);
        radioGroupBtn = findViewById(R.id.radioGroupBtn);
        regSignUpButton = findViewById(R.id.regSignUpButton);
        employerRegBtn = findViewById(R.id.employerRegBtn);
       // companyNametext = findViewById(R.id.companyNametext);
        employeeRegBtn = findViewById(R.id.employeeRegBtn);
    //    relativeCompanyNamereg = findViewById(R.id.relativeCompanyNamereg);
        editPasswordRegEmpee = findViewById(R.id.editPasswordRegEmpee);
        editConfirmPasswordRegEmpee = findViewById(R.id.editConfirmPasswordRegEmpee);
        regPasswordHidden = findViewById(R.id.regPasswordHidden);
        regPasswordShow = findViewById(R.id.regPasswordShow);
        regConfirmPHidden = findViewById(R.id.regConfirmPHidden);
        regConfirmPShow = findViewById(R.id.regConfirmPShow);
        linearRegisterEmployee = findViewById(R.id.linearRegisterEmployee);

    }
}