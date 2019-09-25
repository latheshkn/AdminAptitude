package com.example.adminaptitude;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CandidateRecyclerAdapter extends RecyclerView.Adapter<CandidateRecyclerAdapter.Vpadapter> {

ArrayList<ModelForCandidateList> list;

    public CandidateRecyclerAdapter(ArrayList<ModelForCandidateList> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Vpadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.candate_recycler_item,parent,false);

        return new Vpadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vpadapter holder, int position) {

        holder.name.setText(list.get(position).getName());
//        holder.mobile.setText(list.get(position).getMobile());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vpadapter extends RecyclerView.ViewHolder {

        TextView  name,mobile;

        public Vpadapter(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            mobile=itemView.findViewById(R.id.mobile);
        }
    }
}
