package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInscription = (Button) findViewById(R.id.btnInscription);
        Button btnConnexion = (Button) findViewById(R.id.btnConnexion);
        Button btnMdpOublie = (Button) findViewById(R.id.btnMdpOublie);

        btnInscription.setBackgroundColor(Color.GRAY);
        btnConnexion.setBackgroundColor(Color.BLUE);
        btnMdpOublie.setBackgroundColor(Color.WHITE);
        btnMdpOublie.setTextColor(Color.BLACK);
        btnMdpOublie.setOutlineSpotShadowColor(Color.WHITE);
    }
}