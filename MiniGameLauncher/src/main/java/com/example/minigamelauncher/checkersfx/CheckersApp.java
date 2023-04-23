package com.example.minigamelauncher.checkersfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CheckersApp extends Application { //line 28

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private Tile[][] board = new Tile[WIDTH][HEIGHT];
    private Pane tileGroup = new Pane();
    private Pane pieceGroup = new Pane();
    private Label timerLabel = new Label();
    private Button startNewGameButton = new Button("Start New Game");
    private boolean paused = false;
    private Stage primaryStage;
    private boolean playerTurn = true;
    private boolean aiPlay = false;


    private PieceType currentTurn = PieceType.WHITE; // line 46

    static void playMoveSound() {
        String soundPath = "src/main/resources/com/example/minigamelauncher/checkersfx/sounds/move_sound.mp3"; // Replace with the path of your sound file
        Media moveSound = new Media(Paths.get(soundPath).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(moveSound);
        mediaPlayer.play();
    }

    private Pane createBoard() {
        Pane boardPane = new Pane();
        boardPane.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        boardPane.getChildren().addAll(tileGroup, pieceGroup);
        return boardPane;
    }


    Parent createContent() {
        VBox root = new VBox();
        root.setPrefSize(WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE) + 60);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        root.getChildren().addAll(createTaskbar(), createBoard());

        return root;
    }

    public void resetGame() {
        resetBoard();
        timerLabel.setText("00:00");
    }

    private HBox createTaskbar() {
        HBox taskbar = new HBox(10);
        taskbar.setPrefSize(WIDTH * TILE_SIZE, 60);

        timerLabel.setFont(new Font("Arial", 20));

        AtomicInteger elapsedSeconds = new AtomicInteger(0);

        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            int seconds = elapsedSeconds.incrementAndGet(); // Update this line
            timerLabel.setText(String.format("%02d:%02d", seconds / 60, seconds % 60));
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        taskbar.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10px; -fx-alignment: center;");

        Button pauseButton = new Button("Pause");
        Button finishButton = new Button("Finish");

        pauseButton.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold;");
        pauseButton.setPadding(new Insets(10, 20, 10, 20));

        finishButton.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold;");
        finishButton.setPadding(new Insets(10, 20, 10, 20));

        taskbar.getChildren().addAll(timerLabel, startNewGameButton, pauseButton, finishButton);

        startNewGameButton.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold;");
        startNewGameButton.setPadding(new Insets(10, 20, 10, 20));

        startNewGameButton.setOnAction(e -> {
            resetBoard(); // Add this line to reset the board when starting a new game
            elapsedSeconds.set(0); // Reset the timer when starting a new game
        });

        pauseButton.setOnAction(e -> {
            paused = !paused;
            if (paused) {
                timer.pause();
                pauseButton.setText("Resume");
            } else {
                timer.play();
                pauseButton.setText("Pause");
            }
        });

        finishButton.setOnAction(e -> {
            restartApplication(); // Replace the previous code with this line
        });

        return taskbar;
    }

    public void restartApplication() {
        Stage newStage = new Stage();
        CheckersApp newApp = new CheckersApp();

        try {
            newApp.start(newStage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.close();
    }



    private void resetBoard() {
        pieceGroup.getChildren().clear(); // Clear all existing pieces

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if (piece != null) {
                    board[x][y].setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                } else {
                    board[x][y].setPiece(null);
                }
            }
        }

        currentTurn = PieceType.WHITE; // Reset the turn to white
    }


    public void newGameAI() { //line 110
        // Implement the logic to start a new game here
        System.out.println("Starting a new game...");
        currentTurn = PieceType.WHITE; // reset turn to white
        aiPlay = true;
    }
    public void newGameHuman() {
        System.out.println("Starting a new game...");
        currentTurn = PieceType.WHITE;
    }

    public void exit() {
        // Implement the logic to exit the application here
        System.out.println("Exiting the application...");
        Platform.exit();
    }


    private MoveResult tryMove(Piece piece, int newX, int newY) {

        if (paused) {
            return new MoveResult(MoveType.NONE);
        }

        if (piece.getType() != currentTurn) { // check if it's the current player's turn
            return new MoveResult(MoveType.NONE);
        }

        if (!isInBounds(newX, newY)) { // add this line to check if newX and newY are within bounds
            return new MoveResult(MoveType.NONE);
        }

        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            currentTurn = (currentTurn == PieceType.WHITE) ? PieceType.RED : PieceType.WHITE; // switch turn
            return new MoveResult(MoveType.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                currentTurn = (currentTurn == PieceType.WHITE) ? PieceType.RED : PieceType.WHITE; // switch turn
                Piece killedPiece = board[x1][y1].getPiece(); // store the killed piece in a variable
                pieceGroup.getChildren().remove(killedPiece); // remove the killed piece using the variable
                board[x1][y1].setPiece(null);
                board[x0][y0].setPiece(null);
                board[newX][newY].setPiece(piece);
                return new MoveResult(MoveType.KILL, killedPiece);


            }
        }

        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    private void aiMove() { 
        Random random = new Random();
        List<Piece> redPieces = pieceGroup.getChildren().stream()
                .filter(node -> node instanceof Piece)
                .map(node -> (Piece) node)
                .filter(piece -> piece.getType() == PieceType.RED)
                .collect(Collectors.toList());

        while (!redPieces.isEmpty()) {
            Piece randomPiece = redPieces.get(random.nextInt(redPieces.size()));
            int x0 = toBoard(randomPiece.getOldX());
            int y0 = toBoard(randomPiece.getOldY());
            List<Point2D> possibleMoves = Arrays.asList(
                            new Point2D(x0 + 1, y0 + 1),
                            new Point2D(x0 - 1, y0 + 1),
                            new Point2D(x0 + 2, y0 + 2),
                            new Point2D(x0 - 2, y0 + 2)
                    ).stream()
                    .filter(move -> isInBounds((int) move.getX(), (int) move.getY())) // add this line to filter out invalid positions
                    .collect(Collectors.toList());


            for (Point2D move : possibleMoves) {
                int newX = (int) move.getX();
                int newY = (int) move.getY();

                MoveResult result = tryMove(randomPiece, newX, newY);
                if (result.getType() == MoveType.NORMAL || result.getType() == MoveType.KILL) {
                    randomPiece.move(newX, newY);
                    if (result.getType() == MoveType.KILL) {
                        pieceGroup.getChildren().remove(result.getPiece());
                    }
                    return;
                }
            }
            redPieces.remove(randomPiece);
        }
        playerTurn = true;
    }
    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }


    private Piece makePiece(PieceType type, int x, int y) { //line 211
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result = tryMove(piece, newX, newY);

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    CheckersApp.playMoveSound(); // Add this line to play the sound
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    CheckersApp.playMoveSound(); // Add this line to play the sound
                    break;
            }
            if (aiPlay == true){
                playerTurn = false;
                aiMove();
            }
        });

        return piece;

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage; // Set the primaryStage value

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/checkersfx/main_menu.fxml"));
            Parent root = loader.load();
            MainMenuController menuController = loader.getController();
            //menuController.setApp(this);
            menuController.setPrimaryStage(primaryStage);

            primaryStage.setTitle("Checkers");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}