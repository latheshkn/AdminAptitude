package com.example.adminaptitude;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminaptitude.ModelClass.ModelClassForQuestionList;

import java.util.ArrayList;

public class RecyclerAdapterforQuestionList extends RecyclerView.Adapter<RecyclerAdapterforQuestionList.Vholder> {

    ArrayList<ModelClassForQuestionList> list;

    public RecyclerAdapterforQuestionList(ArrayList<ModelClassForQuestionList> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_for_question, parent, false);

        return new Vholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {

        holder.txt_question.setText(list.get(position).getQuestion());
        holder.mobile.setText(list.get(position).getOption_a());
        holder.optionb.setText(list.get(position).getOption_b());
        holder.optionc.setText(list.get(position).getOption_c());
        holder.optiond.setText(list.get(position).getOption_d());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vholder extends RecyclerView.ViewHolder{

        TextView txt_question, mobile,optionb,optionc,optiond;
        public Vholder(@NonNull View itemView) {
            super(itemView);
            txt_question = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);
            optionb = itemView.findViewById(R.id.optionb);
            optionc = itemView.findViewById(R.id.optionc);
            optiond = itemView.findViewById(R.id.optiond);
        }
    }
}
