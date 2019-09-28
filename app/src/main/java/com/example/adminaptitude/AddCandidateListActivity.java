package com.example.adminaptitude;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCandidateListActivity extends AppCompatDialogFragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView txt_name,txt_userid,mobile_no, email ,password;
Dialoglisener dialoglisener;
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_candidate_list, null);

        builder.setView(view)
                .setTitle("Add Candidate")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username =txt_name.getText().toString();
                        String useid =txt_userid.getText().toString();
                        String Mobile =mobile_no.getText().toString();
                        String email_id =email.getText().toString();
                        String secretno =password.getText().toString();
                        dialoglisener.applytext(username,secretno);

                        firebaseDatabase=FirebaseDatabase.getInstance();
                       databaseReference=firebaseDatabase.getReference().child("Cadidate").push();

                       databaseReference.child("Name").setValue(username);
                       databaseReference.child("User_Id").setValue(useid);
                       databaseReference.child("Mobile").setValue(Mobile);
                       databaseReference.child("Email").setValue(email_id);
                       databaseReference.child("Password").setValue(secretno);



                    }
                });
        txt_name = view.findViewById(R.id.txt_name);
        txt_userid = view.findViewById(R.id.txt_userid);
        mobile_no = view.findViewById(R.id.mobile_no);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialoglisener = (Dialoglisener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implemnt dialoglisener");
        }
    }
    public interface Dialoglisener

    {
        void applytext(String username, String password );
    }

}
