package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Modif_task extends AppCompatActivity {
    private TextView etTitre;
    private TextView etDesc;
    private TextView etDate;
    private Button btnModifier;
    private Button btnDeleteTask;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_task);
        Intent intent = getIntent();
        btnModifier = (Button) findViewById(R.id.btnModifierTask);
        btnDeleteTask = (Button) findViewById(R.id.btnDeleteTask);
        etTitre = (TextView) findViewById(R.id.etTitreM);
        etTitre.setText(intent.getStringExtra("titre"));
        etDesc = (TextView) findViewById(R.id.etDescM);
        etDesc.setText(intent.getStringExtra("desc"));
        etDate = (TextView) findViewById(R.id.etDateM);
        int temps = intent.getIntExtra("limite",0);
        int idT = intent.getIntExtra("idT",0);
        int idU = intent.getIntExtra("idU",0);
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int saisie = 0;
                if (etDate.getText().toString().isEmpty())
                    saisie = 0;
                else
                    saisie =Integer.valueOf(etDate.getText().toString());
                int tempsnew = temps + saisie;
                Modif(etTitre.getText().toString(),etDesc.getText().toString(),tempsnew,idT,idU);
                Startactivity();
            }
        });
        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteTask(idT,idU);
            }
        });
    }
    public void Startactivity(){
        Intent intent = new Intent(this,TaskpersoActivity.class);
        startActivity(intent);
        finish();
    }
    public void Modif(String titre, String desc, int tempsnew,int idT,int idU){
        stringRequest = new StringRequest(Request.Method.POST, DBPages.task_modif_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("idT",String.valueOf(idT));
                params.put("idU",String.valueOf(idU));
                params.put("titre",titre);
                params.put("desc",desc);
                params.put("temps",String.valueOf(tempsnew));
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(Modif_task.this);
        requestQueue.add(stringRequest);
    }
    public void DeleteTask(int idT, int idU){
        stringRequest = new StringRequest(Request.Method.POST, DBPages.task_delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("idT",String.valueOf(idT));
                params.put("idU",String.valueOf(idU));
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(Modif_task.this);
        requestQueue.add(stringRequest);
    }
    public void showSimpleDialog(View view, int idU, int idT) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(Modif_task.this);
        builder.setCancelable(false);
        builder.setTitle("Supprimer l'utilisateur ?");
        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                DeleteTask(idU, idT);
            }
        })
                .setNegativeButton("Annuler ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.create().show();
    }
    public void start(){
        Intent intent = new Intent(this,TaskpersoActivity.class);
        startActivity(intent);
        finish();
    }
}