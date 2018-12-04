package com.example.reggi.gamebola.Model;

public interface ListenerGame {
    public void startSensor();
    public Bola getBola();
    public void randomizedPosition(int x, int y);
    public void startGameTrue();
    public void stopGameTrue();
    public void startTimer();
    public void timerEnd(boolean end);
}
