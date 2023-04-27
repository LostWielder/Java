package com.example.minigamelauncher.chessgame.engine.board;

import com.example.minigamelauncher.chessgame.engine.Alliance;
import com.example.minigamelauncher.chessgame.engine.piece.*;
import com.example.minigamelauncher.chessgame.engine.player.BlackPlayer;
import com.example.minigamelauncher.chessgame.engine.player.Player;
import com.example.minigamelauncher.chessgame.engine.player.WhitePlayer;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Board {

    private final List<Cell> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;

    private final Pawn enPassantPawn;



    public Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
        this.enPassantPawn = builder.enPassantPawn;

        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);

        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < Utils.NUM_CELLS; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s",tileText));
            if((i + 1) % Utils.NUM_CELLS_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public Player whitePlayer() {
        return this.whitePlayer;
    }

    public Player blackPlayer() {
        return this.blackPlayer;
    }

    public Player currentPlayer() {
        return this.currentPlayer;
    }

    public Pawn getEnPassantPawn() {
        return this.enPassantPawn;
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    private static String prettyPrint(final Cell cell) {
        return cell.toString();
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final Piece piece : pieces) {

            legalMoves.addAll(piece.calculateLegalMoves(this));

        }

        return List.copyOf(legalMoves);


    }

    private static Collection<Piece> calculateActivePieces(List<Cell> gameBoard, Alliance alliance) {

        final List<Piece> activePieces = new ArrayList<>();

        for(final Cell cell : gameBoard) {
            if(cell.isCellOccupied()) {
                final Piece piece = cell.getPiece();
                if(piece.getPieceAlliance() == alliance) {
                    activePieces.add(piece);
                }
            }
        }
        return List.copyOf(activePieces); //copyOf
    }

    public Cell getCell(final int cellCoordinate) {
        return gameBoard.get(cellCoordinate);
    }

    private static List<Cell> createGameBoard(final Builder builder) {
        final Cell[] cells = new Cell[Utils.NUM_CELLS];
        for(int i = 0; i < Utils.NUM_CELLS; i++) {
            cells[i] = Cell.createCell(i, builder.boardConfig.get(i));
        }
        List<Cell> cellList = new ArrayList<>();
        Collections.addAll(cellList, cells);
        return cellList;
    }

    public static Board createStandardBoard() {

        final Builder builder = new Builder();

        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4));
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));

        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new King(Alliance.WHITE, 60));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));

        builder.setMoveMaker(Alliance.WHITE);

        return builder.build();
    }

    public Iterable<Move> getAllLegalMoves() {
        return StreamSupport.stream(
                Stream.concat(this.whitePlayer.getLegalMoves().stream(), this.blackPlayer.getLegalMoves().stream())
                        .collect(Collectors.toUnmodifiableList())
                        .spliterator(), false)
                ::iterator;
    }

    public static class Builder {

        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;
        Pawn enPassantPawn;

        public Builder() {
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build() {
            return new Board(this);
        }

        public void setEnPassantPawn(Pawn enPassantPawn) {
            this.enPassantPawn = enPassantPawn;
        }
    }

}
