package com.application.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ApplicationFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationFX.class.getResource("FirstWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Пример");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
