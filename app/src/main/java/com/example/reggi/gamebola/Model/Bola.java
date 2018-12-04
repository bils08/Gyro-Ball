package com.example.reggi.gamebola.Model;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.reggi.gamebola.R;

import java.util.Random;

public class Bola {
    protected Game game;
    protected int radius, width, height;
    protected float x,y, speedX, speedY, staticX, staticY, obstacleX, obstacleY;
    protected Random r = new Random();

    public Bola(Game game, int radius){
        this.speedX = 0;
        this.speedY = 0;
        this.game = game;
        this.radius = radius;
    }

    public float getX(){
        return x;
    }

    public float getStaticX(){
        return staticX;
    }

    public float getY(){
        return y;
    }

    public float getStaticY(){
        return staticY;
    }

    public float getObstacleX(){
        return obstacleX;
    }

    public float getObstacleY(){
        return obstacleY;
    }

    public int getRadius(){
        return radius;
    }

    public void randomPosition(int width, int height){
        this.width = width;
        this.height = height;
        this.x = r.nextInt(width-radius);
        this.y = r.nextInt(height-radius);
        this.staticX = r.nextInt(width-radius);
        this.staticY = r.nextInt(width-radius);
        this.obstacleX = r.nextInt(width-radius);
        this.obstacleY = r.nextInt(width-radius);
        if(staticX == obstacleX){
            obstacleX = r.nextInt(width-radius);
        }
        else if(staticY == obstacleY){
            obstacleY = r.nextInt(width-radius);
        }
    }

    public void updatePosition(float[]values){
        Log.d("x : ", String.valueOf(values[0]));
        Log.d("y : ", String.valueOf(values[1]));
        Log.d("z : ", String.valueOf(values[2]));
            if (!isIntersected()) {
                this.speedX -= values[0] / 3;
                this.speedY -= values[1] / -3;

                this.x += speedX;
                this.y += speedY;

                this.game.onBolaPositionChanged();
                if(isMatching()){
                    this.staticX = r.nextInt(this.width-radius);
                    this.staticY = r.nextInt(this.height-radius);
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

    public boolean isIntersected(){
        if(x+radius > width || y+radius > height || x-radius < 0 || y-radius < 0) return true;
        return false;
    }

    public boolean isMatching(){
        if(Math.abs(x-staticX) <= radius && Math.abs(y-staticY) <= radius){
            this.updateScore();
            return true;
        }
        return false;
    }

    public void updateScore(){
        this.game.updateScore();
    }
}
