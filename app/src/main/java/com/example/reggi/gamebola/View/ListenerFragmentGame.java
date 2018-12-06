package com.example.reggi.gamebola.View;

import com.example.reggi.gamebola.Model.Bola;
import com.example.reggi.gamebola.Model.BolaStatic;
import com.example.reggi.gamebola.Model.Obstacle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ListenerFragmentGame {
    public ArrayList<Bola> getBola();
    public ArrayList<Obstacle> getObstacles();
    public BolaStatic getBolaStatic();
    public void draw(float x, float y);
    public void startGameTrue();
    public void stopGameTrue();
    public void startTimer();
    public void addScoreToAdapter(int x);

}
