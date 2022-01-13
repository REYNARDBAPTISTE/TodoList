package com.example.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.todolist.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListeAdapterT extends ArrayAdapter<Tasks> {
    Context context;

    public ListeAdapterT(@NonNull Context context, @NonNull List<Tasks> listeTasks) {
        super(context,-1, listeTasks);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view = null;
        Tasks uneTask;
        if (view==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_group_design,parent,false);
        }
        else{
            view = convertView;
        }
        uneTask = getItem(position);
        TextView tvTitre = (TextView) view.findViewById(R.id.tvTitreTask);
        TextView tvDesc = (TextView) view.findViewById(R.id.tvDescTask);
        return view;
    }
}
