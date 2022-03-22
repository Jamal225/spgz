package com.application.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StartWindowController {

    private StartWindowLogic startWindowLogic;

    public void setStartWindowLogic(StartWindowLogic startWindowLogic) {
        this.startWindowLogic = startWindowLogic;
    }

    @FXML
    private Label label;

    @FXML
    private Button login;

    @FXML
    private TextField loginBox;

    @FXML
    private TextField passwordBox;

    @FXML
    private Button register;

    @FXML
    void initialize() {
        login.setOnAction(event -> {
            startWindowLogic.LoginUser(loginBox.getText(), passwordBox.getText());
        });
        register.setOnAction(event ->{
            startWindowLogic.RegisterUser(loginBox.getText(), passwordBox.getText());
        });
    }

    public void showMessage(String text){
        label.setText(text);
    }
}
