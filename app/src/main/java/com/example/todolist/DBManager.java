package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="TodoList.db";
    private static final int DATABASE_VERSION = 1;

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlU= "CREATE TABLE User(idU int, emailU String, loginU String, nameU String, first_nameU String, passwordU String)";
        db.execSQL(sqlU);
        String sqlT = "CREATE TABLE Task(idT int, titreT String not null, descT String, creationT int, limiteT int not null,prio String, idU int, PRIMARY KEY(idT), FOREIGN KEY(idU) REFERENCES User(idU))";
        db.execSQL(sqlT);
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
        String sqlTA = "INSERT INTO Task (idT,titreT,descT,creationT,limiteT,idU) VALUES ("+idT+",'"+titreT+"','"+descT+"',"+creationT+","+limiteT+","+idU+")";
        this.getWritableDatabase().execSQL(sqlTA);
    }
    public void UserAdd(int idU, String emailU, String loginU, String nameU, String first_nameU, String password){
        String sqlUA = "INSERT INTO User (idU,emailU,loginU,nameU,first_nameU,passwordU) VALUES("+idU+",'"+emailU+"','"+loginU+"','"+nameU+"','"+first_nameU+"','"+password+"')";
        this.getWritableDatabase().execSQL(sqlUA);
    }
    public void DropTask(){
        String sqlUT = "DELETE FROM Task";
        this.getWritableDatabase().execSQL(sqlUT);
    }

    public void UserAddTest(int idU, String emailU, String loginU,String password){
        String sqlUA2 = "INSERT INTO User (idU,emailU,loginU,passwordU) VALUES("+idU+",'"+emailU+"','"+loginU+"','"+password+"')";
        this.getWritableDatabase().execSQL(sqlUA2);
    }
}
