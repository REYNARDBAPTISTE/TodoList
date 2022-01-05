package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateTaskActivity extends AppCompatActivity {
    private EditText etTitre;
    private EditText etDesc;
    private EditText etDate;
    private TextView tvError;
    private DBManager dbm;
    private Button btnValider;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);
        Intent intent = getIntent();
        etTitre = (EditText) findViewById(R.id.etNomT);
        etDesc = (EditText) findViewById(R.id.etDescT);
        etDate = (EditText) findViewById(R.id.etDateT);
        tvError = (TextView) findViewById(R.id.tvError2);
        dbm = new DBManager(this);
        btnValider = (Button) findViewById(R.id.btnValiderInscriptionT);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int date = 0;
                if (!etDate.getText().toString().isEmpty()){
                    date = Integer.valueOf(etDate.getText().toString());
                }
                if (etTitre.getText().toString().isEmpty()){
                    tvError.setText("Merci de rentrer un titre à votre tâche");
                }
                else if (etDate.getText().toString().isEmpty()){
                    tvError.setText("Merci de selectionner le nombre de jour disponible pour réaliser votre tâche");
                }
                else if (date > 360){
                    tvError.setText("Vous ne pouvez pas créer de tâche de plus de 360 jours");
                }
                else{
                    AjoutBDD();
                    start();
                }
            }
        });
    }
    public void start(){
        Intent intent = new Intent(this,TaskpersoActivity.class);
        startActivity(intent);
        finish();
    }
    public void AjoutBDD(){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("DD");
        String formattedDate = df.format(currentTime);
        int dateActu = Integer.parseInt(formattedDate);
        int dateLimite = Integer.parseInt(etDate.getText().toString());
        int nbjour = dateActu + dateLimite;
        String titre = etTitre.getText().toString();
        String desc = etDesc.getText().toString();
        int limiteT = nbjour;
        Intent intent = getIntent();
        int idU = intent.getIntExtra("idU",0);

        stringRequest = new StringRequest(Request.Method.POST, DBPages.task_add_url, new Response.Listener<String>() {
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
                params.put("titre",titre);
                params.put("desc",desc);
                params.put("creation", String.valueOf(dateActu));
                params.put("limite", String.valueOf(limiteT));
                params.put("id", String.valueOf(idU));
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(CreateTaskActivity.this);
        requestQueue.add(stringRequest);
    }
}