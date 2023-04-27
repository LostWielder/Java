package com.example.minigamelauncher.chessgame;

import com.example.minigamelauncher.chessgame.engine.board.Board;
import com.example.minigamelauncher.chessgame.gui.Table;

public class JChess {

    public static void main(String [] args) {

        Board board = Board.createStandardBoard();

        System.out.println(board);

        Table table = new Table(); }
}
