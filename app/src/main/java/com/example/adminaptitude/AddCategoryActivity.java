package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.M;

public class AddCategoryActivity extends AppCompatActivity {
    ArrayList<ModelClassForSpinner> countryItems;
    private SpinnerAdapter countryAdapter;
    Toolbar tool_add_Category;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView txt_display;
    EditText edt_category;
    Button btn_add, btn_retrieve;
    int i;
    Tag msg;
    String a;
        Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        tool_add_Category=findViewById(R.id.tool_add_Category);
        btn_add=findViewById(R.id.btn_add);
        edt_category=findViewById(R.id.edt_category);
//        txt_display=findViewById(R.id.txt_display);
//        btn_retrieve=findViewById(R.id.btn_retrieve);
                      spinner = (Spinner)findViewById(R.id.spn_type);

        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Category");


        databaseReference.child("Category_1").getDatabase();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                          countryItems=new ArrayList<>();

                    for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                                ModelClassForSpinner areaName =areaSnapshot.getValue(ModelClassForSpinner.class);
                                countryItems.add(areaName);


                     }



                             countryAdapter = new SpinnerAdapter(AddCategoryActivity.this,0,countryItems);
                           
                               countryAdapter = new SpinnerAdapter(AddCategoryActivity.this,0,countryItems);
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

                String a=edt_category.getText().toString();
                firebaseDatabase =FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference().child("Category");


                databaseReference.push().child("Category_name").setValue(a);
            }
        });

        setSupportActionBar(tool_add_Category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //now we have to create ArrayList and fill with items and we are creating in seprate method
        //call that method
//        intiList();
//




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinner clickedItem = (ModelClassForSpinner) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getCategory_name();
                Toast.makeText(AddCategoryActivity.this, clickedCountryName+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

