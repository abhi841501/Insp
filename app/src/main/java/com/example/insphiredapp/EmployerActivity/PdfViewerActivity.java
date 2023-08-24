package com.example.insphiredapp.EmployerActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.insphiredapp.R;

public class PdfViewerActivity extends AppCompatActivity {
    WebView PdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

       String pdf = getIntent().getStringExtra("data");
        Log.e("http", ""+pdf );
         PdfView = findViewById(R.id.PdfView);

        PdfView.getSettings().setJavaScriptEnabled(true);
        String finalUrl = ("https://itdevelopmentservices.com/insphire/public/image/admin/employee/"+pdf);
        PdfView.loadUrl(finalUrl);
    }
}

