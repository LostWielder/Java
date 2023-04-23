package com.example.minigamelauncher.ttt.controller;

import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.ttt.Application;
import com.example.minigamelauncher.ttt.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public  class ViewController {
    //handles stage changes for whole game

    private Stage stage;

    private int xRes = 600;
    private int yRes = 400;

    public ViewController(Stage stage) {
        this.stage = stage;
    }

    public void switchToSettingsScene() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), xRes, yRes);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenuScene() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), xRes, yRes);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEndScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("endScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), xRes, yRes);
        stage.setScene(scene);
        stage.show();
    }

    public void newSinglePlayerGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("spGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void newMultiPlayerGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mpGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if (menu.getCode() == KeyCode.ESCAPE) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) menu.getSource()).getScene().getWindow();
            stage.setTitle("3000-1 MiniGame Compilation");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }
}