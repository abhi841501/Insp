package com.example.insphiredapp.EmployeeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.insphiredapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeEditProfileActivity extends AppCompatActivity {
    ImageView backArroweep;
    CircleImageView userProfileeep;
    EditText editProfileFneep,editProfileEmaileep,editProfilePhoneeep,passwordeep,passwordeepConfirm;
    AppCompatButton editProfileUpBtneep,editCveep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_profile);

        backArroweep = findViewById(R.id.backArroweep);
        userProfileeep = findViewById(R.id.userProfileeep);
        editProfileFneep = findViewById(R.id.editProfileFneep);
        editProfileEmaileep = findViewById(R.id.editProfileEmaileep);
        editProfilePhoneeep = findViewById(R.id.editProfilePhoneeep);
        editCveep = findViewById(R.id.editCveep);
        passwordeep = findViewById(R.id.passwordeep);
        passwordeepConfirm = findViewById(R.id.passwordeepConfirm);
        editProfileUpBtneep = findViewById(R.id.editProfileUpBtneep);
        passwordeepConfirm = findViewById(R.id.passwordeepConfirm);


        backArroweep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}