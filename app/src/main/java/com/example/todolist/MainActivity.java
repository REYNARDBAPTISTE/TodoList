package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etEmail = (EditText) findViewById(R.id.etLogin);
        EditText etPassword = (EditText) findViewById(R.id.etpassword);
        Button btnInscription = (Button) findViewById(R.id.btnInscription);
        Button btnConnexion = (Button) findViewById(R.id.btnConnexion);
        Button btnMdpOublie = (Button) findViewById(R.id.btnMdpOublie);

        btnInscription.setBackgroundColor(Color.GRAY);
        btnConnexion.setBackgroundColor(Color.BLUE);
        btnMdpOublie.setBackgroundColor(Color.WHITE);
        btnMdpOublie.setTextColor(Color.BLACK);
        btnMdpOublie.setOutlineSpotShadowColor(Color.WHITE);

        // PAGE D'INSCRIPTION
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivityRegister();
            }
        });
    }
    public void StartActivityRegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}