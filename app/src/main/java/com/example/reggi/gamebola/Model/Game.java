package com.example.reggi.gamebola.Model;

import android.util.Log;

import com.example.reggi.gamebola.Presenter;
import com.example.reggi.gamebola.View.MainActivity;

import java.util.ArrayList;

public class Game implements ListenerGame {
    protected ArrayList<Bola> bola;
    protected SensorReader sensorReader;
    protected Timer timer;
    protected ArrayList<Obstacle> obstacles;
    protected BolaStatic bolaStatic;
    protected int jmlhBola;
    protected int jmlhObs;
    protected Presenter p;

    public Game(MainActivity mainActivity, Presenter p){
        this.p = p;
        this.bola = new ArrayList<>();
        bola.add(new Bola(this,50)); //jika setting bola belum diisi maka otomatis bola berjumlah 1
        this.obstacles = new ArrayList<>();
        this.bolaStatic = new BolaStatic();
        this.sensorReader = new SensorReader(mainActivity, bola);
        this.timer = new Timer(this);
    }

    public void randomizedPosition(int width, int height){
        for (int i = 0; i<this.bola.size(); i++) {
            this.bola.get(i).randomPosition(width, height);
        }
        for (int i = 0; i<this.obstacles.size(); i++){
            this.obstacles.get(i).randomPosition(width, height);
        }
        this.bolaStatic.randomPosition(width, height);
    }

    @Override
    public void startGameTrue() {
        this.sensorReader.startGame = true;
    }

    @Override
    public void stopGameTrue(){
        this.sensorReader.startGame = false;
    }

    @Override
    public void startTimer(){
        this.timer.startTimer();
    }

    public void onBolaPositionChanged() {
        for (int i = 0; i<this.jmlhBola; i++) {
            this.p.drawBall(this.bola.get(i).getX(), this.bola.get(i).getY());
        }
    }

    public void startSensor(){
        this.sensorReader.start();
    }

    public ArrayList<Bola> getBola(){
        return this.bola;
    }

    @Override
    public ArrayList<Obstacle> getObstacles() {
        return this.obstacles;
    }

    @Override
    public BolaStatic getBolaStatic() {
        return this.bolaStatic;
    }


    public void updateScore(){
        this.p.increaseScore();
    }

    public void decreaseScore(){
        this.p.decreaseScore();
    }

    public void updateTimeToPresenter(String time){
        this.p.updateTimer(time);
    }

    public void timerEnd(boolean end){
        this.p.timerEnd(end);
    }

    @Override
    public void setJmlBola(int x) {
        this.jmlhBola = x;
        this.bola.clear();
        Log.d("coba",this.jmlhBola+" bola");
        Log.d("coba",this.jmlhObs+" obs");
        for (int i = 0; i<this.jmlhBola; i++) {
            this.bola.add(new Bola(this,50));
        }
    }

    @Override
    public void setJmlObstacle(int x) {
        this.jmlhObs = x;
        this.obstacles.clear();
        for (int i = 0; i<jmlhObs; i++){
            this.obstacles.add(new Obstacle());
        }
    }
}
