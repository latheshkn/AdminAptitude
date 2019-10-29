package com.example.adminaptitude;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Toolbar tool_nav;
    LinearLayout linear_candidate,linear_report,linear_questionlist,linear_addquestion
            ,linear_delete,linear_add_category;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tool_nav=findViewById(R.id.tool_dash);
        drawerLayout=findViewById(R.id.nav_drawer);
        linear_report=findViewById(R.id.linear_report);
        linear_questionlist=findViewById(R.id.linear_questionlist);
        linear_addquestion=findViewById(R.id.linear_addquestion);
        linear_delete=findViewById(R.id.linear_delete);
        linear_add_category=findViewById(R.id.linear_add_category);

        linear_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddCategoryActivity.class);
                startActivity(intent);
            }
        });


        linear_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DeleteCategory.class);
                startActivity(intent);
            }
        });

        linear_addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SelectCategoryForAddQuestionActivity.class);
                startActivity(intent);
            }
        });

        linear_questionlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,QuestionListActivity.class);
                startActivity(intent);
            }
        });

        linear_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });

        setSupportActionBar(tool_nav);

        linear_candidate=findViewById(R.id.linear_candidate);

        linear_candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CandidateListActivity.class);
                startActivity(intent);
            }
        });



        ActionBarDrawerToggle  toggle=new ActionBarDrawerToggle(this,drawerLayout,tool_nav,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}
