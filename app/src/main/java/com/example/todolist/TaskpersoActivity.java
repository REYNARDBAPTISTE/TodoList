package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TaskpersoActivity extends AppCompatActivity {

    private  ArrayList<Tasks> listeTasks;
    private ListView lvTask;
    private DBManager dbm;
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskperso);

        lvTask = (ListView) findViewById(R.id.lvTask);
        listeTasks = new ArrayList<Tasks>();
        dbm = new DBManager(this);
        ChargerLesTasksPerso();
    }

    public void ChargerLesTasksPerso(){
        jsonArrayRequest = new JsonArrayRequest(DBPages.tasks_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Tasks uneTask = new Tasks(jsonObject.getInt("idT"), jsonObject.getString("titreT"), jsonObject.getString("descT"), jsonObject.getInt("creationT"), jsonObject.getInt("limiteT"), jsonObject.getInt("idU"));
                        dbm.TaskAdd(uneTask.idT,uneTask.titreT,uneTask.descT,uneTask.tempsR,uneTask.limiteT,uneTask.idU);
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
}