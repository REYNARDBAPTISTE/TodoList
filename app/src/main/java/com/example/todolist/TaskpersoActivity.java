package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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
    private ImageButton btnCreateTask;
    private StringRequest stringRequest;
    private RequestQueue requestQueue2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskperso);

        lvTask = (ListView) findViewById(R.id.lvTask);
        listeTasks = new ArrayList<Tasks>();
        dbm = new DBManager(this);
        lvTask = (ListView) findViewById(R.id.lvTask);
        btnCreateTask = (ImageButton) findViewById(R.id.btnCreateTask);

        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCreate();
            }
        });
        ChargerTasks();

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivityModif(i);
            }
        });
    }
    public void startActivityModif(int i){
        Intent intent = new Intent(this,Modif_task.class);
        intent.putExtra("titre",listeTasks.get(i).getTitreT());
        intent.putExtra("desc",listeTasks.get(i).getDescT());
        intent.putExtra("limite",listeTasks.get(i).getLimiteT());
        intent.putExtra("idT",listeTasks.get(i).getIdT());
        intent.putExtra("idU",listeTasks.get(i).getIdU());
        startActivity(intent);
        finish();
    }
    public void toCreate() {
        Intent intent = new Intent (this, CreateTaskActivity.class);
        listeUser = dbm.lectureU();
        intent.putExtra("idU",listeUser.get(0).getIdU());
        this.startActivity(intent);
        finish();
    }

    public void ChargerTasks(){
        dbm.DropTask();
        listeUser = dbm.lectureU();
        String idU = String.valueOf(listeUser.get(0).getIdU());
        stringRequest = new StringRequest(Request.Method.POST, DBPages.tasks_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray j = new JSONArray(response);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = j.getJSONObject(i);
                        Tasks uneTask = new Tasks(object.getInt("idT"), object.getString("titreT"), object.getString("descT"),object.getInt("creationT"), object.getInt("limiteT"),object.getInt("idU"));
                        int calcul = uneTask.getTempsR() - uneTask.getLimiteT();
                        dbm.TaskAdd(uneTask.idT, uneTask.titreT, uneTask.descT, uneTask.tempsR,uneTask.limiteT,uneTask.idU,uneTask.calcul);
                        listeTasks.add(uneTask);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setAdapterTaches(listeTasks);
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

    private void setAdapterTaches(ArrayList<Tasks> listeTasks) {
        ListeAdapterT listeAdapterT = new ListeAdapterT(this,listeTasks);
        lvTask.setAdapter(listeAdapterT);
    }
}