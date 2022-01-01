package com.example.todolist;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTaskActivity extends AppCompatActivity {
    private EditText etTitre;
    private EditText etDesc;
    private EditText etDate;
    private TextView tvError;
    private String choixPrio;
    private Spinner spChoixPriorite;
    private DBManager dbm;
    private Button btnValider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);
        Intent intent = getIntent();
        etTitre = (EditText) findViewById(R.id.etNomT);
        etDesc = (EditText) findViewById(R.id.etDescT);
        etDate = (EditText) findViewById(R.id.etDateT);
        tvError = (TextView) findViewById(R.id.tvError2);
        spChoixPriorite = (Spinner) findViewById(R.id.spPriorite);
        dbm = new DBManager(this);
        btnValider = (Button) findViewById(R.id.btnValiderInscriptionT);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.priorite_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spChoixPriorite.setAdapter(adapter);
        spChoixPriorite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choixPrio=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), choixPrio,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                    String choix = choixPrio;
                    int priorite = 0;
                    if (choix.equalsIgnoreCase("Critique") ==true){
                        priorite = 1;
                    }
                    else if (choix.equalsIgnoreCase("Très Urgent") ==true){
                        priorite = 2;
                    }
                    else if (choix.equalsIgnoreCase("Urgent") ==true){
                        priorite = 3;
                    }
                    else if (choix.equalsIgnoreCase("Non Urgent") ==true){
                        priorite = 4;
                    }
                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("DD");
                    String formattedDate = df.format(currentTime);
                    int dateActu = Integer.parseInt(formattedDate);
                    int dateLimite = Integer.parseInt(etDate.getText().toString());
                    int nbjour = dateActu + dateLimite;
                    int idU = intent.getIntExtra("idU",0);
                    dbm.CreateTask(etTitre.getText().toString(),etDesc.getText().toString(),nbjour,priorite,idU);
                    tvError.setText("Votre tâche a été créé");
                    // PROBLEME DE RESTART PAGE
                    start();
                }
            }
        });
    }
    public void start(){
        Intent intent = new Intent(this,TaskpersoActivity.class);
        startActivity(intent);
    }
}