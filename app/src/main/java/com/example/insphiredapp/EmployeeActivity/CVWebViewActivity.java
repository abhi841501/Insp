package com.example.insphiredapp.EmployeeActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.insphiredapp.R;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CVWebViewActivity extends AppCompatActivity {
    WebView webViewCV;
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvweb_view);
        webViewCV = findViewById(R.id.webViewCV);

        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", MODE_PRIVATE);
        UserId= getUserIdData.getString("Id", "");
        Log.e("feedback", "change" + UserId);

        //String id = ""; // Your id value
        String stringToEncode = UserId != UserId ? UserId : "";

        byte[] data = stringToEncode.getBytes(StandardCharsets.UTF_8);
        String stringBase = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stringBase = Base64.getEncoder().encodeToString(data);
        }
        System.out.println(stringBase);
        String finalUrl =("https://itdevelopmentservices.com/insphire/cv_upload?user_id=\\(stringBase ?? \"");
        webViewCV.loadUrl(finalUrl);
    }


    }
