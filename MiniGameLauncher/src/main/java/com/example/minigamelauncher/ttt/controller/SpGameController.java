package com.example.minigamelauncher.ttt.controller;

import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.ttt.Application;
import com.example.minigamelauncher.ttt.model.SpBoard;
import com.example.minigamelauncher.ttt.model.GameStats;
import com.example.minigamelauncher.ttt.model.SpGame;
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
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class SpGameController implements Initializable {
    //UI controller for single player mode
    private SpGame spGame;

    MediaPlayer game = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/ttt/Sounds/G2.mp3").toURI().toString()));



    private Image xIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/xIcon.png"));
    private Image oIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/oIcon.png"));
    private Image blankIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/blankIcon.png"));
    private Image boardIcon = new Image(getClass().getResourceAsStream("/com/example/minigamelauncher/ttt/Icons/board.png"));

    private ArrayList<ImageView> images = new ArrayList<ImageView>();

    @FXML
    protected void onMainMenuButtonClick(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spGame = new SpGame();
game.play();

        images.add(Image0);
        images.add(Image1);
        images.add(Image2);
        images.add(Image3);
        images.add(Image4);
        images.add(Image5);
        images.add(Image6);
        images.add(Image7);
        images.add(Image8);

        for (ImageView i : images) { i.setImage(blankIcon); }

        board.setImage(boardIcon);

        if (SpBoard.getPlayerX() == false) {
            aiFirstTurn();
        }
    }

    private void aiFirstTurn() {
        Random rand = new Random();

        int firstMove = rand.nextInt(9);

        images.get(firstMove).setImage(xIcon);
        SpBoard.update(firstMove, 1);
    }


    @FXML
    protected void handleClick(MouseEvent event) throws IOException {
        //gets image object from passed event
        ImageView clickedImage = (ImageView) event.getSource();
        //gets number of image from end of id string
        int imageNum = Integer.parseInt(clickedImage.getId().substring(5, 6));

        //checks if space is clear and udpates ui / game obj if it is
        if (SpBoard.getSpace(imageNum) == 0) {
            if (SpBoard.isOnX()) {
                clickedImage.setImage(xIcon);
                SpBoard.update(imageNum, 1);
            } else {
                clickedImage.setImage(oIcon);
                SpBoard.update(imageNum, 2);
            }
        }

        //checks if that click won the game and moves to end screen if it did
        if (checkWin()) {
            return;
        }

        aiTurn();
    }

    private void aiTurn() throws IOException {
        int aiMove = spGame.getAiMove();

        if (SpBoard.isOnX()) {
            images.get(aiMove).setImage(xIcon);
            SpBoard.update(aiMove, 1);
        } else {
            images.get(aiMove).setImage(oIcon);
            SpBoard.update(aiMove, 2);
        }

        checkWin();
    }

    //returns false if no win
    private boolean checkWin() throws IOException {
        int winner = spGame.checkForWin();
        if (winner > 0) {
            GameStats.setCurrentWinner(winner);
            game.stop();
            game.dispose();
            Application.viewController.switchToEndScene();
            return true;
        } else if (winner == -1) {
            GameStats.setCurrentWinner(winner);
            game.stop();
            game.dispose();
            Application.viewController.switchToEndScene();
            return true;
        }
        return false;
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
