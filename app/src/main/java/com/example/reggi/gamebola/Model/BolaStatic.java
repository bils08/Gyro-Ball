package com.example.reggi.gamebola.Model;

import java.util.Random;

public class BolaStatic {
    protected int x,y;
    protected int radius;
    protected Game game;
    protected Random rand;

    public BolaStatic(){
        this.rand = new Random();
        this.radius = 50;
    }

    public void randomPosition(int width, int height){
        this.x = this.rand.nextInt(width - radius)+radius;
        this.y = this.rand.nextInt(height - radius)+radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
}
