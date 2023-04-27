package com.example.minigamelauncher.IntelliDice.Entities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Scores {
    private final LocalDateTime date;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-DD-yyyy HH:mm");

    private final String player;
    Player playerOb;

    private final int roundCount;

    public String playerName;

    public Scores(LocalDateTime date, String player, int roundCount) {
        this.date = date;
        this.player = player;
        this.roundCount = roundCount;
    }

    public void setPlayerOb(Player playerOb) {
        this.playerOb = playerOb;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", player='" + player + '\'' +
                ", Rounds =" + roundCount +
                '}';
    }

    public void writeScoresToTxt() throws IOException {
        File scores = new File("src/main/resources/com/example/minigamelauncher/IntelliDice/ScoreCard.txt");
        BufferedReader scoresIn = new BufferedReader(new FileReader(scores));


        StringBuilder listScores = new StringBuilder();
        int lineCount = 0;

        if (playerOb.getName().length() >= 13) {
           playerName = playerOb.getName().substring(0,11);
            playerName = playerName+"...";
        }else{
           playerName = playerOb.getName();
        }



        while (scoresIn.ready() && lineCount < 9) {

          listScores.append(scoresIn.readLine()).append("\n");
          lineCount++;



        }

            FileWriter scoresOut = new FileWriter("src/main/resources/com/example/minigamelauncher/IntelliDice/ScoreCard.txt");
            String listScores2 = this.date.format(format) + "| " + playerName + "| Rounds:  " + roundCount + "\n" + listScores;
            System.out.println(listScores2);
            scoresOut.write(listScores2);
            scoresIn.close();
            scoresOut.close();

    }





}
