package com.example.minigamelauncher.ttt.model;

public class SpBoard {

    private static int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static boolean onX = true;
    private static int difficulty = 1;
    private static boolean playerX = true;

    public static int getSpace(int index) {
        return board[index];
    }

    public static void update(int index, int value) {
        board[index] = value;
        onX = !onX;
    }

    public static boolean isOnX() {
        return onX;
    }

    public static void setDifficulty(int diff) {
        difficulty = diff;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static int[] getCopy() {
        return board;
    }

    public static void clearBoard() {
        board = new int[]{0, 0, 0, 0, 0 ,0 ,0 ,0 ,0};
        onX = true;
    }

    public static boolean getPlayerX() {
        return playerX;
    }

    public static void setPlayerX(boolean x) {
        playerX = x;
    }

}
