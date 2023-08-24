package com.example.insphiredapp.EmployerActivity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.insphiredapp.R;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    private String UserId;
    private String employeeId,slotid,totalAmount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);
        employeeId = getIntent().getStringExtra("strEmpid");
        totalAmount = getIntent().getStringExtra("strAmount");
        UserId = getIntent().getStringExtra("UserId");
        slotid = getIntent().getStringExtra("SlotId");

      // String finalUrl ="http://192.168.1.17/insphire/card_payment?";


        String finalUrl ="https://itdevelopmentservices.com/insphire/card_payment?"+"employee_id="+employeeId+"&employer_id="+UserId+"&amount="+
                totalAmount+"&time_slot_id="+slotid;

        webView.loadUrl(finalUrl);


    }
}