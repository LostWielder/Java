package com.example.minigamelauncher.snakegame;


import com.example.minigamelauncher.HelloApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URISyntaxException;

import java.net.URL;
import java.util.Scanner;

public class SnakeApplication extends Application {

    private static final int HEIGHT = 800;
    private static final int WIDTH = HEIGHT;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;

    private static final int SQUARE_SIZE = WIDTH/ROWS;

    private static final String FOOD_IMAGE = "/images/fruit.png";
    private static final String HIGHSCORE_FILE = "/highscore.txt";

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private static final String FONT = "Comic Sans MS";

    private boolean gameOver;
    private Snake snake;

    private Image foodImage;
    private Object food;
    private int startLen = 2;
    private int growAmt = 1;
    private boolean restart = false;

    private int currentDirection;

    private int score = 0;

    private int highScore = 0;

    private int speed = 120;

    //MediaPlayer turning_sfx = new MediaPlayer(new Media(new File("src/main/resources/com/example/snake/sounds/turn.wav").toURI().toString()));
    MediaPlayer eating_sfx = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/snakegame/sounds/eat.wav").toURI().toString()));
    MediaPlayer dying_sfx = new MediaPlayer(new Media(new File("src/main/resources/com/example/minigamelauncher/snakegame/sounds/die.wav").toURI().toString()));


    private GraphicsContext gc;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("menu-view.fxml"));
        stage.setTitle("Snake");

        foodImage = new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/fruit.png").toURI().toString());

        Group root = new Group();


        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        gc = canvas.getGraphicsContext2D();
        //set up and spawn in the snake
        spawnSnake();
        //readHighScore();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                //checking the first body segment to ENSURE that you cannot turn immediately from left to right or up to down and game over.
                SnakeBody b = snake.getBody().get(0);
                KeyCode code = event.getCode();

                    if(code==KeyCode.ESCAPE){
                        eating_sfx.dispose();
                        dying_sfx.dispose();
                        gameOver=true;
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 600, 400);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage.setTitle("3000-1 MiniGame Compilation");
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    }


                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        if(!(snake.getX() + 1 == b.getX() && snake.getY() == b.getY())) {
                            currentDirection = RIGHT;
                            /*
                            if(!gameOver){
                            turning_sfx.stop();
                            turning_sfx.play();
                            }
                            */
                        }
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        if(!(snake.getX() - 1 == b.getX() && snake.getY() == b.getY())){
                            currentDirection = LEFT;
                            /*
                            if(!gameOver){
                            turning_sfx.stop();
                            turning_sfx.play();
                            }
                            */
                        }
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        if(!(snake.getY() - 1 == b.getY() && snake.getX() == b.getX())){
                            currentDirection = UP;
                            /*
                            if(!gameOver){
                            turning_sfx.stop();
                            turning_sfx.play();
                            }
                            */
                        }
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        if(!(snake.getY() + 1 == b.getY() && snake.getX() == b.getX())){
                            currentDirection = DOWN;
                            /*
                            if(!gameOver){
                            turning_sfx.stop();
                            turning_sfx.play();
                            }
                            */
                        }
                    }
                }else if (code == KeyCode.SPACE) {
                    restart = true;
                }
            }
        });

        food = new Object(0, 0);
        generateFood();

        //This is here to make sure that the game doesn't start with a blank screen for a second before the Timeline kicks in
        run(gc);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed),e->run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc){
        if(gameOver){
            if(score > highScore){
                try {
                    writeHighScore(score);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                highScore = score;
            }
            drawGameOver();
            if(restart){
                restart();
            }
        }else{
            checkForDeath();
            drawBg(gc);
            drawFood(gc);

            if(!gameOver){
                snake.move(currentDirection);

                if(snake.getX() == food.getX() && snake.getY() == food.getY()){
                    eat();
                }
            }
            snake.drawSnake(gc, SQUARE_SIZE, currentDirection, gameOver);
            drawScore();
            restart = false;
        }
    }

    private void drawBg(GraphicsContext gc){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if((i + j) % 2 == 0){
                    gc.setFill(Color.web("AAD751"));
                }else{
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFood(){
        start:
        while(true){
            food.setX((int)(Math.random() * ROWS));
            food.setY((int)(Math.random() * COLUMNS));

            //check if food is placed at the snake head, repeat if so
            if(snake.getX() == food.getX() && snake.getY() == food.getY()){
                continue;
            }

            //check if food is placed in any snake body segment, repeat if so
            for(SnakeBody body : snake.getBody()){
                if(body.getX() == food.getX() && body.getY() == food.getY()){
                    //continues the outer loop rather than inner loop
                    continue start;
                }
            }

            /*
            try {
                foodImage = new Image(String.valueOf(getClass().getResource("/images/fruit.png").toURI()));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            */
            break;
        }
    }

    private void drawFood(GraphicsContext gc){
        gc.drawImage(foodImage, food.getX() * SQUARE_SIZE, food.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    //spawn the snake upon starting the game or restarting
    private void spawnSnake(){
        snake = new Snake(5, COLUMNS/2, startLen);
        snake.spawnSnake();
    }

    private void eat(){
        score++;
        generateFood();
       eating_sfx.stop();
        eating_sfx.play();
        snake.grow(growAmt);
    }

    private void checkForDeath(){
        if((snake.getX() <= 0 && currentDirection == LEFT)|| (snake.getY() <= 0 && currentDirection == UP) || (snake.getX() > ROWS - 2 && currentDirection == RIGHT) || (snake.getY() > COLUMNS - 2 && currentDirection == DOWN)){
            die();
            return;
        }
        for(SnakeBody b : snake.getBody()){
            if(snake.getY() == b.getY()){
                if((snake.getX() == b.getX() + 1 && currentDirection == LEFT) || (snake.getX() == b.getX() - 1 && currentDirection == RIGHT)){
                    die();
                    break;
                }
            }
            if(snake.getX() == b.getX()){
                if((snake.getY() == b.getY() + 1 && currentDirection == UP) || (snake.getY() == b.getY() - 1 && currentDirection == DOWN)){
                    die();
                }
            }
        }

    }

    public void die(){
        gameOver = true;
        dying_sfx.stop();
        dying_sfx.play();
    }

    public void restart(){
        gameOver = false;
        spawnSnake();
        generateFood();
        score = 0;
        currentDirection = RIGHT;
    }

    private void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(FONT, 30));
        gc.fillText("Score: " + score, 10, 35);
    }

    private void drawGameOver(){
        gc.setFill(Color.RED);
        int gameOverSize = 80;
        int scoreSize = 40;
        gc.setFont(new Font(FONT, gameOverSize));
        gc.fillText("Game Over", WIDTH / 4, HEIGHT / 2);
        gc.setFont(new Font(FONT, scoreSize));
        gc.fillText("High Score: " + highScore, WIDTH / 2.8, HEIGHT / 2 + 40);
        gc.fillText("[press SPACE to restart]", WIDTH / 4.9,  HEIGHT / 2 + 80);
    }

    private void readHighScore() throws IOException {
        InputStream is = getClass().getResourceAsStream(HIGHSCORE_FILE);
        Scanner scanner = new Scanner(is);
        highScore = scanner.nextInt();
        is.close();

    }

    private void writeHighScore(int newScore) throws IOException, URISyntaxException {
        URL resourceUrl = getClass().getResource(HIGHSCORE_FILE);
        File file = new File("src/main/resources/com/example/minigamelauncher/snakegame/highscore.txt");
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writer.write(Integer.toString(newScore));
        writer.close();
    }


    public static void main(String[] args) {
        launch();
    }
}