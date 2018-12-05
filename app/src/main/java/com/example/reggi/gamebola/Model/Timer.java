package com.example.reggi.gamebola.Model;

import android.os.CountDownTimer;

import com.example.reggi.gamebola.Presenter;

public class Timer {
    protected String printTime;
    protected Game game;

    public Timer(Game game){
        this.game = game;
    }

    protected long timeInMillis = 0;
    CountDownTimer timer;
    public void startTimer(){
        timeInMillis = 11000; //waktu kita set menjadi 10 detik per game
        timer = new CountDownTimer(timeInMillis,1000) {
            @Override
            public void onTick(long s) {
                timeInMillis = s;
                updateTime();
            }

            @Override
            public void onFinish() {
                timerFinish();
            }
        }.start();
    }

    public void updateTime(){
        int seconds = (int) (timeInMillis/1000) % 60;
        this.printTime = "Time : " + seconds;
        this.game.updateTimeToPresenter(this.printTime);
    }

    public void stopTimer(){
        timer.cancel();
    }


    public void timerFinish(){
        this.game.timerEnd(true);
    }
}
