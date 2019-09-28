package com.example.adminaptitude;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CandidateRecyclerAdapter extends RecyclerView.Adapter<CandidateRecyclerAdapter.Vpadapter> {

    ArrayList<CandidateResponse> list;
    String a;
    String b;
    String c;
    String d;
    String p;

    public CandidateRecyclerAdapter(ArrayList<CandidateResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Vpadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candate_recycler_item, parent, false);

        return new Vpadapter(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Vpadapter holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.mobile.setText(list.get(position).getMobile());
//        holder.mobile.setText(list.get(position).getMobile());

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        a = list.get(position).getName();
        b = list.get(position).getUser_id();
        c = list.get(position).getMobile();
        d = list.get(position).getEmail();
        p = list.get(position).getPassword();

        Toast.makeText(holder.mobile.getContext(), a, Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vpadapter extends RecyclerView.ViewHolder {

        TextView name, mobile;

        public Vpadapter(@NonNull final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), CandidateDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Key", a);
                    bundle.putString("Id", b);
                    bundle.putString("Mob", c);
                    bundle.putString("Email", d);
                    bundle.putString("Pass", b);
                    intent.putExtras(bundle);
                    Toast.makeText(itemView.getContext(), a, Toast.LENGTH_SHORT).show();
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
