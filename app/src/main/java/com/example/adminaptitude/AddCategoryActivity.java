package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCategoryActivity extends AppCompatActivity {
    ArrayList<ModelClassForSpinner> countryItems;
    private SpinnerAdapter countryAdapter;
    Toolbar tool_add_Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        tool_add_Category=findViewById(R.id.tool_add_Category);

        setSupportActionBar(tool_add_Category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //now we have to create ArrayList and fill with items and we are creating in seprate method
        //call that method
        intiList();

        Spinner spinner = (Spinner)findViewById(R.id.spn_type);
        countryAdapter = new SpinnerAdapter(this,0,countryItems);
        spinner.setAdapter(countryAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinner clickedItem = (ModelClassForSpinner) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getName();
                Toast.makeText(AddCategoryActivity.this, clickedCountryName+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void intiList() {
        countryItems = new ArrayList<>();
        countryItems.add(new ModelClassForSpinner("India",R.drawable.andlogo));
        countryItems.add(new ModelClassForSpinner("Pakisthan",R.drawable.andlogo));
        countryItems.add(new ModelClassForSpinner("us",R.drawable.andlogo));

    }
    }

