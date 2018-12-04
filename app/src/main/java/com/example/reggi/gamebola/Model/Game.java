package com.example.reggi.gamebola.Model;

import com.example.reggi.gamebola.Presenter;
import com.example.reggi.gamebola.View.MainActivity;

public class Game implements ListenerGame {
    protected Bola bola;
    protected SensorReader sensorReader;
    protected Timer timer;
    protected Presenter p;

    public Game(MainActivity mainActivity, Presenter p){
        this.p = p;
        this.bola = new Bola(this,50);
        this.sensorReader = new SensorReader(mainActivity, bola);
        this.timer = new Timer(this);
    }

    public void randomizedPosition(int width, int height){
        this.bola.randomPosition(width,height);
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
        this.p.drawBall(this.bola.getX(), this.bola.getY());
    }

    public void startSensor(){
        this.sensorReader.start();
    }

    public Bola getBola(){
        return this.bola;
    }

    public void updateScore(){
        this.p.increaseScore();
    }

    public void updateTimeToPresenter(String time){
        this.p.updateTimer(time);
    }

    public void timerEnd(boolean end){
        this.p.timerEnd(end);
    }


}
