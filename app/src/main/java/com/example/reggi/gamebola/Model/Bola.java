package com.example.reggi.gamebola.Model;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.reggi.gamebola.R;

import java.util.ArrayList;
import java.util.Random;

public class Bola {
    protected Game game;
    protected int radius, width, height;
    protected float x,y, speedX, speedY;
    protected BolaStatic bolaStatic;
    protected ArrayList<Obstacle> obstacle;
    protected Random r = new Random();

    public Bola(Game game, int radius, ArrayList<Obstacle> obstacle, BolaStatic bolaStatic){
        this.speedX = 0;
        this.speedY = 0;
        this.game = game;
        this.radius = radius;
        this.obstacle = obstacle;
        this.bolaStatic = bolaStatic;
    }

    public float getX(){
        return x;
    }

    public float getStaticX(){
        return this.bolaStatic.getX();
    }

    public float getY(){
        return y;
    }

    public float getStaticY(){
        return this.bolaStatic.getY();
    }

    /*public int getObstacleX(){
        return this.obstacle.getX();
    }

    public int getObstacleY(){
        return this.obstacle.getY();
    }*/

    public int getRadius(){
        return radius;
    }

    public void randomPosition(int width, int height){
        this.width = width;
        this.height = height;
        this.x = r.nextInt(width-radius)+radius;
        this.y = r.nextInt(height-radius)+radius;
    }

    public void updatePosition(float[]values){
        Log.d("x : ", String.valueOf(values[0]));
        Log.d("y : ", String.valueOf(values[1]));
        Log.d("z : ", String.valueOf(values[2]));
            if (!isCornered()) {
                this.speedX -= values[0] / 10;
                this.speedY -= values[1] / -10;

                this.x += speedX;
                this.y += speedY;

                this.game.onBolaPositionChanged();
                if(isIntersectedObstacle()){
                    this.decreaseScore();
                }

                if(isMatching()){
                    this.bolaStatic.setX(r.nextInt(this.width-radius));
                    this.bolaStatic.setY(r.nextInt(this.height-radius));
                }
            } else {
                if (x + radius >= width) {
                    this.x = width - radius;
                    speedX *= -0.4;
                }
                if (y + radius >= height) {
                    this.y = height - radius;
                    speedY *= -0.4;
                }
                if (x - radius <= 0) {
                    this.x = radius;
                    speedX *= -0.4;
                }
                if (y - radius <= 0) {
                    this.y = radius;
                    speedY *= -0.4;
                }
            }

    }

    public boolean isCornered(){
        if(x+radius > width || y+radius > height || x-radius < 0 || y-radius < 0) return true;
        return false;
    }

    public boolean isMatching(){
        if(Math.abs(x-this.bolaStatic.getX()) <= radius && Math.abs(y-this.bolaStatic.getY()) <= radius){
            this.increaseScore();
            return true;
        }
        return false;
    }

    public boolean isIntersectedObstacle(){
        boolean res = false;
        for (int i = 0; i<obstacle.size(); i++) {
            if (Math.abs(x - obstacle.get(i).getX()) <= radius && Math.abs(y - obstacle.get(i).getY()) <= radius) {
                res = true;
                break;
            }
            else {
                res = false;
            }
        }
        return res;
    }

    public void increaseScore(){
        this.game.updateScore();
    }

    public void decreaseScore(){
        this.game.decreaseScore();
    }
}
