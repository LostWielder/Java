package com.example.minigamelauncher.ttt.model;

public class MpGame {
    //data for muliplayer game. handles boardstate and checking for wins

    public MpGame() {  }

    //keeps track of state of board. reads 123, 456, 789 with 1 in the top left and 9 in the bottom right
    private int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    //tracks who won the game. 0 is default.
    private int winner = 0;

    //tracks if we are on player 1's turn
    private boolean onX = true;

    //contains 2d array of winning board states. values represent groups of three tiles player must occupy to win
    //searches each group on board for the same value, ie. all x or all o
    //returns value if there is a winning board state, otherwise returns 0
    public int checkForWin() {
        int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
        for (int[] combination : winCombinations) {
            int first = board[combination[0]];
            int second = board[combination[1]];
            int third = board[combination[2]];
            if (first != 0 && first == second && second == third) {
                winner = first;
                return first;
            }
        }
        return 0;
    }

    //getters and setters
    public void update(int index, int value) {
        board[index] = value;
    }

    public boolean isSpaceClear(int index) {
        if (board[index] == 0) {
            return true;
        }
        return false;
    }

    public boolean isOnX() {
        return onX;
    }

    public void setOnX(Boolean x) {
        onX = x;
    }
}
