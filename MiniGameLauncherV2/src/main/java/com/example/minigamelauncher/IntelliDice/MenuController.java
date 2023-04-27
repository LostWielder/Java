package com.example.minigamelauncher.IntelliDice;


import com.example.minigamelauncher.HelloApplication;
import com.example.minigamelauncher.IntelliDice.Entities.Player;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MenuController {

    MediaPlayer title = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/BGM/Title.mp3").toURI().toString()));
    MediaPlayer game = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/BGM/Game.mp3").toURI().toString()));

    Image chris = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face1.png").toURI().toString());
    Image arnold = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face2.png").toURI().toString());
    Image jason = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face3.png").toURI().toString());
    Image rachel = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face4.png").toURI().toString());
    Image sarah = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face5.png").toURI().toString());
    Image jenny = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face6.png").toURI().toString());
    Image none = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliDice/Icons/Characters/face7.png").toURI().toString());


    private TextField[] playerList;
    private ImageView[] playerPortraits;
    private CheckBox[] aiCheck;
    @FXML
    private TextField playerOneName = new TextField();
    @FXML
    private TextField playerTwoName = new TextField();
    @FXML
    private TextField playerThreeName = new TextField();
    @FXML
    private TextField playerFourName = new TextField();
    @FXML
    private TextField playTo = new TextField();
    @FXML
    String[] portraits = {"Chris", "Arnold", "Jason", "Rachel", "Sarah", "Jenny"};

    @FXML
    ComboBox playerOneComboBox = new ComboBox();
    @FXML
    ComboBox playerTwoComboBox = new ComboBox();
    @FXML
    ComboBox playerThreeComboBox = new ComboBox();
    @FXML
    ComboBox playerFourComboBox = new ComboBox();

    @FXML
    ImageView playerOnePortrait = new ImageView();
    @FXML
    ImageView playerTwoPortrait = new ImageView();
    @FXML
    ImageView playerThreePortrait = new ImageView();
    @FXML
    ImageView playerFourPortrait = new ImageView();
    @FXML
    CheckBox playerOneAI = new CheckBox();
    @FXML
    CheckBox playerTwoAI = new CheckBox();
    @FXML
    CheckBox playerThreeAI = new CheckBox();
    @FXML
    CheckBox playerFourAI = new CheckBox();




    @FXML
    public void initialize() {

        playerList = new TextField[]{
                playerOneName,
                playerTwoName,
                playerThreeName,
                playerFourName
        };
        playerPortraits = new ImageView[]{
                playerOnePortrait,
                playerTwoPortrait,
                playerThreePortrait,
                playerFourPortrait
        };

        aiCheck = new CheckBox[]{
                playerOneAI,
                playerTwoAI,
                playerThreeAI,
                playerFourAI
        };

        playerOneComboBox.setItems((FXCollections.observableArrayList(portraits)));
        playerTwoComboBox.setItems((FXCollections.observableArrayList(portraits)));
        playerThreeComboBox.setItems((FXCollections.observableArrayList(portraits)));
        playerFourComboBox.setItems((FXCollections.observableArrayList(portraits)));
        game.stop();
        game.dispose();
        title.play();



        playerTwoName.disableProperty().bind(Bindings.isEmpty(playerOneName.textProperty()).or(playerTwoAI.selectedProperty()));

        playerThreeName.disableProperty().bind(Bindings.isEmpty(playerOneName.textProperty())
                .or(Bindings.isEmpty(playerTwoName.textProperty()).or(playerThreeAI.selectedProperty())));

        playerFourName.disableProperty().bind(Bindings.isEmpty(playerOneName.textProperty())
                .or(Bindings.isEmpty(playerTwoName.textProperty()))
                .or(Bindings.isEmpty(playerThreeName.textProperty()).or(playerFourAI.selectedProperty())));

        playerTwoComboBox.disableProperty().bind(Bindings.isEmpty(playerOneName.textProperty()));

        playerThreeComboBox.disableProperty().bind(Bindings.isEmpty(playerOneName.textProperty())
                .or(Bindings.isEmpty(playerTwoName.textProperty())));

        playerFourComboBox.disableProperty().bind(Bindings.isEmpty(playerOneName.textProperty())
                .or(Bindings.isEmpty(playerTwoName.textProperty()))
                .or(Bindings.isEmpty(playerThreeName.textProperty())));
    }

    private List<Player> playerSetup() {


        List<Player> players = new ArrayList<>();
        int i = 0;





        for (TextField nameField : playerList) {
            if (!nameField.isDisabled() && !nameField.getText().trim().isEmpty()) {

                if (playerPortraits[i].getImage() == null){
                    players.add(new Player(nameField.getText(), none, false));

                }else {
                    players.add(new Player(nameField.getText(), playerPortraits[i].getImage(), false));
                }


            } else {
                players.add(new Player("No Player", none, aiCheck[i].isSelected()));
            }
            i++;
        }

        return players;
    }

    public void setPlayerOnePortrait() {
        switch (playerOneComboBox.getValue().toString()) {
            case "Chris" -> playerOnePortrait.setImage(chris);
            case "Arnold" -> playerOnePortrait.setImage(arnold);
            case "Jason" -> playerOnePortrait.setImage(jason);
            case "Rachel" -> playerOnePortrait.setImage(rachel);
            case "Sarah" -> playerOnePortrait.setImage(sarah);
            case "Jenny" -> playerOnePortrait.setImage(jenny);
        }
    }

    public void setPlayerTwoPortrait() {
        switch (playerTwoComboBox.getValue().toString()) {
            case "Chris" -> playerTwoPortrait.setImage(chris);
            case "Arnold" -> playerTwoPortrait.setImage(arnold);
            case "Jason" -> playerTwoPortrait.setImage(jason);
            case "Rachel" -> playerTwoPortrait.setImage(rachel);
            case "Sarah" -> playerTwoPortrait.setImage(sarah);
            case "Jenny" -> playerTwoPortrait.setImage(jenny);

        }
    }

    public void setPlayerThreePortrait() {
        switch (playerThreeComboBox.getValue().toString()) {
            case "Chris" -> playerThreePortrait.setImage(chris);
            case "Arnold" -> playerThreePortrait.setImage(arnold);
            case "Jason" -> playerThreePortrait.setImage(jason);
            case "Rachel" -> playerThreePortrait.setImage(rachel);
            case "Sarah" -> playerThreePortrait.setImage(sarah);
            case "Jenny" -> playerThreePortrait.setImage(jenny);

        }
    }

    public void setPlayerFourPortrait() {
        switch (playerFourComboBox.getValue().toString()) {
            case "Chris" -> playerFourPortrait.setImage(chris);
            case "Arnold" -> playerFourPortrait.setImage(arnold);
            case "Jason" -> playerFourPortrait.setImage(jason);
            case "Rachel" -> playerFourPortrait.setImage(rachel);
            case "Sarah" -> playerFourPortrait.setImage(sarah);
            case "Jenny" -> playerFourPortrait.setImage(jenny);
        }
    }

    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if(menu.getCode()== KeyCode.ESCAPE){
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
    public void checkScores(Event event) throws IOException {

        title.stop();
        title.dispose();


        FXMLLoader fxmlLoaderSB = new FXMLLoader(getClass().getResource(("/com/example/minigamelauncher/IntelliDice/ScoreBoard.fxml")));
        Parent parent = fxmlLoaderSB.load();
        ScoreBoardController scoreBoardController = fxmlLoaderSB.getController();
       scoreBoardController.readScoresFromTxt();
       scoreBoardController.playBGM();
        Scene scene = new Scene(parent);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);


    }

    @FXML
    public void play(Event event) throws IOException {
        List<Player> players = playerSetup();
        title.stop();
        title.dispose();
        String toInt = playTo.getText();


        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource(("/com/example/minigamelauncher/IntelliDice/Game.fxml")));
        Parent parent = fxmlLoader2.load();
        GameController gameController = fxmlLoader2.getController();
        gameController.setPlayerList(players);
        gameController.turnSetup();

        if (toInt.matches("[0-9]*")&& !playTo.getText().trim().isEmpty()){
            gameController.setPlayTo(Integer.parseInt(playTo.getText()));
        }else if (playTo.getText().trim().isEmpty()){
            gameController.setPlayTo(30);
        } else{
            gameController.setPlayTo(30);
        }


        Scene scene = new Scene(parent);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);


    }
}