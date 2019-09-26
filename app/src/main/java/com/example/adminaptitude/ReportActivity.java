package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class ReportActivity extends AppCompatActivity {

    Toolbar tool_dash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        tool_dash=findViewById(R.id.tool_dash);

        setSupportActionBar(tool_dash);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
