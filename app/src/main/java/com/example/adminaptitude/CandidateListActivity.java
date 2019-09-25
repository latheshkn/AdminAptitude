package com.example.adminaptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CandidateListActivity extends AppCompatActivity {

    RecyclerView recycler_candidate;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);
        recycler_candidate=findViewById(R.id.recycler_candidate);

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
}
