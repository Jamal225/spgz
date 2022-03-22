package com.application.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class StartWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartWindow.class.getResource("FirstWindow.fxml"));
        StartWindowController startWindowController = new StartWindowController();
        AppFXClient appFXClient = new AppFXClient(startWindowController);
        StartWindowLogic startWindowLogic = new StartWindowLogic(new UserRepo(), new MessageForUser(), appFXClient);
        startWindowController.setStartWindowLogic(startWindowLogic);
        fxmlLoader.setController(startWindowController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Пример");
        stage.setScene(scene);
        stage.show();
    }

    public void run(String[] args) {
        launch();
    }
}
