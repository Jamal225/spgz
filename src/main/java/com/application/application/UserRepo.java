package com.application.application;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRepo {

    private HashMap<String, User> users;

    public UserRepo() {
        users = new HashMap<>();
    }

    public User getUserOrCreate(String login, String password) {
        var user = users.get(login);
        return user != null ? user : new User(login, password);
    }

    public void put(User user) {
        if (users.containsKey(user.getLogin()))
            users.replace(user.getLogin(), user);
        else
            users.put(user.getLogin(), user);
    }

    public Boolean contains(User user){
        return users.containsKey(user.getLogin());
    }
}
