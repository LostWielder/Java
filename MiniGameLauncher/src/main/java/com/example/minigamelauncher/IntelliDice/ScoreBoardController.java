package com.example.minigamelauncher.IntelliDice;

import com.example.minigamelauncher.HelloApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;


public class ScoreBoardController {

    MediaPlayer scoreBgm = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/BGM/Scores.mp3").toURI().toString()));

    @FXML
    private Text scoreText = new Text();

    public void playBGM(){
        scoreBgm.play();
    }

    public void readScoresFromTxt() throws IOException {

        File scores = new File("src/main/resources/com/example/minigamelauncher/IntelliDice/ScoreCard.txt");
        BufferedReader scoresIn = new BufferedReader(new FileReader(scores));
        int lineCount = 0;

        StringBuilder listScores = new StringBuilder();
        while (scoresIn.ready() && lineCount < 10) {
            listScores.append(scoresIn.readLine()).append("\n");
            lineCount++;
        }

        scoresIn.close();
        scoreText.setText(listScores.toString());



    }

    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if(menu.getCode()== KeyCode.ESCAPE){
            scoreBgm.stop();
            scoreBgm.dispose();
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
    protected void Menu(Event event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/com/example/minigamelauncher/IntelliDice/menu.fxml")));
        Parent parent = fxmlLoader.load();
        scoreBgm.stop();
        scoreBgm.dispose();

        Scene scene = new Scene(parent);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);


    }

    public void clearScores() throws IOException {

        FileWriter scoresOut = new FileWriter("src/main/resources/com/example/minigamelauncher/IntelliDice/ScoreCard.txt");
        scoresOut.write("");
        scoresOut.close();
        scoreText.setText("");
    }
}
