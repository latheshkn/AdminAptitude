package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class EntterQuestionActivity extends AppCompatActivity {

    Toolbar tool_enter_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entter_question);

        tool_enter_question=findViewById(R.id.tool_enter_question);

        setSupportActionBar(tool_enter_question);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
