package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminaptitude.ModelClass.ModelClassForSpinner;
import com.example.adminaptitude.ModelClass.ModelClassForSpinnerSelectType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddCategoryActivity extends AppCompatActivity {
    ArrayList<ModelClassForSpinner> countryItems;
    ArrayList<ModelClassForSpinnerSelectType> type;
    private SpinnerAdapter countryAdapter;
    private SpinnerAdapterSelectType selectAdapter;
    Toolbar tool_add_Category;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView txt_display;
    EditText edt_category;
    Button btn_add, btn_retrieve,btn_addsub;
    int i;
    Tag msg;
    String a;
    Spinner spinner,spn_category;
    String clickedCountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        tool_add_Category = findViewById(R.id.tool_add_Category);
        btn_add = findViewById(R.id.btn_add);
        edt_category = findViewById(R.id.edt_category);
//        txt_display=findViewById(R.id.txt_display);
//        btn_retrieve=findViewById(R.id.btn_retrieve);
        spinner = (Spinner) findViewById(R.id.spn_type);
        btn_addsub = (Button) findViewById(R.id.btn_addsub);

        btn_addsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddCategoryActivity.this,AddSubCategoryActivity.class);
                startActivity(intent);
            }
        });
        spn_category = (Spinner) findViewById(R.id.spn_category);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Category");

        gettype();

//        databaseReference.child("Category_1").getDatabase();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                countryItems = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    ModelClassForSpinner spinner_itemareaName = areaSnapshot.getValue(ModelClassForSpinner.class);
                    countryItems.add(spinner_itemareaName);


                }


                countryAdapter = new SpinnerAdapter(AddCategoryActivity.this, 0, countryItems);

                countryAdapter = new SpinnerAdapter(AddCategoryActivity.this, 0, countryItems);
                spinner.setAdapter(countryAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String cat=dataSnapshot.getValue(String.class);
//                Toast.makeText(AddCategoryActivity.this, cat, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a = edt_category.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child(clickedCountryName) ;


                databaseReference.push().child("Category_name").setValue(a);
            }
        });

        setSupportActionBar(tool_add_Category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //now we have to create ArrayList and fill with items and we are creating in seprate method
        //call that method
//        intiList();
//


        spn_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinnerSelectType clickedItem = (ModelClassForSpinnerSelectType) parent.getItemAtPosition(position);
                clickedCountryName = clickedItem.getType_name();
                Toast.makeText(AddCategoryActivity.this, clickedCountryName + " " + "Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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


                selectAdapter = new SpinnerAdapterSelectType(AddCategoryActivity.this, 0, type);

                selectAdapter = new SpinnerAdapterSelectType(AddCategoryActivity.this, 0, type);
                spn_category.setAdapter(selectAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void intiList() {
//        countryItems = new ArrayList<>();
//
//
//                    countryItems.add(new ModelClassForSpinner("Lathesh",R.drawable.andlogo));
//
//                    countryItems.add(new ModelClassForSpinner(a,R.drawable.andlogo));
//
//    }
    }

