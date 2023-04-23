package com.example.minigamelauncher;


import com.example.minigamelauncher.checkersfx.CheckersApp;
import com.example.minigamelauncher.checkersfx.MainMenuController;
import com.example.minigamelauncher.chessgame.JChess;
import com.example.minigamelauncher.snakegame.SnakeApplication;
import com.example.minigamelauncher.ttt.Application;
import com.example.minigamelauncher.ttt.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloController extends CheckersApp {
    MediaPlayer bgm = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/Launcher/BGM/menu.mp3").toURI().toString()));
    MediaPlayer select = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/Launcher/BGM/select.mp3").toURI().toString()));

    Image dicePreview = new Image(new File("src/main/resources/com/example/minigamelauncher/Launcher/Icons/DiceGamePreview.gif").toURI().toString());
    Image matchPreview = new Image(new File("src/main/resources/com/example/minigamelauncher/Launcher/Icons/matchGamePreview.gif").toURI().toString());
    Image snakePreview = new Image(new File("src/main/resources/com/example/minigamelauncher/Launcher/Icons/SnakeGamePreview.gif").toURI().toString());
    Image ticPreview = new Image(new File("src/main/resources/com/example/minigamelauncher/Launcher/Icons/ticPreview.gif").toURI().toString());
    Image checkersPreview = new Image(new File("src/main/resources/com/example/minigamelauncher/Launcher/Icons/checkersGamePreview.gif").toURI().toString());

    Image titlePreview = new Image(new File("src/main/resources/com/example/minigamelauncher/Launcher/Icons/inone.png").toURI().toString());
    @FXML
    ImageView gamePreview = new ImageView();
    @FXML
    Text author = new Text();

    @FXML
    public void initialize(){
bgm.play();
gamePreview.setImage(titlePreview);
    }

    public void highlighted(MouseEvent event){
        select.play();
        ((Button) event.getSource()).setEffect(new Glow());
        System.out.println(((Button) event.getSource()).getId());
        switch (((Button)event.getSource()).getId()){
            case "playDice" ->{gamePreview.setImage(dicePreview);
            author.setText("Written by Jaron Swartz");}
            case "playMatch"->{gamePreview.setImage(matchPreview);
                author.setText("Written by Jaron Swartz");}
            case "playSnake"->{gamePreview.setImage(snakePreview);
                author.setText("Written by Manny Flores");}
            case "playTic"->{
                author.setText("Written by Autumn Day");
                gamePreview.setImage(ticPreview);
            }
            case "playChess"->{author.setText("Written by Arkam LastName");

            }
            case "playCheck"->{author.setText("Written By Jorge Flores");
                gamePreview.setImage(checkersPreview);}
        }


    }
    public void exited(MouseEvent event){
        select.stop();
        ((Button) event.getSource()).setEffect(null);
        gamePreview.setImage(titlePreview);

    }

    @FXML
    protected void startDice(Event event) throws IOException {
        select.dispose();
        bgm.stop();
        bgm.dispose();
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("IntelliDice/menu.fxml"));
            Scene scene = new Scene(fxmlLoader2.load(), 600, 400);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("IntelliDice");


            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

        };
    @FXML
    protected void startMatch(Event event) throws IOException {
        select.dispose();
        bgm.stop();
        bgm.dispose();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intellimatch/main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("IntelliMatch");


        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    };

    @FXML
    protected void startSnake(Event event) throws IOException {

        SnakeApplication sn = new SnakeApplication();
        select.dispose();
        bgm.stop();
        bgm.dispose();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Snake");
        sn.start(stage);
        stage.show();
        stage.setResizable(false);

    };

    @FXML
    protected void startTic(Event event) throws IOException {

        Application main = new Application();
        select.dispose();
        bgm.stop();
        bgm.dispose();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main.start(stage);
        stage.setTitle("Super Tic-Tac-Toe");

        stage.show();
        stage.setResizable(false);

    };
    @FXML
    protected void startCheck(Event event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        bgm.stop();
        bgm.dispose();
        select.dispose();
       // this.primaryStage = primaryStage; // Set the primaryStage value

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minigamelauncher/checkersfx/main_menu.fxml"));
            Parent root = loader.load();
            MainMenuController menuController = loader.getController();
            menuController.setApp(this);
            menuController.setPrimaryStage(primaryStage);

            primaryStage.setTitle("Checkers");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void startChess(Event event) throws IOException {
        String[] args = new String[0];
        JChess chess = new JChess();
        select.dispose();
        bgm.stop();
        bgm.dispose();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        chess.main(args);
        stage.setTitle("Super Tic-Tac-Toe");

        stage.show();
        stage.setResizable(false);

    };
    }
