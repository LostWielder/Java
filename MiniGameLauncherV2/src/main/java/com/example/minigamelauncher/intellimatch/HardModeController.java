package com.example.minigamelauncher.intellimatch;

import com.example.minigamelauncher.HelloApplication;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class HardModeController {
    public boolean beaten = false;
    MediaPlayer game = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/BGM/stage3.mp3").toURI().toString()));
    MediaPlayer select = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/BGM/select.wav").toURI().toString()));

    ArrayList<ImageView> hardImageViews = new ArrayList<>();
    ArrayList<Image> hardImages = new ArrayList<>();
    int firstClickStorage;
    int imageViewIDNum;
    int secondImageViewIDNum;
    int successfulMatches =0;
    int numOfMatches =10;
    int guessesCount;
    String imageViewImageName = "";
    String secondImageViewImageName = "";
    Boolean barnCheck = false;
    Boolean secondBarnCheck = false;
    @FXML
    ImageView replayButton = new ImageView();
    @FXML
    Button menuButton = new Button();





    Image img1 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph1.png").toURI().toString());
    Image img2 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph2.png").toURI().toString());
    Image img3 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph3.png").toURI().toString());
    Image img4 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph4.png").toURI().toString());
    Image img5 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph5.png").toURI().toString());
    Image img6 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph6.png").toURI().toString());
    Image img7 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph7.png").toURI().toString());
    Image img8 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph8.png").toURI().toString());
    Image img9 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph9.png").toURI().toString());
    Image img10 = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/glyph10.png").toURI().toString());
    Image cover = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/HardModeIcons/casket.png").toURI().toString());
    Image replayBtn = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/Replay.png").toURI().toString());
    Image continueBtn = new Image(new File("src/main/resources/com/example/minigamelauncher/IntelliMatch/Next.png").toURI().toString());

    @FXML
    private ImageView image01;
    @FXML
    private ImageView image02;
    @FXML
    private ImageView image03;
    @FXML
    private ImageView image04;
    @FXML
    private ImageView image05;
    @FXML
    private ImageView image06;
    @FXML
    private ImageView image07;
    @FXML
    private ImageView image08;
    @FXML
    private ImageView image09;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;
    @FXML
    private ImageView image13;
    @FXML
    private ImageView image14;
    @FXML
    private ImageView image15;
    @FXML
    private ImageView image16;
    @FXML
    private ImageView image17;
    @FXML
    private ImageView image18;
    @FXML
    private ImageView image19;
    @FXML
    private ImageView image20;
    @FXML
    private ImageView continueButton;
    @FXML
    private Text winLoseText;
    @FXML
    private Text remainGuessText;

    private boolean click1;


    public void hardInitialize(){
        game.play();
        replayButton.setVisible(false);
        continueButton.setVisible(false);
        continueButton.setImage(continueBtn);
        replayButton.setImage(replayBtn);
        guessesCount = 20;
        newGamePlusCheck();
        remainGuessText.setText("Remaining Guesses: "+guessesCount);
        winLoseText.setText("");
        hardImages.addAll(Arrays.asList(img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img1,img2,img3,img4,img5,img6,img7,img8,img9,img10));
        Collections.shuffle(hardImages);
        hardImageViews.addAll(Arrays.asList(image01,image02,image03,image04,image05,image06,image07,image08,image09,image10,image11,image12,image13,image14,image15,image16,image17,image18,image19,image20));
        for(int i=0; i<21-1; i++) {
            Random r = new Random();
            int indx = r.nextInt((21 - 1));
            System.out.println(indx);
            hardImageViews.get(i).setImage(hardImages.get(i));

        }
        for (int i=0; i<21-1; i++){
            hardImageViews.get(i).setImage(cover);
        }
    }

    public void disableImages(){
        for (int i=0; i<21-1; i++){
            hardImageViews.get(i).setDisable(true);
        }
    }
    public void enableImages(){
        for (int i=0; i<21-1; i++){
        hardImageViews.get(i).setDisable(false);
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
    public void check(MouseEvent event) {

        if (!click1) {
            System.out.println("Starting barncheck 1 " + barnCheck);
            if (((ImageView) event.getSource()).getImage().equals(cover)) {
                barnCheck = true;
                System.out.println("First CLick was a Barn");
            }
            System.out.println("barncheck After click " + barnCheck);
            String imageId = ((ImageView) event.getSource()).getId();
            imageViewIDNum = Integer.parseInt(imageId.substring(5, 7));
            firstClickStorage = imageViewIDNum - 1;
            hardImageViews.get(imageViewIDNum - 1).setImage(hardImages.get(imageViewIDNum - 1));
            imageViewImageName = ((ImageView) event.getSource()).getImage().toString();
            click1 = true;

        } else {
            System.out.println("Starting Second barncheck 1 " + secondBarnCheck);
            if (((ImageView) event.getSource()).getImage().equals(cover)) {
                secondBarnCheck = true;
                System.out.println("Second Click was a Barn");
            }
            System.out.println("Second barncheck After second click " + secondBarnCheck);
            String imageId = ((ImageView) event.getSource()).getId();
            secondImageViewIDNum = Integer.parseInt(imageId.substring(5, 7));


            hardImageViews.get(secondImageViewIDNum - 1).setImage(hardImages.get(secondImageViewIDNum - 1));
            secondImageViewImageName = ((ImageView) event.getSource()).getImage().toString();
            System.out.println(" Image Match " + imageViewImageName.equals(secondImageViewImageName));
            click1 = false;

            if (!imageViewImageName.equals((secondImageViewImageName)) && barnCheck && secondBarnCheck) {
                disableImages();
                barnCheck = false;
                secondBarnCheck = false;
                ParallelTransition parallelTransition = new ParallelTransition();
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event2 -> {
                    hardImageViews.get(secondImageViewIDNum - 1).setImage(cover);
                    hardImageViews.get(firstClickStorage).setImage(cover);
                    winLoseText.setText("Try Again!");
                    guessesCount--;
                    remainGuessText.setText("Remaining Guesses: "+guessesCount);


                    enableImages();
                    loseCheck();

                });
                parallelTransition.getChildren().add(delay);
                parallelTransition.play();


            } else if (imageViewImageName.equals((secondImageViewImageName)) && barnCheck && imageViewIDNum == secondImageViewIDNum) {
                winLoseText.setText("No Cheating!");
                hardImageViews.get(firstClickStorage).setImage(cover);
                click1 = false;
                barnCheck = false;
                secondBarnCheck = false;


            } else if (imageViewImageName.equals((secondImageViewImageName)) && imageViewIDNum != secondImageViewIDNum && !barnCheck) {
                winLoseText.setText("Already Matched");
                click1 = false;

                barnCheck = false;
                secondBarnCheck = false;
            } else if (!imageViewImageName.equals((secondImageViewImageName)) && imageViewIDNum != secondImageViewIDNum && barnCheck && !secondBarnCheck) {
                winLoseText.setText("No Cheating!");
                hardImageViews.get(imageViewIDNum - 1).setImage(cover);
                click1 = false;

                barnCheck = false;
                secondBarnCheck = false;
            } else if (!imageViewImageName.equals((secondImageViewImageName)) && imageViewIDNum != secondImageViewIDNum && !barnCheck && secondBarnCheck) {
                winLoseText.setText("No Cheating!");
                hardImageViews.get(secondImageViewIDNum - 1).setImage(cover);
                click1 = false;

                barnCheck = false;
                secondBarnCheck = false;
            } else if (imageViewImageName.equals((secondImageViewImageName)) && imageViewIDNum != secondImageViewIDNum) {
                winLoseText.setText("Correct!");
                barnCheck = false;
                secondBarnCheck = false;
                System.out.println("Successful Match");
                successfulMatches++;
                winCheck();
                click1 = false;
            }

        }


    }

    public void winCheck(){
        if (successfulMatches == numOfMatches){
            winLoseText.setText("You Win!");
            disableImages();
            replayButton.setVisible(true);
            continueButton.setVisible(true);
            beaten = true;
        }

    }
    public void loseCheck(){
        if (guessesCount == 0){
            winLoseText.setText("You Lose!");
            for(int i=0; i<21-1; i++) {

                hardImageViews.get(i).setImage(hardImages.get(i));

            }
            disableImages();
            replayButton.setVisible(true);
        }
    }
    public void replay(){
        for (int i=0; i<21-1; i++){
            hardImageViews.remove(i);
        }
        for (int i=0; i<21-1; i++){
            hardImages.remove(i);
        }
        enableImages();
        replayButton.setVisible(false);
        hardInitialize();
    }
    @FXML
    public void returnToMenu(KeyEvent menu) throws IOException {
        if(menu.getCode()== KeyCode.ESCAPE){
            game.stop();
            game.dispose();
            select.dispose();
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
        game.stop();
        game.dispose();
        select.dispose();


        enableImages();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("main-menu.fxml")));
        Parent parent = fxmlLoader.load();
        MatchMenuController matchMenuController = fxmlLoader.getController();
        matchMenuController.beaten = true;
        matchMenuController.newGamePlus();

        Scene scene = new Scene(parent);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("IntelliMatch");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();



    }
    public void newGamePlusCheck() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("main-menu.fxml")));
        MatchMenuController matchMenuController = fxmlLoader.getController();
        if(matchMenuController.beaten){
            guessesCount=guessesCount/2;
        }

    }

}
