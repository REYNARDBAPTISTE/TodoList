package com.example.todolist;

public class Tasks {


    public Tasks(int idT, String titreT, int creationT, int limiteT, int idU) {
        this.idT = idT;
        this.titreT = titreT;
        this.descT = descT;
        this.tempsR = tempsR;
        this.limiteT = limiteT;
        this.idU = idU;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public String getTitreT() {
        return titreT;
    }

    public void setTitreT(String titreT) {
        this.titreT = titreT;
    }

    public String getDescT() {
        return descT;
    }

    public void setDescT(String descT) {
        this.descT = descT;
    }

    public int getLimiteT() {return limiteT;
    }

    public void setLimiteT(int limiteT) {
        this.limiteT = limiteT;
    }

    public int getTempsR() {return tempsR;}

    public void setTempsR(int tempsR) {this.tempsR = tempsR;}

    public int getCalcul() {return calcul;}

    public void setCalcul(int calcul) {this.calcul = calcul;}

    public int Getprio() {return prio;}

    public void setPrio(int prio) {this.prio = prio;}

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }


    int idT;
    String titreT;
    String descT;
    int limiteT;
    int tempsR;
    int calcul;
    int prio;

    int idU;

    public Tasks(int idT, String titreT,int limiteT){
        this.idT = idT;
        this.titreT = titreT;
        this.limiteT = limiteT;
    }
    public Tasks(int idT, String titreT,String descT, int tempsR, int limiteT){
        this.idT = idT;
        this.titreT = titreT;
        this.descT = descT;
        this.tempsR = tempsR;
        this.limiteT = limiteT;
    }
    public Tasks(){

    }

    public Tasks(int idT, String titreT, String descT, int tempsR, int limiteT, int calcul, int prio){
        this.idT = idT;
        this.titreT = titreT;
        this.descT = descT;
        this.tempsR = tempsR;
        this.limiteT = limiteT;
        this.calcul = calcul;
        this.prio = prio;
    }
    public Tasks(int idT, String titreT, String descT, int tempsR, int limiteT, int idU){
        this.idT = idT;
        this.titreT = titreT;
        this.descT = descT;
        this.tempsR = tempsR;
        this.limiteT = limiteT;
        this.idU = idU;
    }
    public Tasks(int idT, String titreT, int creationT, int limiteT) {
        this.idT = idT;
        this.titreT=titreT;
        this.tempsR=creationT;
        this.limiteT=limiteT;
    }
}
