package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AddCategoryActivity extends AppCompatActivity {

    Toolbar tool_add_Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        tool_add_Category=findViewById(R.id.tool_add_Category);

        setSupportActionBar(tool_add_Category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
