package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adminaptitude.ModelClass.ModelClassForSpinner;
import com.example.adminaptitude.ModelClass.ModelClassForSpinnerSelectType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddSubCategoryActivity extends AppCompatActivity {
    public ArrayList<ModelClassForSpinner> countryItems;
    ArrayList<ModelClassForSpinnerSelectType> type;
    private SpinnerAdapter countryAdapter;
    private SpinnerAdapterSelectType selectAdapter;
    Spinner spn_type,spn_category,spn_subcategory;
    Button btn_subadd,btn_refresh;
    EditText edt_subcategory;
    public  String types = "Advance";
    public  String category = null;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ModelClassForSpinner spinner_itemareaName;

    public String a,subname,b,typename;
    ProgressBar progressbar;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category);

        spn_type=findViewById(R.id.spn_type);

        spn_category=findViewById(R.id.spn_subcategory);

        btn_subadd=findViewById(R.id.btn_subadd);

        edt_subcategory=findViewById(R.id.edt_subcategory);

        progressbar=findViewById(R.id.progressbar);

        btn_refresh=findViewById(R.id.btn_refresh);


        SharedPreferences sharedPreferences=getSharedPreferences("type",MODE_PRIVATE);
        a=sharedPreferences.getString("Type","two");



        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                startActivity(getIntent());

            }
        });


        gettype();



        spn_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinnerSelectType clickedItem = (ModelClassForSpinnerSelectType) parent.getItemAtPosition(position);
                types = clickedItem.getType_name();

                    spn_category.setAdapter(countryAdapter);


                Toast.makeText(AddSubCategoryActivity.this, types + " " + "Selected", Toast.LENGTH_SHORT).show();

                getSubCategory();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Toast.makeText(AddSubCategoryActivity.this, subname, Toast.LENGTH_SHORT).show();



        spn_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinner click = (ModelClassForSpinner) parent.getItemAtPosition(position);

               category = click.getCategory_name();

                Toast.makeText(AddSubCategoryActivity.this, category + " " + "Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_subadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences share =getSharedPreferences("Sub",MODE_PRIVATE);
                 String b=share.getString("sub",subname);


                String sub = edt_subcategory.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("SubCategory").child(typename) ;


                databaseReference.child(subname).child("SubCategory_name").setValue(sub);

               startActivity(new Intent(AddSubCategoryActivity.this,AddSetActivity.class));

            }
        });


    }


    void getSubCategory(){


        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child(types);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                countryItems = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    spinner_itemareaName = areaSnapshot.getValue(ModelClassForSpinner.class);
                    countryItems.add(spinner_itemareaName);
                    progressbar.setVisibility(View.GONE);


                }


                countryAdapter = new SpinnerAdapter(AddSubCategoryActivity.this,0, countryItems);
                countryAdapter = new SpinnerAdapter(AddSubCategoryActivity.this, 0, countryItems);
                spn_category.setAdapter(countryAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }




    void gettype() {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference data=firebaseDatabase.getReference().child("Type");

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                type = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                    ModelClassForSpinnerSelectType areaName = areaSnapshot.getValue(ModelClassForSpinnerSelectType.class);

                    type.add(areaName);


                }


                selectAdapter = new SpinnerAdapterSelectType(AddSubCategoryActivity.this, 0, type);

                selectAdapter = new SpinnerAdapterSelectType(AddSubCategoryActivity.this, 0, type);
                spn_type.setAdapter(selectAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
