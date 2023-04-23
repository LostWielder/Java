package com.example.minigamelauncher.intellimatch;

import com.example.minigamelauncher.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class MatchMenuController {
    @FXML
    String[] songs = {"Menu","Level 1","Level 2","Level 3"};
    @FXML
    ComboBox jukeBox = new ComboBox();


    MediaPlayer menu = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/BGM/menu.mp3").toURI().toString()));
    MediaPlayer select = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/BGM/select.wav").toURI().toString()));
    MediaPlayer game1 = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/BGM/stage1.mp3").toURI().toString()));
    MediaPlayer game2 = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/BGM/stage2.mp3").toURI().toString()));
    MediaPlayer game3 = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/BGM/stage3.mp3").toURI().toString()));
    @FXML
    VBox levelSelect = new VBox();
    @FXML
    ImageView levelSelectText = new ImageView();
    @FXML
    ImageView levelSelectBorder = new ImageView();
    @FXML
    ImageView easyStage = new ImageView();
    @FXML
    ImageView stanStage = new ImageView();
    @FXML
    ImageView hardStage = new ImageView();
    Image img1 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/EasyModeIcons/grass.jpg").toURI().toString());
    Image img2 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/StandardModeIcons/castle.jpg").toURI().toString());
    Image img3 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/desert.png").toURI().toString());
    Image lvlSlctTxt = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/MenuIcons/levelSelect.gif").toURI().toString());
    Image lvlSlctBrdr = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/MenuIcons/Border.png").toURI().toString());

    static boolean beaten;

    @FXML
    public void initialize() {
        menu.play();
    }
    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if (menu.getCode() == KeyCode.ESCAPE) {
            select.dispose();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) menu.getSource()).getScene().getWindow();
            stage.setTitle("3000-1 MiniGame Compilation");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else if (menu.getCode() == KeyCode.F) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("main-menu.fxml")));
            MatchMenuController matchMenuController = fxmlLoader.getController();
            matchMenuController.beaten = true;

        }
    }


public void newGamePlus() throws IOException {


    if (beaten){
        jukeBox.setVisible(true);
        jukeBox.setItems((FXCollections.observableArrayList(songs)));
        levelSelectText.setImage(lvlSlctTxt);
        levelSelectBorder.setImage(lvlSlctBrdr);
        levelSelectText.setVisible(true);
        levelSelectBorder.setVisible(true);
        levelSelect.setVisible(true);
        easyStage.setImage(img1);
        stanStage.setImage(img2);
        hardStage.setImage(img3);
    }

    }
    public void highlighted(MouseEvent event){
        ((ImageView) event.getSource()).setEffect(new Glow());
        select.play();
    }
    public void exited(MouseEvent event){
        ((ImageView) event.getSource()).setEffect(null);
        select.stop();
    }


    @FXML
    public void easyPlay(Event event) throws IOException {
        select.dispose();
        menu.stop();
        game1.stop();
        game2.stop();
        game3.stop();
        menu.dispose();
        game1.dispose();
        game2.dispose();
        game3.dispose();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("easy-mode.fxml")));
        Parent parent = fxmlLoader.load();
        EasyModeController easyModeController = fxmlLoader.getController();
        easyModeController.Initialize();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("IntelliMatch Easy Mode");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void standardPlay(Event event) throws IOException {
        select.dispose();
        menu.stop();
        game1.stop();
        game2.stop();
        game3.stop();
        menu.dispose();
        game1.dispose();
        game2.dispose();
        game3.dispose();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("standard-mode.fxml")));
        Parent parent = fxmlLoader.load();
        StandardModeController stanModeController = fxmlLoader.getController();
        stanModeController.standardInitialize();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("IntelliMatch Standard Mode");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void hardPlay(Event event) throws IOException {
        select.dispose();
        menu.stop();
        game1.stop();
        game2.stop();
        game3.stop();
        menu.dispose();
        game1.dispose();
        game2.dispose();
        game3.dispose();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("hard-mode.fxml")));
        Parent parent = fxmlLoader.load();
        HardModeController hardModeController = fxmlLoader.getController();
        hardModeController.hardInitialize();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("IntelliMatch Hard Mode");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void playSongs(){
    switch (jukeBox.getValue().toString()){
        case "Menu" -> {
            game1.stop();
            game2.stop();
            game3.stop();
            menu.play();//Play menu song
        }
        case "Level 1" -> {
            menu.stop();
            game2.stop();
            game3.stop();
            game1.play();
        }
        case "Level 2" -> {
            menu.stop();
            game1.stop();
            game3.stop();
            game2.play();
        }
        case "Level 3" -> {
            menu.stop();
            game2.stop();
            game1.stop();
            game3.play();

        }
    }
    }

}
