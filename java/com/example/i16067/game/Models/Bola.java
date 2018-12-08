package com.example.i16067.game.Models;

import java.util.Random;

public class Bola extends Lingkaran {

    protected float x,y,kecepatanX,kecepatanY;
    protected int r,berat;

    public Bola(float x, float y,int r)  {
        super(x,y,r);
        Random random=new Random();
        this.berat=1+random.nextInt(5);
        this.kecepatanX=0;
        this.kecepatanY=0;

    }

    public float getX() {
        return super.getX();
    }

    public float getY() {
        return super.getY();
    }

    public int getR() {
        return super.getR();
    }

    public void setX(float x) {
        super.setX(x);
    }

    public void setY(float y) {
        super.setY(y);
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public float getKecepatanX() {
        return kecepatanX;
    }

    public void setKecepatanX(float kecepatanX) {
        this.kecepatanX = kecepatanX;
    }

    public float getKecepatanY() {
        return kecepatanY;
    }

    public void setKecepatanY(float kecepatanY) {
        this.kecepatanY = kecepatanY;
    }
}