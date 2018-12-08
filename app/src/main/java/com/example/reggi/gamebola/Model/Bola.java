package com.example.reggi.gamebola.Model;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.reggi.gamebola.R;

import java.util.ArrayList;
import java.util.Random;

public class Bola {
    protected Game game;
    protected int radius, width, height, idx, idxBump1, idxBump2;
    protected float x,y, speedX, speedY, berat;
    protected BolaStatic bolaStatic;
    protected ArrayList<Obstacle> obstacle;
    protected Random r;

    public Bola(Game game, int radius, ArrayList<Obstacle> obstacle, BolaStatic bolaStatic){
        this.speedX = 0;
        this.speedY = 0;
        this.idx = -1;
        this.r = new Random();
        this.game = game;
        this.radius = radius;
        this.obstacle = obstacle;
        this.bolaStatic = bolaStatic;
        this.berat = (float) (r.nextFloat()*(2-0.5)+0.5);
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void updatePosition(float[]values){
        //Log.d("x : ", String.valueOf(values[0]));
        //Log.d("y : ", String.valueOf(values[1]));
        //Log.d("z : ", String.valueOf(values[2]));
            if (!isCornered()) {
                if (!isBump(this.game.bola)) {
                    this.speedX -= (values[0] / 10) * this.berat;
                    this.speedY -= (values[1] / -10) * this.berat;

                    this.x += speedX;
                    this.y += speedY;

                    this.game.onBolaPositionChanged();
                    if (isIntersectedObstacle()) {
                        this.decreaseScore();
                        this.obstacle.get(idx).setX(r.nextInt(this.width - radius));
                        this.obstacle.get(idx).setY(r.nextInt(this.height - radius));
                    }

                    if (isMatching()) {
                        this.bolaStatic.setX(r.nextInt(this.width - radius));
                        this.bolaStatic.setY(r.nextInt(this.height - radius));
                    }
                }
                else{
                    Log.d("coba", "BUMP!!");
                    if (this.game.bola.get(idxBump1).getX() + this.game.bola.get(idxBump1).getRadius() == this.game.bola.get(idxBump2).getX()-this.game.bola.get(idxBump2).getRadius()) {
                        this.game.bola.get(idxBump1).setX(this.game.bola.get(idxBump2).getX()-this.game.bola.get(idxBump2).getRadius() - this.game.bola.get(idxBump1).getRadius());
                        this.game.bola.get(idxBump2).setX(this.game.bola.get(idxBump1).getX()+this.game.bola.get(idxBump1).getRadius() + this.game.bola.get(idxBump2).getRadius());
                        speedX *= -0.4;
                    }
                    if (this.game.bola.get(idxBump1).getY() + this.game.bola.get(idxBump1).getRadius() == this.game.bola.get(idxBump2).getY()-this.game.bola.get(idxBump2).getRadius()) {
                        this.game.bola.get(idxBump1).setY(this.game.bola.get(idxBump2).getY()-this.game.bola.get(idxBump2).getRadius() - this.game.bola.get(idxBump1).getRadius());
                        this.game.bola.get(idxBump2).setY(this.game.bola.get(idxBump1).getY()+this.game.bola.get(idxBump1).getRadius() + this.game.bola.get(idxBump2).getRadius());
                        speedY *= -0.4;
                    }
                    if (this.game.bola.get(idxBump2).getX() + this.game.bola.get(idxBump2).getRadius() == this.game.bola.get(idxBump1).getX()-this.game.bola.get(idxBump1).getRadius()) {
                        this.game.bola.get(idxBump2).setX(this.game.bola.get(idxBump1).getX()-this.game.bola.get(idxBump1).getRadius() - this.game.bola.get(idxBump2).getRadius());
                        this.game.bola.get(idxBump1).setX(this.game.bola.get(idxBump2).getX()+this.game.bola.get(idxBump2).getRadius() + this.game.bola.get(idxBump1).getRadius());
                        speedX *= -0.4;
                    }
                    if (this.game.bola.get(idxBump2).getY() + this.game.bola.get(idxBump2).getRadius() == this.game.bola.get(idxBump1).getY()-this.game.bola.get(idxBump1).getRadius()) {
                        this.game.bola.get(idxBump2).setY(this.game.bola.get(idxBump1).getY()-this.game.bola.get(idxBump1).getRadius() - this.game.bola.get(idxBump2).getRadius());
                        this.game.bola.get(idxBump1).setY(this.game.bola.get(idxBump2).getY()+this.game.bola.get(idxBump2).getRadius() + this.game.bola.get(idxBump1).getRadius());
                        speedY *= -0.4;
                    }
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

    public boolean isBump(ArrayList<Bola> bolas){
        Log.d("coba", "masuk cek bump");
        boolean res = false;
        for (int i = 0; i<bolas.size()-1; i++){
            for (int j = i+1; j<bolas.size(); j++){
                if (bolas.get(i).getX()+bolas.get(i).getRadius() == bolas.get(j).getX()-bolas.get(j).getRadius() ||
                        bolas.get(i).getX()-bolas.get(i).getRadius() == bolas.get(j).getX()+bolas.get(j).getRadius() ||
                        bolas.get(i).getY()+bolas.get(i).getRadius() == bolas.get(j).getY()-bolas.get(j).getRadius() ||
                        bolas.get(i).getY()-bolas.get(i).getRadius() == bolas.get(j).getY()+bolas.get(j).getRadius()){
                    this.idxBump1 = i;
                    this.idxBump2 = j;
                    res = true;
                    break;
                }
            }
        }
        Log.d("coba",res+"");
        return res;
    }

    public boolean isIntersectedObstacle(){
        boolean res = false;
        for (int i = 0; i<obstacle.size(); i++) {
            if (Math.abs(x - obstacle.get(i).getX()) <= radius && Math.abs(y - obstacle.get(i).getY()) <= radius) {
                res = true;
                this.idx = i;
                break;
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
