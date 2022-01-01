package com.example.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="TodoList.db";
    private static final int DATABASE_VERSION = 7;

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlU= "CREATE TABLE User(idU int, emailU String, loginU String, nameU String, first_nameU String, passwordU String)";
        db.execSQL(sqlU);
        String sqlUT = "INSERT INTO User (idU) VALUES (16)";
        db.execSQL(sqlUT);
        String sqlT = "CREATE TABLE Task(idT int, titreT String, descT String, creationT int, limiteT int,idU int,prio String, PRIMARY KEY(idT))";
        db.execSQL(sqlT);
        String sqlTsk1 = "INSERT INTO Task(idT,titreT,descT,creationT,limiteT) VALUES (1,'titre1','desc1',1,20)";
        String sqlTsk2 = "INSERT INTO Task(idT,titreT,descT,creationT,limiteT) VALUES (2,'titre2','desc2',1,25)";
        String sqlTsk3 = "INSERT INTO Task(idT,titreT,descT,creationT,limiteT) VALUES (3,'titre3','desc3',1,30)";
        db.execSQL(sqlTsk1);
        db.execSQL(sqlTsk2);
        db.execSQL(sqlTsk3);
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
    public ArrayList<Tasks> lectureTask(){
        ArrayList<Tasks> listeTask = new ArrayList<>();
        String sqlTL = "SELECT idT,titreT,descT,creationT,limiteT FROM Task";
        Cursor cursor = this.getReadableDatabase().rawQuery(sqlTL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int creation = cursor.getInt(3);
            int limite = cursor.getInt(4);
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("DD");
            String formattedDate = df.format(currentTime);
            int tempsActu =  Integer.parseInt(formattedDate);
            int restant = limite - tempsActu;
            int prio = 0;
            Tasks uneTask = new Tasks(cursor.getInt(0), cursor.getString(1), cursor.getString(2),creation,limite,restant,prio);
            listeTask.add(uneTask);
            cursor.moveToNext();
        }
        return listeTask;
    }

    public int CountTask(){
        String sqlTL = "SELECT max(idT) FROM Task";
        Cursor curseur = this.getReadableDatabase().rawQuery(sqlTL,null);
        curseur.moveToFirst();
        int nbT = (curseur.getInt(0)) + 1;
        curseur.close();
        return nbT;
    }
    public void CreateTask(String titreT, String descT, int nbjour, int prio,int idU) {
        int nbT = this.CountTask();
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("DD");
        String formattedDate = df.format(currentTime);
        // CONTRÔLE D'ERREUR
        // -> AlertDialog
        // INSERT INTO

        String sqlTU = "INSERT INTO Task (idT, titreT, descT, creationT, limiteT,prio, idU) VALUES(" + nbT + ",'" + titreT + "','" + descT + "'," + formattedDate + "," + nbjour + "," + prio + "," + idU + ")";
        this.getWritableDatabase().execSQL(sqlTU);
    }
}
