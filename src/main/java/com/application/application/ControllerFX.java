package com.application.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerFX {

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
            System.out.print(loginBox.getText());
            System.out.print(passwordBox.getText());
        });
    }
}
