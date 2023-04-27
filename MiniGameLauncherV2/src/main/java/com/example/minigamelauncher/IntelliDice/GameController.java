package com.example.minigamelauncher.IntelliDice;

import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.IntelliDice.Entities.Die;
import com.example.minigamelauncher.IntelliDice.Entities.Player;
import com.example.minigamelauncher.IntelliDice.Entities.Scores;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController {
    private List<Player> players = new ArrayList<>();
    @FXML
    AnchorPane anchor = new AnchorPane();

     private int playTo;

    MediaPlayer game = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/BGM/Game.mp3").toURI().toString()));


    Image rollGif = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Dice/diceRoll.gif").toURI().toString());

    Image die1 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Dice/Die1.png").toURI().toString());
    Image die2 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Dice/Die2.png").toURI().toString());
    Image die3 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Dice/Die3.png").toURI().toString());
    Image die4 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Dice/Die4.png").toURI().toString());
    Image die5 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Dice/Die5.png").toURI().toString());
    Image die6 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Dice/Die6.png").toURI().toString());


    @FXML
    private Die die = new Die();
    @FXML
    private Text playerOneName = new Text();
    @FXML
    private Text playerTwoName = new Text();
    @FXML
    private Text playerThreeName = new Text();
    @FXML
    private Text playerFourName = new Text();
    @FXML
    private Text playerOneScore = new Text();
    @FXML
    private Text playerTwoScore = new Text();
    @FXML
    private Text playerThreeScore = new Text();
    @FXML
    private Text playerFourScore = new Text();
    @FXML
    private Text rollValue = new Text();
    @FXML
    private Text turnNum = new Text();

    @FXML
    private Button playerOneRoll = new Button();
    @FXML
    private Button playerTwoRoll = new Button();
    @FXML
    private Button playerThreeRoll = new Button();
    @FXML
    private Button playerFourRoll = new Button();
    @FXML
    private Button playerOnePass = new Button();
    @FXML
    private Button playerTwoPass = new Button();
    @FXML
    private Button playerThreePass = new Button();
    @FXML
    private Button playerFourPass = new Button();
    @FXML
    private Button replayButton = new Button();
    @FXML
    private Button menuButton = new Button();


    @FXML
    private ImageView dieRoll = new ImageView();
    @FXML
    ImageView playerOnePortrait = new ImageView();
    @FXML
    ImageView playerTwoPortrait = new ImageView();
    @FXML
    ImageView playerThreePortrait = new ImageView();
    @FXML
    ImageView playerFourPortrait = new ImageView();



    @FXML
    private int turnCount = 1;
    @FXML
    private int roundCount =0;



    private int dieValue;

    public void setPlayTo(int playTo) {
        this.playTo = playTo;
        System.out.println(playTo);
    }

    public void setPlayerList(List<Player> players) {

        if (players.get(0).getName().length() > 13) {
            players.get(0).setName(players.get(0).getName().substring(0,11)+"...");
            System.out.println(players.get(0).getName());

        }
        if (players.get(1).getName().length() > 13) {
            players.get(1).setName(players.get(1).getName().substring(0,11)+"...");

        }
        if (players.get(2).getName().length() > 13) {
            players.get(2).setName(players.get(2).getName().substring(0,11)+"...");

        }
        if (players.get(3).getName().length() > 13) {
            players.get(3).setName(players.get(3).getName().substring(0,11)+"...");

        }


        this.players = players;
        turnNum.setText(String.valueOf(turnCount));
        replayButton.setVisible(false);
        menuButton.setVisible(false);

        if (Objects.equals(players.get(0).getName(), "No Player")){
            players.get(0).setName("Player One");
            players.get(0).setPortait(new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face1.png").toURI().toString()));
        }
        playerOneName.setText(players.get(0).getName());
        playerOneScore.setText(String.valueOf(players.get(0).getScore()));
        playerOnePortrait.setImage(players.get(0).getPortrait());

        playerTwoName.setText(players.get(1).getName());
        playerTwoScore.setText(String.valueOf(players.get(1).getScore()));
        playerTwoPortrait.setImage(players.get(1).getPortrait());
        System.out.println(players.get(1).getAI());

        playerThreeName.setText(players.get(2).getName());
        playerThreeScore.setText(String.valueOf(players.get(2).getScore()));
        playerThreePortrait.setImage(players.get(2).getPortrait());
        System.out.println(players.get(2).getAI());

        playerFourName.setText(players.get(3).getName());
        playerFourScore.setText(String.valueOf(players.get(3).getScore()));
        playerFourPortrait.setImage(players.get(3).getPortrait());
        System.out.println(players.get(3).getAI());
        game.play();



    }

    public void turnSetup() {


        turnNum.setText(String.valueOf(turnCount));
        if ((players.get(turnCount - 1).getAI() == true)){
            aiRoll();
        } else if (Objects.equals(players.get(turnCount - 1).getName(), "No Player") && (players.get(turnCount - 1).getAI() == false)){
                pass();

        } else {

            switch (turnCount) {
                case 1 -> {
                    playerOneRoll.setVisible(true);
                    playerOnePass.setVisible(true);
                    playerTwoRoll.setVisible(false);
                    playerTwoPass.setVisible(false);
                    playerThreeRoll.setVisible(false);
                    playerThreePass.setVisible(false);
                    playerFourRoll.setVisible(false);
                    playerFourPass.setVisible(false);
                }
                case 2 -> {
                    playerOneRoll.setVisible(false);
                    playerOnePass.setVisible(false);
                    playerTwoRoll.setVisible(true);
                    playerTwoPass.setVisible(true);
                    playerThreeRoll.setVisible(false);
                    playerThreePass.setVisible(false);
                    playerFourRoll.setVisible(false);
                    playerFourPass.setVisible(false);
                }
                case 3 -> {
                    playerOneRoll.setVisible(false);
                    playerOnePass.setVisible(false);
                    playerTwoRoll.setVisible(false);
                    playerTwoPass.setVisible(false);
                    playerThreeRoll.setVisible(true);
                    playerThreePass.setVisible(true);
                    playerFourRoll.setVisible(false);
                    playerFourPass.setVisible(false);
                }
                case 4 -> {
                    playerOneRoll.setVisible(false);
                    playerOnePass.setVisible(false);
                    playerTwoRoll.setVisible(false);
                    playerTwoPass.setVisible(false);
                    playerThreeRoll.setVisible(false);
                    playerThreePass.setVisible(false);
                    playerFourRoll.setVisible(true);
                    playerFourPass.setVisible(true);
                }
            }
        }

    }

    public void pass() {
        if (turnCount < 4) {
            turnCount++;
            turnSetup();
        } else {
            roundCount++;
            System.out.println(roundCount);
            turnCount = 1;
            turnSetup();
        }

    }

    public void checkWin() throws IOException {

        if (players.get(turnCount-1).getScore() > playTo) {
            players.get(turnCount-1).setScore(playTo - dieValue);
            updateScores();
            pass();
        } else if (players.get(turnCount-1).getScore() == playTo) {
            playerOneRoll.setVisible(false);
            playerOnePass.setVisible(false);
            playerTwoRoll.setVisible(false);
            playerTwoPass.setVisible(false);
            playerThreeRoll.setVisible(false);
            playerThreePass.setVisible(false);
            playerFourRoll.setVisible(false);
            playerFourPass.setVisible(false);
            rollValue.setText(dieValue + " "+ players.get(turnCount-1).getName() + " Wins!");
            Scores records = new Scores(LocalDateTime.now(),players.get(turnCount-1).getName(),roundCount);
            records.setPlayerOb(players.get(turnCount-1));
            records.writeScoresToTxt();

            replayButton.setVisible(true);
            menuButton.setVisible(true);
        }

    }
    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if(menu.getCode()== KeyCode.ESCAPE){
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

    public void updateScores() {
        switch (turnCount) {
            case 1 -> playerOneScore.setText(String.valueOf(players.get(0).getScore()));
            case 2 -> playerTwoScore.setText(String.valueOf(players.get(1).getScore()));
            case 3 -> playerThreeScore.setText(String.valueOf(players.get(2).getScore()));
            case 4 -> playerFourScore.setText(String.valueOf(players.get(3).getScore()));
        }
    }

    public void setDieImage() throws IOException, ClassNotFoundException {

        switch (dieValue) {
            case 1 -> dieRoll.setImage(die1);
            case 2 -> dieRoll.setImage(die2);
            case 3 -> dieRoll.setImage(die3);
            case 4 -> dieRoll.setImage(die4);
            case 5 -> dieRoll.setImage(die5);
            case 6 -> dieRoll.setImage(die6);
        }

    }
    /*
    ====================================================================================================================
    | Roll Logic
    ====================================================================================================================
     */


    public void playerRoll() {
        playerOneScore.setVisible(false);
        playerTwoScore.setVisible(false);
        playerThreeScore.setVisible(false);
        playerFourScore.setVisible(false);
        ParallelTransition parallelTransition = new ParallelTransition();
        double pause = 1;
        dieRoll.setImage(rollGif);
        PauseTransition delay = new PauseTransition(Duration.seconds(pause));
        delay.setOnFinished(event -> {
            try {
                setDieImage();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (dieValue == 1) {

                rollValue.setText("Rolled a 1 Too Bad!");
                pass();

            } else {
                rollValue.setText(String.valueOf(dieValue));
                players.get(turnCount-1).setScore(players.get(turnCount-1).getScore() + dieValue);

                try {
                    updateScores();
                    turnSetup();
                    checkWin();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }


            playerOneScore.setVisible(true);
            playerTwoScore.setVisible(true);
            playerThreeScore.setVisible(true);
            playerFourScore.setVisible(true);
        });
        parallelTransition.getChildren().add(delay);
        parallelTransition.play();
        dieValue = die.Roll();
    }
    public void aiRoll() {
        playerOneScore.setVisible(false);
        playerTwoScore.setVisible(false);
        playerThreeScore.setVisible(false);
        playerFourScore.setVisible(false);
        ParallelTransition parallelTransition = new ParallelTransition();
        double pause = 1;
        dieRoll.setImage(rollGif);
        PauseTransition delay = new PauseTransition(Duration.seconds(pause));
        delay.setOnFinished(event -> {
            try {
                setDieImage();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (dieValue == 1) {

                rollValue.setText("Rolled a 1 Too Bad!");
                pass();

            } else if (dieValue % 2 == 0) {
                rollValue.setText(String.valueOf(dieValue));
                players.get(turnCount - 1).setScore(players.get(turnCount - 1).getScore() + dieValue);

                if (players.get(turnCount - 1).getScore() == playTo || players.get(turnCount - 1).getScore() > playTo) {
                    try {
                        updateScores();
                        checkWin();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    updateScores();
                    try {
                        checkWin();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    turnSetup();

                }
            } else {
                rollValue.setText(String.valueOf(dieValue));
                players.get(turnCount - 1).setScore(players.get(turnCount - 1).getScore() + dieValue);

                if (players.get(turnCount - 1).getScore() == playTo || players.get(turnCount - 1).getScore() > playTo) {
                    try {
                        updateScores();
                        checkWin();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    updateScores();
                    pass();
                    try {
                        checkWin();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    turnSetup();

                }
            }
            playerOneScore.setVisible(true);
            playerTwoScore.setVisible(true);
            playerThreeScore.setVisible(true);
            playerFourScore.setVisible(true);

        });
        parallelTransition.getChildren().add(delay);
        parallelTransition.play();
        dieValue = die.Roll();

    }


/*
========================================================================================================================
| End of Game Logic
========================================================================================================================
 */


    public void replay(){
        players.get(0).setScore(0);
        players.get(1).setScore(0);
        players.get(2).setScore(0);
        players.get(3).setScore(0);
        roundCount = 0;

        playerOneScore.setText(String.valueOf(players.get(0).getScore()));
        playerTwoScore.setText(String.valueOf(players.get(1).getScore()));
        playerThreeScore.setText(String.valueOf(players.get(2).getScore()));
        playerFourScore.setText(String.valueOf(players.get(3).getScore()));

        playerOneRoll.setVisible(true);
        playerOnePass.setVisible(true);
        playerTwoRoll.setVisible(false);
        playerTwoPass.setVisible(false);
        playerThreeRoll.setVisible(false);
        playerThreePass.setVisible(false);
        playerFourRoll.setVisible(false);
        playerFourPass.setVisible(false);
        rollValue.setText("Roll the die");

        replayButton.setVisible(false);
        menuButton.setVisible(false);


    }


    @FXML
    protected void Menu(Event event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/com/example/minigamelauncher/IntelliDice/menu.fxml")));
        Parent parent = fxmlLoader.load();

        game.stop();
        game.dispose();
        Scene scene = new Scene(parent);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);


    }
}
