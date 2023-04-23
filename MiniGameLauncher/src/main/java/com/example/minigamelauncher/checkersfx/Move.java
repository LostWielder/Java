package com.example.minigamelauncher.checkersfx;

public class Move {
    private int oldX;
    private int oldY;
    private int newX;
    private int newY;

    public Move(int oldX, int oldY, int newX, int newY) {
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }

    public Move(int newX, int newY) {
        this.newX = newX;
        this.newY = newY;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }
}