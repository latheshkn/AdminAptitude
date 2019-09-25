package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar tool_nav;
    LinearLayout linear_candidate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tool_nav=findViewById(R.id.tool_nav);

        setSupportActionBar(tool_nav);

        linear_candidate=findViewById(R.id.linear_candidate);

        linear_candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CandidateListActivity.class);
                startActivity(intent);
            }
        });

    }
}
