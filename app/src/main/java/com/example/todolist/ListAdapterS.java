package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterS extends ArrayAdapter<Group> {
    Context context;

    public ListAdapterS(@NonNull Context context, List<Group> listeGroupe) {
        super(context, -1, listeGroupe);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view = null;

        if (view==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.tasks_design,parent,false);
        }
        else{
            view = convertView;
        }
        return view;
    }
}
