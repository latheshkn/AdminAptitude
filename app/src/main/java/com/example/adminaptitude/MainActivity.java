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
    LinearLayout linear_candidate,linear_report;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tool_nav=findViewById(R.id.tool_dash);
        drawerLayout=findViewById(R.id.nav_drawer);
        linear_report=findViewById(R.id.linear_report);

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
