package com.example.adminaptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EntterQuestionActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Toolbar tool_enter_question;
    Button btn_addq;
    EditText edt_qtn,option_a,option_b,option_c,option_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entter_question);

        tool_enter_question=findViewById(R.id.tool_enter_question);

        btn_addq=findViewById(R.id.btn_addq);
        edt_qtn=findViewById(R.id.edt_qtn);
        option_a=findViewById(R.id.option_a);
        option_b=findViewById(R.id.option_b);
        option_c=findViewById(R.id.option_c);
        option_d=findViewById(R.id.option_d);
        setSupportActionBar(tool_enter_question);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();

        Bundle bundle=intent.getExtras();

        String a=bundle.getString("typename");
        String b=bundle.getString("category");
        String c=bundle.getString("subcatename");
        String d=bundle.getString("set");

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Question").child(a).child(b).child(c).child(d).child("questionList").push();
//



        btn_addq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String qtn=edt_qtn.getText().toString();

                final String value_a=option_a.getText().toString();
                final String value_b=option_b.getText().toString();
                final String value_c=option_c.getText().toString();
                final String value_d=option_d.getText().toString();
//                int a=1; firebaseDatabase=FirebaseDatabase.getInstance();
//                databaseReference=firebaseDatabase.getReference().child("Q Set").child(typename).child(catename).child(subcatname);;
//
//
//
//              databaseReference.push().child("set").setValue(s);
//
//                Bundle bundle=new Bundle();
//
//                bundle.putString("typename",typename);
//                bundle.putString("category",catename);
//                bundle.putString("subcatename",subcatname);
//                bundle.putString("set",s);

//                Intent intent=new Intent(SelectCategoryForAddQuestionActivity.this,EntterQuestionActivity.class);
//                intent.putExtras(bundle);
//                star
//                for(int i=1;i<=a;i++){
//
//                    a=i+a;
//
//
//
//

                        databaseReference.child("question").setValue(qtn);
                        databaseReference.child("option_a").setValue(value_a);
                        databaseReference.child("option_b").setValue(value_b);
                        databaseReference.child("option_c").setValue(value_c);
                        databaseReference.child("option_d").setValue(value_d);



            }
        });


        Toast.makeText(this, b, Toast.LENGTH_SHORT).show();
    }
}
