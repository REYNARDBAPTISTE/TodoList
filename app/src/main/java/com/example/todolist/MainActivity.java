package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SI UTILISATEUR DEJA CONNECTE ALORS STARTACTIVITY
        etEmail = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etpassword);
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
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivityLogin();
            }
        });
    }
    public void StartActivityRegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    public void StartActivityLogin(){
        String email = etEmail.getText().toString();
        String pwd = etPassword.getText().toString();

        if (email.isEmpty()||pwd.isEmpty()){
            Toast.makeText(getApplicationContext(), "Veuillez saisir tout les champs", Toast.LENGTH_SHORT).show();
        }
        else{
            stringRequest = new StringRequest(Request.Method.POST, DBPages.login_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (StringUtils.contains(response, "Connecté")) {
                        Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (StringUtils.contains(response, "Mot de passe incorect")) {
                        Toast.makeText(getApplicationContext(), "Le mot de passe est incorrect", Toast.LENGTH_SHORT).show();
                        etPassword.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "L'utilisateur est inconnu", Toast.LENGTH_SHORT).show();
                    }
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
                    Map<String,String> params= new HashMap<>();
                    params.put("login",email);
                    params.put("pwd",pwd);
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);
        }
    }
}