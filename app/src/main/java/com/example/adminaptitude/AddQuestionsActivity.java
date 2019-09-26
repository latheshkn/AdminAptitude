package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AddQuestionsActivity extends AppCompatActivity {

    Toolbar tool_add_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        tool_add_question=findViewById(R.id.tool_add_question);

        setSupportActionBar(tool_add_question);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
