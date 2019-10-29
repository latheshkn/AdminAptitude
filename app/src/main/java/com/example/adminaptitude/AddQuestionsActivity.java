package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.adminaptitude.ModelClass.ModelClassForSpinner;
import com.example.adminaptitude.ModelClass.ModelClassForSpinnerSelectType;
import com.example.adminaptitude.ModelClass.ModelclassForSet;

import java.util.ArrayList;

public class AddQuestionsActivity extends AppCompatActivity {

    ArrayList<ModelClassForSpinnerSelectType> set;
    Toolbar tool_add_question;
    Button btn_add;
    Spinner snr_set;
    SpinnerAdapterSelectType adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        tool_add_question=findViewById(R.id.tool_add_question);
        btn_add=findViewById(R.id.btn_add);
        snr_set=findViewById(R.id.snr_set);

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
