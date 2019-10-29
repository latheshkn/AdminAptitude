package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CandidateDetailActivity extends AppCompatActivity {

    String get_name;
    String get_mob;
    String get_em;
    String get_pss;
    String get_id;
    DatabaseReference mref;

    TextView name,mobile,id,email,pass;
    Toolbar tool_candidate_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_detail);
        mobile=findViewById(R.id.mobile);
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        tool_candidate_details=findViewById(R.id.tool_candidate_details);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setSupportActionBar(tool_candidate_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        get_name=getIntent().getExtras().getString("Key");
        get_mob=getIntent().getExtras().getString("Mob");
        get_em=getIntent().getExtras().getString("Email");
        get_id=getIntent().getExtras().getString("Id");
        get_pss=getIntent().getExtras().getString("Pass");

        Toast.makeText(this, get_name, Toast.LENGTH_SHORT).show();
        mref= FirebaseDatabase.getInstance().getReference().child("Cadidate").child(get_name);

        mref.child(get_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name1= (String) dataSnapshot.child("Name").getValue();
                String image= (String) dataSnapshot.child("image").getValue();
                String model= (String) dataSnapshot.child("mobile").getValue();
                String capacity= (String) dataSnapshot.child("capacity").getValue();
                String rent= (String) dataSnapshot.child("rent").getValue();

                name.setText(get_name);
                mobile.setText(get_mob);
                id.setText(get_id);
                email.setText(get_em);
                pass.setText(get_pss);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
