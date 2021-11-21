package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etEmail = (EditText) findViewById(R.id.etEmailI);
        EditText etLogin = (EditText) findViewById(R.id.etLoginI);
        EditText etPassword = (EditText) findViewById(R.id.etpasswordI);
        EditText etPasswordConfirm = (EditText) findViewById(R.id.etpasswordConfirm);
        Button btnInscription = (Button) findViewById(R.id.btnInscription);


    }
}