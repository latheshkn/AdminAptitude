package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class QuestionListActivity extends AppCompatActivity {

    Toolbar tool_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        tool_question=findViewById(R.id.tool_question);

        setSupportActionBar(tool_question);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
