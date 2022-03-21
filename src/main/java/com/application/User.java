package com.application;

public class User {

    private String login;
    private int password;

    public User(String login, String password){
        this.login = login;
        this.password = password.hashCode();
    }

    public int getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
