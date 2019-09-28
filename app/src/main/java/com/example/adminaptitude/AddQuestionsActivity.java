package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuestionsActivity extends AppCompatActivity {

    Toolbar tool_add_question;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        tool_add_question=findViewById(R.id.tool_add_question);
        btn_add=findViewById(R.id.btn_add);

        setSupportActionBar(tool_add_question);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddQuestionsActivity.this,EntterQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}
