package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etLogin;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmailI);
        etLogin = (EditText) findViewById(R.id.etLoginI);
        etPassword = (EditText) findViewById(R.id.etpasswordI);
        etPasswordConfirm = (EditText) findViewById(R.id.etpasswordConfirm);
        Button btnInscription = (Button) findViewById(R.id.btnInscription);

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegister();
            }
        });
    }
    public void toRegister(){
        String email = etEmail.getText().toString();
        String login = etLogin.getText().toString();
        String pwd = etPassword.getText().toString();
        String pwdConfirm = etPasswordConfirm.getText().toString();

        if ((email.isEmpty())||(login.isEmpty())||(pwd.isEmpty())||(pwdConfirm.isEmpty())){
            Toast.makeText(getApplicationContext(), "Les champs ne sont pas tous remplis", Toast.LENGTH_SHORT).show();
        }
        else if (!pwd.equals(pwdConfirm)){
            Toast.makeText(getApplicationContext(), "Les mots de passe ne sont pas identiques", Toast.LENGTH_SHORT).show();
        }
        else if (!StringUtils.contains(email,"@")||(!StringUtils.contains(email,"."))){
            Toast.makeText(getApplicationContext(), "Veuillez saisir une adresse mail valide", Toast.LENGTH_SHORT).show();
        }
        else{
            stringRequest = new StringRequest(Request.Method.POST, DBPages.register_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (StringUtils.contains(response, "Ok")) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (StringUtils.contains(response,"error_email")){
                        Toast.makeText(getApplicationContext(), "L'email est déjà utilisé", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Le login est déjà utilisé", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected Map<String, String> getParams(){
                    Map<String,String> params = new HashMap<>();
                    params.put("email",email);
                    params.put("login",login);
                    params.put("pwd",pwd);
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(stringRequest);
        }
    }
}