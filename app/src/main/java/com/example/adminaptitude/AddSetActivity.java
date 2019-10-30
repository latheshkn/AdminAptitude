package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminaptitude.ModelClass.ModelClassForSpinner;
import com.example.adminaptitude.ModelClass.ModelClassForSpinnerSelectType;
import com.example.adminaptitude.ModelClass.ModelFroSubcategory;
import com.example.adminaptitude.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddSetActivity extends AppCompatActivity {

    ArrayList<ModelClassForSpinner> countryItems;
    ArrayList<ModelFroSubcategory> subtry;
    ArrayList<ModelClassForSpinnerSelectType> type;
    private SpinnerAdapter countryAdapter;
    private SpinnerAdapterSelectType selectAdapter;
    private  SppinerAdapterForSubCatery subCateryAdapter;
    Toolbar tool_add_Category;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView txt_display;
    Spinner spn_aset;
    Button btn_qtn, btn_retrieve,btn_addsub;
    int i;
    Tag msg;
    String a,typename,subname,cate;
    Spinner spn_qtype,spn_qcategory,spn_qsubcategory;
    String clickedCountryName;
    ProgressBar progressbar_qn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);

        btn_qtn=findViewById(R.id.btn_stn);
        spn_qtype=findViewById(R.id.spn_qtype);
        progressbar_qn=findViewById(R.id.progressbar_qn);
        spn_qcategory=findViewById(R.id.spn_qcategory);
        spn_qsubcategory=findViewById(R.id.spn_qsubcategory);
        spn_aset=findViewById(R.id.spn_aset);


        gettype();
        getcategory();
        getsubCategory();



        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("JAVA");
        arrayList.add("ANDROID");
        arrayList.add("C Language");
        arrayList.add("CPP Language");
        arrayList.add("Go Language");
        arrayList.add("AVN SYSTEMS");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_aset.setAdapter(arrayAdapter);

    }


    void gettype() {


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Type");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                type = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    ModelClassForSpinnerSelectType areaName = areaSnapshot.getValue(ModelClassForSpinnerSelectType.class);
                    type.add(areaName);

                    progressbar_qn.setVisibility(View.GONE);

                }


                selectAdapter = new SpinnerAdapterSelectType(AddSetActivity.this, 0, type);

                selectAdapter = new SpinnerAdapterSelectType(AddSetActivity.this, 0, type);
                spn_qtype.setAdapter(selectAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        btn_qtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                SharedPreferences share =getSharedPreferences("Sub",MODE_PRIVATE);
////                String b=share.getString("sub",subname);
//
//
////                String sub = spn_aset.getText().toString();
//                firebaseDatabase = FirebaseDatabase.getInstance();
//                databaseReference = firebaseDatabase.getReference().child("Set").child(typename) ;
//
//
//                databaseReference.child(cate).child(subname).push().child("set").setValue(sub);
//
//                startActivity(new Intent(AddSetActivity.this,AddSetActivity.class));
//
//            }
//        });class

        spn_qtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


//                Toast.makeText(SelectCategoryForAddQuestionActivity.this, typename + " " + "Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spn_qcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelClassForSpinner clickedItem = (ModelClassForSpinner) parent.getItemAtPosition(position);
                cate = clickedItem.getCategory_name();

                SharedPreferences cat=getSharedPreferences("Cat",MODE_PRIVATE);
                SharedPreferences.Editor editor=cat.edit();
                editor.putString("cat",cate);
                editor.apply();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spn_qsubcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item
                ModelFroSubcategory clickedItem = (ModelFroSubcategory) parent.getItemAtPosition(position);
                subname = clickedItem.getSubCategory_name();

                SharedPreferences sharesub=getSharedPreferences("Sub",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharesub.edit();
                editor.putString("sype",subname);
                editor.apply();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Toast.makeText(AddSetActivity.this, typename + " " + "Selected", Toast.LENGTH_SHORT).show();

    }




    void getsubCategory(){

        SharedPreferences category=getSharedPreferences("Cat",MODE_PRIVATE);
        String catget= category.getString("cat","hjhj");

        SharedPreferences sharedPreferences=getSharedPreferences("type",MODE_PRIVATE);
        String a=sharedPreferences.getString("Type","two");


        firebaseDatabase = FirebaseDatabase.getInstance();
        if (a==null){

            a= "Easy";
            Toast.makeText(this, a,Toast.LENGTH_SHORT).show();
        }

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
                    String c=item.getSubCategory_name();

                    Toast.makeText(AddSetActivity.this, c, Toast.LENGTH_SHORT).show();

                }


                subCateryAdapter=new SppinerAdapterForSubCatery(AddSetActivity.this,0,subtry);

                spn_qsubcategory.setAdapter(subCateryAdapter);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void getcategory(){


        SharedPreferences sharedPreferences=getSharedPreferences("type",MODE_PRIVATE);
        String a=sharedPreferences.getString("Type","two");


        firebaseDatabase = FirebaseDatabase.getInstance();
        if (a==null){

            a= "Easy";
            Toast.makeText(this, a,Toast.LENGTH_SHORT).show();
        }

        firebaseDatabase=FirebaseDatabase.getInstance();


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(a);



//        databaseReference.child("Category_1").getDatabase();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                countryItems = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    ModelClassForSpinner spinner_itemareaName = areaSnapshot.getValue(ModelClassForSpinner.class);
                    countryItems.add(spinner_itemareaName);


                }


                countryAdapter = new SpinnerAdapter(AddSetActivity.this, 0, countryItems);

                countryAdapter = new SpinnerAdapter(AddSetActivity.this, 0, countryItems);
                spn_qcategory.setAdapter(countryAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
