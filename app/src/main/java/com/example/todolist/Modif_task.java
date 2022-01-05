package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_task);
        Intent intent = getIntent();
        btnModifier = (Button) findViewById(R.id.btnModifierTask);
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
}