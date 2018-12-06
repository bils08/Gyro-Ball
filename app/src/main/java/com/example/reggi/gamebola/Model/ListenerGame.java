package com.example.reggi.gamebola.Model;

import java.util.ArrayList;

public interface ListenerGame {
    public void startSensor();
    public ArrayList<Bola> getBola();
    public ArrayList<Obstacle> getObstacles();
    public BolaStatic getBolaStatic();
    public void randomizedPosition(int x, int y);
    public void startGameTrue();
    public void stopGameTrue();
    public void startTimer();
    public void timerEnd(boolean end);
    public void setJmlBola(int x);
    public void setJmlObstacle(int x);
}
