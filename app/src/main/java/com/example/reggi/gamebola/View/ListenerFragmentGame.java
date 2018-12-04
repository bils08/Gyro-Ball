package com.example.reggi.gamebola.View;

import com.example.reggi.gamebola.Model.Bola;

public interface ListenerFragmentGame {
    public Bola getBola();
    public void draw(float x, float y);
    public void startGameTrue();
    public void stopGameTrue();
    public void startTimer();

}
