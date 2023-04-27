package com.example.minigamelauncher.IntelliDice.Entities;

import java.util.Random;

public class Die {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Die(int value) {
        this.value = value;
    }
    public Die(){

    }

    public int Roll(){
        Random r = new Random();
        value = r.nextInt((6 - 1)+1)+1;
        return value;
    }
}
