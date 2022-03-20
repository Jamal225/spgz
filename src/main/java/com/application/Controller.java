package com.application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Button login;

    @FXML
    private Label loginBox;

    @FXML
    private Label passwordBox;

    @FXML
    private Button register;

    @FXML
    void initialize() {
        login.setOnAction(event ->{
            System.out.print(loginBox.getText());
            System.out.print(passwordBox.getText());
        });
    }
}
