package com.example.insphiredapp.EmployerActivity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.insphiredapp.R;

public class WebViewEmployerActivity extends AppCompatActivity {
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_employer);

        webView1 = findViewById(R.id.webView1);
        String finalUrl =("https://itdevelopmentservices.com/insphire/term_condition");
        webView1.loadUrl(finalUrl);

    }
    }