package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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
import com.example.adminaptitude.ModelClass.ModelclassForSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectCategoryForAddQuestionActivity extends AppCompatActivity {

    ArrayList<ModelClassForSpinner> countryItems;
    ArrayList<ModelFroSubcategory> subtry;
    ArrayList<ModelClassForSpinnerSelectType> type;
    private SpinnerAdapter countryAdapter;
    private SpinnerAdapterSelectType selectAdapter;
    private  SpinnerAdapterForSet setadapter;
    ArrayList<ModelclassForSet> setlist;
    private  SppinerAdapterForSubCatery subCateryAdapter;
    Toolbar tool_add_Category;
    FirebaseDatabase firebaseDatabase ,fbrefcat;
    DatabaseReference databaseReference ,databaseReferencecat;

    TextView txt_display;
    EditText edt_category;
    Button btn_qtn, btn_retrieve,btn_addsub;
    int i;
    Tag msg;
    String n;
    static String a,typename,s,catget,catename,subcatname;
    Spinner spn_qtype,spn_qcategory,spn_qsubcategory,spn_qset;
    String clickedCountryName;
    ProgressBar progressbar_qn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category_for_add_question);

        btn_qtn=findViewById(R.id.btn_qtn);
        spn_qtype=findViewById(R.id.spn_qtype);
        progressbar_qn=findViewById(R.id.progressbar_qn);
        spn_qcategory=findViewById(R.id.spn_qcategory);
        spn_qsubcategory=findViewById(R.id.spn_qsubcategory);
        spn_qset=findViewById(R.id.spn_qset);



        gettype();
        getcategory();
        getsubCategory();




        setlist=new ArrayList<>();
        setlist.add(new ModelclassForSet("Set 1"));
        setlist.add(new ModelclassForSet("Set 2"));
        setlist.add(new ModelclassForSet("Set 3"));
        setlist.add(new ModelclassForSet("Set 4"));
        setlist.add(new ModelclassForSet("Set 5"));

        setadapter = new SpinnerAdapterForSet(SelectCategoryForAddQuestionActivity.this, 0, setlist);

        spn_qset.setAdapter(setadapter);

//        Toast.makeText(SelectCategoryForAddQuestionActivity.this, typename, Toast.LENGTH_SHORT).show();

        btn_qtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference().child("Q Set").child(typename).child(catename).child(subcatname);;



              databaseReference.push().child("set").setValue(s);

                Bundle bundle=new Bundle();

                bundle.putString("typename",typename);
                bundle.putString("category",catename);
                bundle.putString("subcatename",subcatname);
                bundle.putString("set",s);

                Intent intent=new Intent(SelectCategoryForAddQuestionActivity.this,EntterQuestionActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(SelectCategoryForAddQuestionActivity.this, subcatname, Toast.LENGTH_SHORT).show();


               //n=s;
            }
        });


 String f="Intermediate";


        SharedPreferences sharedPreferences=getSharedPreferences("type",MODE_PRIVATE);

        String gory=sharedPreferences.getString("Type","lathi");

        Toast.makeText(SelectCategoryForAddQuestionActivity.this, gory, Toast.LENGTH_SHORT).show();

        fbrefcat = FirebaseDatabase.getInstance();
        databaseReferencecat = fbrefcat.getReference().child(gory);


//        databaseReference.child("Category_1").getDatabase();

        databaseReferencecat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                countryItems = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    ModelClassForSpinner spinner_itemareaName = areaSnapshot.getValue(ModelClassForSpinner.class);
                    countryItems.add(spinner_itemareaName);


                }


                countryAdapter = new SpinnerAdapter(SelectCategoryForAddQuestionActivity.this, 0, countryItems);

                countryAdapter = new SpinnerAdapter(SelectCategoryForAddQuestionActivity.this, 0, countryItems);
                spn_qcategory.setAdapter(countryAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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


                selectAdapter = new SpinnerAdapterSelectType(SelectCategoryForAddQuestionActivity.this, 0, type);

                selectAdapter = new SpinnerAdapterSelectType(SelectCategoryForAddQuestionActivity.this, 0, type);
                spn_qtype.setAdapter(selectAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spn_qset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here we want country name when we click
                //so we need refrence of our clicked item


                ModelclassForSet set=(ModelclassForSet)parent.getItemAtPosition(position);

                   s=set.getSet();





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





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

                countryAdapter.setNotifyOnChange(true);
//               Toast.makeText(SelectCategoryForAddQuestionActivity.this, typename + " " + "Selected", Toast.LENGTH_SHORT).show();
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
                catename = clickedItem.getCategory_name();

                SharedPreferences cat=getSharedPreferences("Cat",MODE_PRIVATE);
                SharedPreferences.Editor editor=cat.edit();
                editor.putString("cat",catename);
                editor.apply();


//                Bundle bundle=new Bundle();
//
//
//                Intent intent=new Intent(SelectCategoryForAddQuestionActivity.this,EntterQuestionActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);

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
                subcatname = clickedItem.getSubCategory_name();

                SharedPreferences sharesub=getSharedPreferences("Sub",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharesub.edit();
                editor.putString("sype",subcatname);
                editor.apply();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Toast.makeText(SelectCategoryForAddQuestionActivity.this, typename + " " + "Selected", Toast.LENGTH_SHORT).show();

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


//                    Toast.makeText(SelectCategoryForAddQuestionActivity.this, c, Toast.LENGTH_SHORT).show();

                }


                subCateryAdapter=new SppinerAdapterForSubCatery(SelectCategoryForAddQuestionActivity.this,0,subtry);

                spn_qsubcategory.setAdapter(subCateryAdapter);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void getcategory(){





    }


     void getset(){



         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 databaseReference.push().setValue(s);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
     }

}
