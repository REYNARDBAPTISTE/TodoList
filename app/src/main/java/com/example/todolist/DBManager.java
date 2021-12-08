package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="TodoList";
    private static final int DATABASE_VERSION = 1;

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlU= "CREATE TABLE User(idU int, emailU String, loginU String, nameU String, first_nameU String, passwordU String)";
        db.execSQL(sqlU);
        String insertU = "INSERT INTO User VALUES(1)";
        db.execSQL(insertU);
        String sqlT = "CREATE TABLE Task(idT int, titreT String not null, descT String, creationT int, limiteT int not null,prio String, idU int, PRIMARY KEY(idT), FOREIGN KEY(idU) REFERENCES User(idU))";
        db.execSQL(sqlT);
        String insert = "INSERT INTO Task (idT,titreT,descT,creationT,limiteT,idU) VALUES (1,test,test2,100,150,1)";
        db.execSQL(insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlU ="DROP TABLE User";
        db.execSQL(sqlU);
        String sqlUT = "DROP TABLE Task";
        db.execSQL(sqlUT);
        this.onCreate(db);
    }
    public void TaskAdd(int idT, String titreT, String descT, int creationT, int limiteT, int idU){
        String sqlTA = "INSERT INTO Task (idT,titreT,descT,creationT,limiteT,idU) VALUES ("+idT+",'"+titreT+"','"+descT+"',"+creationT+","+limiteT+","+idU+"";
        this.getWritableDatabase().execSQL(sqlTA);
    }
}
