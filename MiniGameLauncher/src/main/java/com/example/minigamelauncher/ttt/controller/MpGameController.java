package com.example.minigamelauncher.ttt.controller;

import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.ttt.Application;
import com.example.minigamelauncher.ttt.model.MpGame;
import com.example.minigamelauncher.ttt.model.GameStats;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MpGameController implements Initializable {
    //UI controller for multiplayer game mode

    private MpGame mpGame;
    MediaPlayer game = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/ttt/Sounds/G2.mp3").toURI().toString()));

    private Image xIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/xIcon.png"));
    private Image oIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/oIcon.png"));
    private Image blankIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/blankIcon.png"));
    private Image boardIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/board.png"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mpGame = new MpGame();
        game.play();
        Image0.setImage(blankIcon);
        Image1.setImage(blankIcon);
        Image2.setImage(blankIcon);
        Image3.setImage(blankIcon);
        Image4.setImage(blankIcon);
        Image5.setImage(blankIcon);
        Image6.setImage(blankIcon);
        Image7.setImage(blankIcon);
        Image8.setImage(blankIcon);
        board.setImage(boardIcon);
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

    //whenever image is clicked, grabs id of image and uses that to reference object.
    //checks if space has been claimed, processes turn if not.
    @FXML
    protected void handleClick(MouseEvent event) throws IOException {
        //gets image object from passed event
        ImageView clickedImage = (ImageView) event.getSource();
        //gets number of image from end of id string
        int imageNum = Integer.parseInt(clickedImage.getId().substring(5, 6));

        //checks if space is clear and udpates ui / game obj if it is
        if(mpGame.isSpaceClear(imageNum)) {
            if(mpGame.isOnX()) {
                clickedImage.setImage(xIcon);
                mpGame.update(imageNum, 1);
                mpGame.setOnX(false);
            }
            else {
                clickedImage.setImage(oIcon);
                mpGame.update(imageNum, 2);
                mpGame.setOnX(true);
            }
        }

        //checks if that click won the game and moves to end screen if it did
        int winner = mpGame.checkForWin();
        if (winner != 0) {
            GameStats.setCurrentWinner(winner);
            game.stop();
            game.dispose();
            Application.viewController.switchToEndScene();
        }
    }

    @FXML
    protected void onMainMenuButtonClick(ActionEvent event) throws IOException {
        game.stop();
        game.dispose();
        Application.viewController.switchToMainMenuScene();
    }

    //gets reference to image objects
    @FXML
    private ImageView Image0;
    @FXML
    private ImageView Image1;
    @FXML
    private ImageView Image2;
    @FXML
    private ImageView Image3;
    @FXML
    private ImageView Image4;
    @FXML
    private ImageView Image5;
    @FXML
    private ImageView Image6;
    @FXML
    private ImageView Image7;
    @FXML
    private ImageView Image8;
    @FXML
    private ImageView board;
}
