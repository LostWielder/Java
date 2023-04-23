package com.example.minigamelauncher.ttt.model;

import java.util.Stack;

public class GameStats {
    //handles who is winning and stores past wins in stack

    private static int currentWinner;
    private static Stack<Integer> winList = new Stack<Integer>();

    public static void setCurrentWinner(int winner) {
        currentWinner = winner;
        winList.push(winner);
    }

    public static int getCurrentWinner() {
        return currentWinner;
    }

    public static Stack<Integer> getWinList() {
        return winList;
    }
}
