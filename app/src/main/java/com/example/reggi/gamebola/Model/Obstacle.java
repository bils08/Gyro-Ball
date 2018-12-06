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
        this.x = rand.nextInt(100);
        this.y = rand.nextInt(100);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSisi() {
        return sisi;
    }
}
