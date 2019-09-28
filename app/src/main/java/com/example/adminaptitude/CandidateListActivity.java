package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CandidateListActivity extends AppCompatActivity implements AddCandidateListActivity.Dialoglisener{

    RecyclerView recycler_candidate;
    LinearLayoutManager linearLayoutManager;
    FloatingActionButton floating_button;
    Toolbar tool_candidate_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);
        recycler_candidate=findViewById(R.id.recycler_candidate);
        floating_button=findViewById(R.id.floating_button);
        tool_candidate_list=findViewById(R.id.tool_candidate_list);

        setSupportActionBar(tool_candidate_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });

        linearLayoutManager=new LinearLayoutManager(CandidateListActivity.this);

recycler_candidate.setLayoutManager(linearLayoutManager);
        ArrayList<ModelForCandidateList> obj= new ArrayList<>();

        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",8792));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));
        obj.add(new ModelForCandidateList("Lathesh",87921));

         CandidateRecyclerAdapter candidateRecyclerAdapter=new CandidateRecyclerAdapter(obj);

    recycler_candidate.setAdapter(candidateRecyclerAdapter);

    }





    public void openDialog() {

        AddCandidateListActivity dialog= new AddCandidateListActivity();
        dialog.show(getSupportFragmentManager(),"dialog");


    }

    @Override
    public void applytext(String username, String password) {


    }

}
