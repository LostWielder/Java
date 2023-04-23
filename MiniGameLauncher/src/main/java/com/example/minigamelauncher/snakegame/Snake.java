package com.example.minigamelauncher.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Snake extends Object{
    private int length;
    private List<SnakeBody> body = new ArrayList<SnakeBody>();
    private Image imageL;
    private Image imageR;
    private Image imageU;
    private Image imageD;

    private Image imageCrashL;
    private Image imageCrashR;
    private Image imageCrashU;
    private Image imageCrashD;
    {
        imageL = new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadL.png").toURI().toString());
        imageR = new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadR.png").toURI().toString());
        imageU = new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadU.png").toURI().toString());
        imageD =new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadD.png").toURI().toString());
        imageCrashL = new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadcrashL.png").toURI().toString());
        imageCrashR = new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadcrashR.png").toURI().toString());
        imageCrashU = new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadcrashU.png").toURI().toString());
        imageCrashD =new Image(new File("src/main/resources/com/example/minigamelauncher/snakegame/images/SnakeheadcrashD.png").toURI().toString());
    }

    public Snake(int x, int y, int length) {
        super(x, y);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<SnakeBody> getBody() {
        return body;
    }

    public void setBody(List<SnakeBody> body) {
        this.body = body;
    }


    public void grow(int amt){
        for(int i = 0; i < amt; i++){
            length += amt;
            body.add(new SnakeBody(100, 100, true));
        }
    }

   //public void addSegment(int x, int y){
        //SnakeBody newSegment = new SnakeBody(x, y);
        //body.add(newSegment);
    //}

    public void spawnSnake(){
        for(int i = 0; i < length; i++){
            boolean tail = false;
            if(i == length - 1){
                tail = true;
            }
            body.add(new SnakeBody(this.getX() - (i + 1), this.getY(), tail));
        }
    }

    public void move(int dir){
        for(int i = body.size() - 1; i >= 0; i--){
            body.get(i).setLastDir(body.get(i).getDir());
            if(i != 0){
                body.get(i).setX(body.get(i - 1).getX());
                body.get(i).setY(body.get(i - 1).getY());
                body.get(i).setDir(body.get(i - 1).getDir());
            }else{
                body.get(i).setX(getX());
                body.get(i).setY(getY());
                body.get(i).setDir(dir);
            }
            if(i != body.size() - 1){
                body.get(i).setTail(false);
            }
        }
        switch(dir){
            case 0:
                setX(getX() + 1);
                break;
            case 1:
                setX(getX() - 1);
                break;
            case 2:
                setY(getY() - 1);
                break;
            case 3:
                setY(getY() + 1);
                break;
        }

        //System.out.println("---------------");
    }

    public void drawSnake(GraphicsContext gc, int size, int dir, boolean gameOver){

        for(SnakeBody b : body){
            b.drawBody(gc, size);
        }
        //gc.rotate(90);
        switch (dir){
            case 0:
                if(gameOver){
                    gc.drawImage(imageCrashR, getX() * size, getY() * size, size, size);
                }else{
                    gc.drawImage(imageR, getX() * size, getY() * size, size, size);
                }
                break;
            case 1:
                if(gameOver){
                    gc.drawImage(imageCrashL, getX() * size, getY() * size, size, size);
                }else{
                    gc.drawImage(imageL, getX() * size, getY() * size, size, size);
                }
                break;
            case 2:
                if(gameOver){
                    gc.drawImage(imageCrashU, getX() * size, getY() * size, size, size);
                }else{
                    gc.drawImage(imageU, getX() * size, getY() * size, size, size);
                }
                break;
            case 3:
                if(gameOver){
                    gc.drawImage(imageCrashD, getX() * size, getY() * size, size, size);
                }else{
                    gc.drawImage(imageD, getX() * size, getY() * size, size, size);
                }
                break;
        }
    }
}
