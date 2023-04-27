package com.example.minigamelauncher.ttt;

import com.example.minigamelauncher.ttt.controller.ViewController ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Stage stage;

    public static ViewController viewController;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        viewController = new ViewController(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/com/example/minigamelauncher/ttt/mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("TIC TAC TOE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}