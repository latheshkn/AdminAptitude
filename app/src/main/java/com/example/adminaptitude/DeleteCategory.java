package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adminaptitude.ModelClass.ModelClassForSpinner;
import com.example.adminaptitude.ModelClass.ModelClassForSpinnerSelectType;
import com.example.adminaptitude.ModelClass.ModelFroSubcategory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteCategory extends AppCompatActivity {

    public ArrayList<ModelClassForSpinner> countryItems;
    ArrayList<ModelClassForSpinnerSelectType> type;
    private SpinnerAdapter countryAdapter;
    private SpinnerAdapterSelectType selectAdapter;
    Spinner spn_type,spn_category,spn_subcategory;
    Button btn_subadd,btn_refresh;
    ArrayList<ModelFroSubcategory> subtry;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ModelClassForSpinner spinner_itemareaName;
    SppinerAdapterForSubCatery subCateryAdapter;
    public String a,subname,b,typename,category;
    ProgressBar progressbar;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);


        spn_type=findViewById(R.id.spn_type);

        spn_category=findViewById(R.id.spn_category);

        btn_subadd=findViewById(R.id.btn_subadd);

        spn_subcategory=findViewById(R.id.spn_subcategory);
        progressbar=findViewById(R.id.progressbar);




        gettype();
        getsubCategory();


        SharedPreferences sharedPreferences=getSharedPreferences("type",MODE_PRIVATE);
        a=sharedPreferences.getString("Type","two");


        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child(a);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                countryItems = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    spinner_itemareaName = areaSnapshot.getValue(ModelClassForSpinner.class);
                    countryItems.add(spinner_itemareaName);
                    progressbar.setVisibility(View.GONE);
                }


                countryAdapter = new SpinnerAdapter(DeleteCategory.this, 0, countryItems);
                countryAdapter = new SpinnerAdapter(DeleteCategory.this, 0, countryItems);
                spn_category.setAdapter(countryAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



        spn_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinnerSelectType clickedItem = (ModelClassForSpinnerSelectType) parent.getItemAtPosition(position);
                typename = clickedItem.getType_name();

                SharedPreferences sharedPreferences=getSharedPreferences("type",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Type",typename);
                editor.apply();
                Toast.makeText(DeleteCategory.this, typename + " " + "Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Toast.makeText(DeleteCategory.this, subname, Toast.LENGTH_SHORT).show();
        spn_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinner click = (ModelClassForSpinner) parent.getItemAtPosition(position);
                category = click.getCategory_name();

                SharedPreferences share=getSharedPreferences("Sub",MODE_PRIVATE);
                SharedPreferences.Editor editor=share.edit();
                editor.putString("sub",category);
                editor.commit();
                Toast.makeText(DeleteCategory.this, subname + " " + "Selected", Toast.LENGTH_SHORT).show();
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


                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("SubCategory").child(typename).child(b).child("SubCategory_name") ;


                databaseReference.removeValue();
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


                selectAdapter = new SpinnerAdapterSelectType(DeleteCategory.this, 0, type);

                selectAdapter = new SpinnerAdapterSelectType(DeleteCategory.this, 0, type);
                spn_type.setAdapter(selectAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    void getsubCategory(){

        SharedPreferences category=getSharedPreferences("Cat",MODE_PRIVATE);
        String catget= category.getString("cat","hjhj");

        SharedPreferences sharedPreferences=getSharedPreferences("type",MODE_PRIVATE);
        String a=sharedPreferences.getString("Type","two");



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("SubCategory").child(a).child(catget);



//        databaseReference.child("Category_1").getDatabase();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                subtry = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    ModelFroSubcategory item = areaSnapshot.getValue(ModelFroSubcategory.class);
                    subtry.add(item);
//             Toast.makeText(SelectCategoryForAddQuestionActivity.this, c, Toast.LENGTH_SHORT).show();

                }
                subCateryAdapter=new SppinerAdapterForSubCatery(DeleteCategory.this,0,subtry);

                spn_subcategory.setAdapter(subCateryAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
