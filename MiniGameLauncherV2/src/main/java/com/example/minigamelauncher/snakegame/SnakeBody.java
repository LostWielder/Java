package com.example.minigamelauncher.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

public class SnakeBody extends Object {
    private String img = "src/main/resources/com/example/minigamelauncher/snakegame/images/Snakebody.png";
    private static final String[] BODY_IMAGES = new String[]{"src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeBodyH.png", "src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeBodyV.png"};

    private static final String[] BODY_TURN_IMAGES = new String[]{"src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeBodyTurn1.png", "src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeBodyTurn2.png", "src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeBodyTurn3.png", "src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeBodyTurn4.png"};
    private static final String[] TAIL_IMAGES = new String[]{"src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeTailL.png", "src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeTailR.png", "src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeTailD.png", "src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeTailU.png"};
    private Image image;

    private boolean tail;
    private int lastDir = -1;

    //RIGHT = 0, LEFT = 1, UP = 2, DOWN = 3
    private int dir;

    public SnakeBody(int x, int y, boolean tail) {
        super(x, y);
        dir = 0;
        this.tail = tail;

    }

    public boolean isTail() {
        return tail;
    }

    public void setTail(boolean tail) {
        this.tail = tail;
    }

    public int getLastDir() {
        return lastDir;
    }

    public void setLastDir(int lastDir) {
        this.lastDir = lastDir;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public String getImg(){
        return img;
    }

    public void drawBody(GraphicsContext gc, int size){
        String stringImg = null;
        if (!tail) {
            if(lastDir < 0 || lastDir == dir){
                if (dir <= 1) {
                    stringImg = BODY_IMAGES[0];
                } else {
                    stringImg = BODY_IMAGES[1];
                }
            }else{
                switch(lastDir){
                    case 0:
                        if(dir == 2){
                            stringImg = BODY_TURN_IMAGES[2];
                        }else{
                            stringImg = BODY_TURN_IMAGES[1];
                        }
                        break;
                    case 1:
                        if(dir == 2){
                            stringImg = BODY_TURN_IMAGES[3];
                        }else{
                            stringImg = BODY_TURN_IMAGES[0];
                        }
                        break;
                    case 2:
                        if(dir == 0){
                            stringImg = BODY_TURN_IMAGES[0];
                        }else{
                            stringImg = BODY_TURN_IMAGES[1];
                        }
                        break;
                    case 3:
                        if(dir == 0){
                            stringImg = BODY_TURN_IMAGES[3];
                        }else{
                            stringImg = BODY_TURN_IMAGES[2];
                        }
                        break;
                }
            }
        } else{
            stringImg = TAIL_IMAGES[dir];
        }

        if(stringImg != null){
            image = new Image(new File(stringImg).toURI().toString());
        }

        gc.drawImage(image, getX() * size, getY() * size, size, size);
    }

}
