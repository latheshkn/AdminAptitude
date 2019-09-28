package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CandidateListActivity extends AppCompatActivity implements AddCandidateListActivity.Dialoglisener {

    RecyclerView recycler_candidate;
    LinearLayoutManager linearLayoutManager;
    FloatingActionButton floating_button;
    Toolbar tool_candidate_list;
    ArrayList<CandidateResponse> candidate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);
        recycler_candidate = findViewById(R.id.recycler_candidate);
        floating_button = findViewById(R.id.floating_button);
        tool_candidate_list = findViewById(R.id.tool_candidate_list);

        setSupportActionBar(tool_candidate_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });

        linearLayoutManager = new LinearLayoutManager(CandidateListActivity.this);
        recycler_candidate.setLayoutManager(linearLayoutManager);



        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Cadidate");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                candidate = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CandidateResponse upload = postSnapshot.getValue(CandidateResponse.class);
                    candidate.add(upload);
                }
                CandidateRecyclerAdapter candidateRecyclerAdapter = new CandidateRecyclerAdapter(candidate);
                recycler_candidate.removeAllViews();
                recycler_candidate.setAdapter(candidateRecyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void openDialog() {
        AddCandidateListActivity dialog = new AddCandidateListActivity();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void applytext(String username, String password) {
    }

}
