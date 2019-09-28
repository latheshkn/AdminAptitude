package com.example.adminaptitude;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<ModelClassForSpinner> {

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<ModelClassForSpinner> objects) {
        super(context, 0, objects);
    }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return inatlizeView(position,convertView,parent);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return inatlizeView(position,convertView,parent);
        }
        //Since we have to put same layout in our getView and getDropdownView
        //for that we will create a method which will return view for both of them
        //and it will have same argument that above

        private View inatlizeView(int position, View convertView, ViewGroup parent) {
            //convertView is the View we have to recycle
            //so we have to che if this view already existing if it is already existing we dont have to create from scratch we just want a old one

            if (convertView == null)// only in this case we want to inflate
            {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.spinner_item, parent, false);
            }

            ImageView country_flag = (ImageView) convertView.findViewById(R.id.country_flag);
            TextView country_name = (TextView) convertView.findViewById(R.id.country_name);

            ModelClassForSpinner countryItem = getItem(position);

            //now we have get our name and flagImage out of this item and show it in our image view and TExt View
            if (countryItem != null) {
                country_flag.setImageResource(countryItem.getImage());
                country_name.setText(countryItem.getName());
            }
            return convertView;
        }
    }

