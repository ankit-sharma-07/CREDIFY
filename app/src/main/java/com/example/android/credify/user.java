package com.example.android.credify;

public class user {
    String name;
    String email;
    int credit;
    int id;

    user(String uName, String uEmail, int uCredit, int uId) {
        name = uName;
        email = uEmail;
        credit = uCredit;
        id = uId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getCredit() {
        return credit;
    }

    public int getId() {
        return id;
    }
}
