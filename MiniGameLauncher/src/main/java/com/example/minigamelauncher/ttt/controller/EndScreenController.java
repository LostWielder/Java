package com.example.minigamelauncher.ttt.controller;

import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.ttt.Application;
import com.example.minigamelauncher.ttt.model.GameStats;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndScreenController implements Initializable {

    @FXML
    private Label winText;
    @FXML
    private ImageView winnerImage;
    @FXML
    private AnchorPane ancPane;

    Image oWin = new Image(new File("src/main/resources/com/example/minigamelauncher/ttt/Icons/oWin.gif").toURI().toString());

    Image xWin = new Image(new File("src/main/resources/com/example/minigamelauncher/ttt/Icons/xWin.gif").toURI().toString());
    Image draw = new Image(new File("src/main/resources/com/example/minigamelauncher/ttt/Icons/draw.gif").toURI().toString());
    MediaPlayer music = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/ttt/Sounds/victory.mp3").toURI().toString()));


    @FXML
    protected void onMainMenuButtonClick(ActionEvent event) throws IOException {
        music.stop();
        music.dispose();
        Application.viewController.switchToMainMenuScene();
    }
    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if (menu.getCode() == KeyCode.ESCAPE) {
            music.stop();
            music.dispose();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) menu.getSource()).getScene().getWindow();
            stage.setTitle("3000-1 MiniGame Compilation");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        music.play();
        if (GameStats.getCurrentWinner() == 1) {
            winnerImage.setImage(xWin);
            ancPane.setBackground(Background.fill(Color.RED));
        } else if (GameStats.getCurrentWinner() == 2) {
            winnerImage.setImage(oWin);
            ancPane.setBackground(Background.fill(Color.BLUE));
        } else if (GameStats.getCurrentWinner() == -1) {
            winnerImage.setImage(draw);
            ancPane.setBackground(Background.fill(Color.PURPLE));
        } else {
            winText.setText("No winner error");
        }
    }
}