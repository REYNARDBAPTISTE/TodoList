package com.example.todolist;

public class Users {
    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getEmailU() {
        return emailU;
    }

    public void setEmailU(String emailU) {
        this.emailU = emailU;
    }

    public String getLoginU() {
        return loginU;
    }

    public void setLoginU(String loginU) {
        this.loginU = loginU;
    }

    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }

    public String getFirst_nameU() {
        return first_nameU;
    }

    public void setFirst_nameU(String first_nameU) {
        this.first_nameU = first_nameU;
    }

    public String getPasswordU() {
        return passwordU;
    }

    public void setPasswordU(String passwordU) {
        this.passwordU = passwordU;
    }

    public Users(int idU, String emailU, String loginU, String nameU, String first_nameU, String passwordU) {
        this.idU = idU;
        this.emailU = emailU;
        this.loginU = loginU;
        this.nameU = nameU;
        this.first_nameU = first_nameU;
        this.passwordU = passwordU;
    }

    int idU;
    String emailU;
    String loginU;
    String nameU;
    String first_nameU;
    String passwordU;
    public Users(int idU, String emailU, String loginU, String passwordU) {
        this.idU = idU;
        this.emailU = emailU;
        this.loginU = loginU;
        this.passwordU = passwordU;
    }

    public Users(int idU) {
        this.idU = idU;
    }
}
