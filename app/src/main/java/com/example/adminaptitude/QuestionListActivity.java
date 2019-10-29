package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.adminaptitude.ModelClass.ModelClassForQuestionList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.adminaptitude.SelectCategoryForAddQuestionActivity.a;

public class QuestionListActivity extends AppCompatActivity {

    ArrayList<ModelClassForQuestionList> quest;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recycler_question;
    Toolbar tool_question;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        recycler_question=findViewById(R.id.recycler_question);

        linearLayoutManager = new LinearLayoutManager(QuestionListActivity.this);
        recycler_question.setLayoutManager(linearLayoutManager);


        Intent intent=getIntent();

        Bundle bundle=intent.getExtras();

//        String a=bundle.getString("typename");
//        String b=bundle.getString("category");
//        String c=bundle.getString("subcatename");
//        String d=bundle.getString("set");


        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference=firebaseDatabase.getReference("Question").child("Easy").child("Numerical").child("hhhhhh").child("Set 1").child("questionList");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quest = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ModelClassForQuestionList qlist = postSnapshot.getValue(ModelClassForQuestionList.class);
                    quest.add(qlist);

                    String a=qlist.getQuestion();

                    Toast.makeText(QuestionListActivity.this, a, Toast.LENGTH_SHORT).show();


                }
                RecyclerAdapterforQuestionList adapter  = new RecyclerAdapterforQuestionList(quest);
                recycler_question.setAdapter(adapter);


                Toast.makeText(QuestionListActivity.this, a, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
