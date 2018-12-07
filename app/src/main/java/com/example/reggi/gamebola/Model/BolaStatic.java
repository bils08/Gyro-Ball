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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
