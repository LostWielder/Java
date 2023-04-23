package com.example.minigamelauncher.checkersfx;

import com.example.minigamelauncher.HelloController;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;



public class MainMenuController {

    @FXML
    private TextField player1NameTextField;

    @FXML
    private TextField player2NameTextField;

    private final StringProperty player1Name = new SimpleStringProperty("Player 1");
    private final StringProperty player2Name = new SimpleStringProperty("Player 2");

    private CheckersApp checkersApp;
    private Stage primaryStage;


    public MainMenuController() {
    }

    // Add a new setApp method
    public void setApp(CheckersApp checkersApp) {
        this.checkersApp = checkersApp;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public StringProperty player1NameProperty() {
        return player1Name;
    }

    public StringProperty player2NameProperty() {
        return player2Name;
    }

    @FXML
    private void newGameAI(ActionEvent event) {
        player1Name.set(player1NameTextField.getText());
        player2Name.set(player2NameTextField.getText());
        checkersApp.newGameAI();
        startGame();
    }

    @FXML
    private void newGameHuman(ActionEvent event) {
        player1Name.set(player1NameTextField.getText());
        player2Name.set(player2NameTextField.getText());
        checkersApp.newGameHuman();
        startGame();
    }

    @FXML
    private void exit(ActionEvent event) {
        checkersApp.exit();
    }

    @FXML

    private void startGame() {
        Pane gameRoot = (Pane) checkersApp.createContent();
        Scene gameScene = new Scene(gameRoot);
        primaryStage.setScene(gameScene);
        primaryStage.show();


    }
}
