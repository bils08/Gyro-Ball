package com.example.reggi.gamebola.Model;

import java.util.Random;

public class Obstacle {
    protected int x,y;
    protected int sisi;
    protected Game game;
    protected Random rand;

    public Obstacle(){
        this.rand = new Random();
        this.sisi = 100;
    }

    public void randomPosition(int width, int height){
        this.x = rand.nextInt(width-100);
        this.y = rand.nextInt(height-100);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSisi(int sisi) {
        this.sisi = sisi;
    }

    public int getY() {
        return y;
    }

    public int getSisi() {
        return sisi;
    }
}
