package com.example.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="TodoList.db";
    private static final int DATABASE_VERSION = 4;

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlU= "CREATE TABLE User(idU int, emailU String, loginU String, nameU String, first_nameU String, passwordU String)";
        db.execSQL(sqlU);
        String sqlUT = "INSERT INTO User (idU) VALUES (16)";
        db.execSQL(sqlUT);
        String sqlT = "CREATE TABLE Task(idT int, titreT String, descT String, creationT int, limiteT int,idU int, PRIMARY KEY(idT))";
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

    public void TaskAdd(int idT, String titreT,int creationT, int limiteT) {
        String sqlTA = "INSERT INTO Task (idT,titreT,creationT,limiteT) VALUES ("+idT+",'"+titreT+"',"+creationT+","+limiteT+")";
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
    public ArrayList<Users> lectureU(){
        ArrayList<Users> listeUser = new ArrayList<Users>();
        String sqlLU = "SELECT idU FROM User";
        Cursor curseur = this.getReadableDatabase().rawQuery(sqlLU,null);
        curseur.moveToFirst();
        Users unUser = new Users(curseur.getInt(0));
        listeUser.add(unUser);
        curseur.close();
        return listeUser;
    }

}
