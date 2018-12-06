package com.example.reggi.gamebola;

import com.example.reggi.gamebola.Model.Bola;
import com.example.reggi.gamebola.Model.Game;
import com.example.reggi.gamebola.Model.ListenerGame;
import com.example.reggi.gamebola.View.MainActivity;

public class Presenter {
    protected MainActivity mainActivity;
    protected ListenerGame listenerGame;

    public Presenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.listenerGame = new Game(mainActivity, this);
        this.listenerGame.startSensor();
    }

    public Bola getBola(){
        return this.listenerGame.getBola();
    }

    public void getPosition(int x, int y){
        this.listenerGame.randomizedPosition(x,y);
    }

    public void drawBall(float x, float y){
        this.mainActivity.draw(x,y);
    }

    public void startGame(){
        this.listenerGame.startGameTrue();
    }

    public void stopGame(){
        this.listenerGame.stopGameTrue();
    }

    public void increaseScore(){
        this.mainActivity.increaseScore();
    }

    public void decreaseScore(){
        this.mainActivity.decreaseScore();
    }

    public void updateTimer(String time){
        this.mainActivity.updateTimeToFragment(time);
    }

    public void startTimer(){
        this.listenerGame.startTimer();
    }

    public void timerEnd(boolean end){
        this.mainActivity.timeEnd(end);
    }

    public void jmlBola(int x){
        this.listenerGame.setJmlBola(x);
    }

}
