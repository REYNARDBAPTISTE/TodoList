package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskpersoActivity extends AppCompatActivity {

    private  ArrayList<Tasks> listeTasks;
    private ArrayList<Users> listeUser;
    private ListView lvTask;
    private DBManager dbm;
    private StringRequest stringRequest;
    private RequestQueue requestQueue2;
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskperso);

        lvTask = (ListView) findViewById(R.id.lvTask);
        listeTasks = new ArrayList<Tasks>();
        dbm = new DBManager(this);
        listeUser = dbm.lectureU();
        dbm.DropTask();
        EnvoieidU();
    }

    public void ChargerLesTasksPerso(){
        jsonArrayRequest = new JsonArrayRequest(DBPages.tasks_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Tasks uneTask = new Tasks(jsonObject.getInt("idT"), jsonObject.getString("titreT"),jsonObject.getInt("creationT"), jsonObject.getInt("limiteT"));
                        dbm.TaskAdd(uneTask.idT,uneTask.titreT,uneTask.tempsR,uneTask.limiteT);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(TaskpersoActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    public void EnvoieidU(){
        String idU = listeUser.toString();
        stringRequest = new StringRequest(Request.Method.POST, DBPages.tasks_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ChargerLesTasksPerso();
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
                Map<String,String> params=new HashMap<>();
                params.put("idU",idU);
                return params;
            }
        };
        requestQueue2 = Volley.newRequestQueue(TaskpersoActivity.this);
        requestQueue2.add(stringRequest);
    }
}