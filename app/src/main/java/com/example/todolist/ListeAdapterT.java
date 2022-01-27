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
            view = layoutInflater.inflate(R.layout.tasks_design,parent,false);
        }
        else{
            view = convertView;
        }
        uneTask = getItem(position);
        TextView tvTitre = (TextView) view.findViewById(R.id.tvTitreTask);
        TextView tvDesc = (TextView) view.findViewById(R.id.tvDescTask);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("DD");
        String formattedDate = df.format(currentTime);
        int tempsDepart = uneTask.getTempsR();
        int tempsLimite = uneTask.getLimiteT();
        int tempsActu = Integer.parseInt(formattedDate);
        int tempsRestant = uneTask.getCalcul();
        if (tempsRestant <= 2)
        {
            tvTitre.setText(uneTask.getTitreT());
            tvTitre.setTextColor(Color.parseColor("#F00020"));
            tvDesc.setText(uneTask.getDescT() + " " +tempsRestant+" jours restants");
            tvDesc.setTextColor(Color.parseColor("#F00020"));
        }
        else if (tempsRestant <= Math.round(tempsDepart * (1/4)))
        {
            tvTitre.setText(uneTask.getTitreT());
            tvTitre.setTextColor(Color.parseColor("#F00020"));
            tvDesc.setText(uneTask.getDescT() + " " +tempsRestant+" jours restants");
            tvDesc.setTextColor(Color.parseColor("#F00020"));
        }
        else if ((tempsRestant > Math.round(tempsDepart * (1/4))) &&(tempsRestant < Math.round(tempsDepart * (2/4)))||(tempsRestant <=4))
        {
            tvTitre.setText(uneTask.getTitreT());
            tvTitre.setTextColor(Color.parseColor("#ff7f00"));
            tvDesc.setText(uneTask.getDescT() + " " +tempsRestant+" jours restants");
            tvDesc.setTextColor(Color.parseColor("#ff7f00"));
        }
        else if ((tempsRestant > Math.round(tempsDepart * (2/4))) &&(tempsRestant < Math.round(tempsDepart * (3/4)))||(tempsRestant <=6))
        {
            tvTitre.setText(uneTask.getTitreT());
            tvTitre.setTextColor(Color.parseColor("#ffff00"));
            tvDesc.setText(uneTask.getDescT() + " " +tempsRestant+" jours restants");
            tvDesc.setTextColor(Color.parseColor("#ffff00"));
        }
        else if ((tempsRestant >= Math.round(tempsDepart *(3/4))) && (tempsRestant > 6))
        {
            tvTitre.setText(uneTask.getTitreT());
            tvTitre.setTextColor(Color.parseColor("#00FF00"));
            tvDesc.setText(uneTask.getDescT() + " " +tempsRestant+" jours restants");
            tvDesc.setTextColor(Color.parseColor("#00FF00"));
        }
        return view;
    }
}
