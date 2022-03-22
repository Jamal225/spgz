package com.application.application;

public class AppFXClient implements ApplicationClient{
    StartWindowController controllerFX;

    public AppFXClient(StartWindowController controllerFX){
        this.controllerFX = controllerFX;
    }

    @Override
    public void showMessage(String text) {
        controllerFX.showMessage(text);
    }
}
