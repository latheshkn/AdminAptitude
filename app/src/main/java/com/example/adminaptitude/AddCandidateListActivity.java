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

public class AddCandidateListActivity extends AppCompatDialogFragment {

    TextView txt_name,txt_userid;
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
                        String password =txt_userid.getText().toString();
                        dialoglisener.applytext(username,password);

                    }
                });
        txt_name = view.findViewById(R.id.txt_name);
        txt_userid = view.findViewById(R.id.txt_userid);
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
