package com.example.minigamelauncher.IntelliDice.Entities;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Player implements Serializable {
    String Name;
    int Score;
    transient Image portrait;
    Boolean AI;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Image getPortrait() {
        return portrait;
    }

    public void setPortait(Image portait) {
        this.portrait = portait;
    }


    public Boolean getAI() {
        return AI;
    }

    public void setAI(Boolean AI) {
        this.AI = AI;
    }

    public Player(String name, Image portrait, Boolean AI) {
     this.Name = name;
     this.portrait = portrait;
     this.AI = AI;
    }






    private void defaultScore(){
        setScore(0);
    }


}
