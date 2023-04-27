package com.example.minigamelauncher.ttt.model;

public class SpGame {
    //contains 2d array of winning board states. values represent groups of three tiles player must occupy to win
    //searches each group on board for the same value, ie. all x or all o
    //returns value if there is a winning board state, otherwise returns 0
    private static int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    public static int checkForWin() {
        //returns 1 for x, 2 for o, 0 for neither, and -1 for draw.
        for (int[] combination : winCombinations) {
            int first = SpBoard.getSpace(combination[0]);
            int second = SpBoard.getSpace(combination[1]);
            int third = SpBoard.getSpace(combination[2]);
            if (first != 0 && first == second && second == third) {
                SpBoard.clearBoard();
                return first;
            }
        }

        boolean full = true;
        for (int v : SpBoard.getCopy()) {
            if (v == 0) {
                full = false;
            }
        }
        if (full) {
            SpBoard.clearBoard();
            return -1;
        }

        return 0;
    }

    public static int getAiMove() {
        if (SpBoard.getDifficulty() == 1) {
            return aiLevel1();
        }
        return aiLevel0();
    }

    private static int aiLevel0() {
        //very bad ai. gets center, then corners, then edge squares. not even random :)
        int[] preferredMoves = {4, 0, 2, 6, 8, 1, 3, 5, 7};

        for (int i : preferredMoves) {
            if (SpBoard.getSpace(i) == 0) {
                return i;
            }
        }
        return 0;
    }

    private static int aiLevel1() {
        //slightly better ai, will stop enemies from winning and will attempt to create forks.
        //weak because it cannot tell the difference between blocking a move and playing a move
        //it just first checks for winning moves, then checks for forking moves.
        //if there are no winning or forking moves, it will just resort to the level 0 ai.

        //check if we have win / if enemy will win
        int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
        for (int[] combo : winCombinations) {
            //for every combo in arr, tally number of x, os, and empty spaces
            int numX = 0;
            int numO = 0;
            int indE = 0;
            for(int i : combo) {
                switch (SpBoard.getSpace(i)) {
                    case 0:
                        indE = i;
                        break;
                    case 1:
                        numX++;
                        break;
                    case 2:
                        numO++;
                        break;
                }
            }

            //if there's one empty space, check if there is a match on the other two spaces
            if (indE != 0) {
                if (numX == 2 || numO == 2) {
                    return indE;
                }
            }
        }

        //check if we will fork / if enemy will fork
        int[][] forkCombinations = {{0, 4, 6}, {0, 4, 2}, {2, 4, 8}, {6, 4, 8}, {0, 2, 6}, {0, 2, 8}, {2, 8, 6}, {0, 6, 8}};
        int[][] forkEmptyCombinations = {{6, 2, 8}, {1, 6, 8}, {5, 0, 6}, {7, 0, 2}, {1, 3, 4}, {1, 5, 4}, {4, 5, 7}, {4, 3, 7}};
        for (int i = 0; i < forkCombinations.length; i++) {
            //for every combo in arr, tally number of x, os, and empty spaces
            int numX = 0;
            int numO = 0;
            int indE = 0;
            for (int j : forkCombinations[i]) {
                switch (SpBoard.getSpace(j)) {
                    case 0:
                        indE = j;
                        break;
                    case 1:
                        numX++;
                        break;
                    case 2:
                        numO++;
                        break;
                }
            }

            //if there's one empty space, check if there is a match on the other two spaces
            if (indE != 0) {
                if (numX == 2 || numO == 2) {
                    //check if 2+ outputs are clear
                    int numClear = 0;
                    for (int j : forkEmptyCombinations[i]) {
                        if (SpBoard.getSpace(j) == 0) {
                            numClear++;
                        }
                    }

                    if (numClear > 1) {
                        return indE;
                    }
                }
            }
        }

        return aiLevel0();
    }
}
