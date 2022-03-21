package com.application;

import java.util.Objects;

public class ApplicationLogic {
    private final UserRepo userRepo;
    private final ApplicationClient applicationClient;
    private final MessageForUser messageForUser;

    public ApplicationLogic(UserRepo userRepo, MessageForUser messageForUser, ApplicationClient applicationClient){
        this.userRepo = userRepo;
        this.applicationClient = applicationClient;
        this.messageForUser = messageForUser;
    }

    public void RegisterUser(String login, String password){
        User newUser = new User(login, password);
        if (userRepo.contains(newUser)){
            applicationClient.showMessage(messageForUser.loginBusy);
        }
        else {
            userRepo.put(newUser);
        }
    }

    public void LoginUser(String login, String password){
        User user = new User(login, password);
        if (userRepo.contains(user)){
            User user1 = userRepo.getUserOrCreate(login, password);
            if (!Objects.equals(user.getPassword(), user1.getPassword()))
                applicationClient.showMessage(messageForUser.incorrectPasswordOrLogin);
        }
        else
            applicationClient.showMessage(messageForUser.userNotExist);
    }
}
