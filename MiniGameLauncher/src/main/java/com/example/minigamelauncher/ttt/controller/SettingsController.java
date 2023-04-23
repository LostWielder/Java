package com.example.minigamelauncher.ttt.controller;

import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.intellimatch.MatchMenuController;
import com.example.minigamelauncher.ttt.Application;
import com.example.minigamelauncher.ttt.model.SpBoard;
import com.example.minigamelauncher.ttt.model.SpGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    //UI controller for game settings menu

    @FXML
    public RadioButton singlePlayerButton;
    public RadioButton multiPlayerButton;
    public RadioButton diff0Button;
    public RadioButton diff1Button;
    public RadioButton teamXButton;
    public RadioButton teamOButton;

  MediaPlayer game = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/ttt/Sounds/Game.mp3").toURI().toString()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        singlePlayerButton.setSelected(true);
        game.play();
    }

    @FXML
    protected void onMainMenuButtonClick() throws IOException {
        game.stop();
        game.dispose();
        Application.viewController.switchToMainMenuScene();
    }
    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if (menu.getCode() == KeyCode.ESCAPE) {
            game.stop();
            game.dispose();
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
    protected void onLaunchButtonClick() throws IOException {
        game.stop();
        game.dispose();
        if (singlePlayerButton.isSelected()) {
            if (diff1Button.isSelected()) {
                SpBoard.setDifficulty(1);
            }
            if (teamOButton.isSelected()) {
                SpBoard.setPlayerX(false);
            } else {
                SpBoard.setPlayerX(true);
            }
            Application.viewController.newSinglePlayerGame();
        } else {
            Application.viewController.newMultiPlayerGame();
        }
    }

    @FXML
    protected void singlePlayerClicked() { multiPlayerButton.setSelected(false); }
    @FXML
    protected void multiPlayerClicked() { singlePlayerButton.setSelected(false); }
    @FXML
    protected void diff0Clicked() { diff1Button.setSelected(false); }
    @FXML
    protected void diff1Clicked() { diff0Button.setSelected(false); }
    @FXML
    protected void teamXClicked() { teamOButton.setSelected(false); }
    @FXML
    protected void teamOClicked() { teamXButton.setSelected(false); }
}
