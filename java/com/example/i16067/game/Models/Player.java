package com.example.i16067.game.Models;

public class Player {
    // Atribut nama dan jumlah score yang disimpan
    protected String name ;
    protected int score ;


    /**
     * Constructor Player untuk inisialisasi objek Player ketika baru
     * memulai game
     * @param name
     * @param score
     */
    public Player(String name , int score){
        this.name = name ;
        this.score = score;
    }

    /**
     * Getter score untuk mengambil skor
     * @return skor
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Getter nama untuk mengambil nama
     * @return skor
     */
    public String getNama(){
        return this.name;
    }

}
