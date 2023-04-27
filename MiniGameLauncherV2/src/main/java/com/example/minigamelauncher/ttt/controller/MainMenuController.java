package com.example.minigamelauncher.ttt.controller;

import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.ttt.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button quitButton;

    public MediaPlayer title = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/ttt/Sounds/title.mp3").toURI().toString()));
    @FXML
    public void initialize(){
        title.play();
    }

    @FXML
    protected void onPlayButtonClick(ActionEvent event) throws IOException {
        title.stop();
        title.dispose();
        Application.viewController.switchToSettingsScene();
    }

    @FXML
    protected void onQuitButtonClick(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if (menu.getCode() == KeyCode.ESCAPE) {
            title.stop();
            title.dispose();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) menu.getSource()).getScene().getWindow();
            stage.setTitle("3000-1 MiniGame Compilation");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }
    @FXML
    public void returnToMenu2(Event menu) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) menu.getSource()).getScene().getWindow();
            stage.setTitle("3000-1 MiniGame Compilation");
        title.stop();
        title.dispose();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
    }
}