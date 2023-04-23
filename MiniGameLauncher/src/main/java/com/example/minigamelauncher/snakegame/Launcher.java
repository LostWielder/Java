package com.example.minigamelauncher.snakegame;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    SnakeApplication sn = new SnakeApplication();
    @Override
    public void start(Stage stage) throws IOException {



        stage.setTitle("Snake");
        sn.start(stage);
        stage.show();
        stage.setResizable(false);

    }

    public static void main(String[] args) {SnakeApplication.main(args);
    }
}

