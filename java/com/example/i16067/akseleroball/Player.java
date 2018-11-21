package com.example.i16067.akseleroball;

public class Player {
    protected String name ;
    protected int score ;


    public Player(String name , int score){
        this.name = name ;
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public String getNama(){
        return this.name;
    }

}
