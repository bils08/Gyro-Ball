package com.example.reggi.gamebola.Model;

import android.os.CountDownTimer;

import com.example.reggi.gamebola.Presenter;

public class Timer {
    protected String printTime;
    protected Game game;
    protected long timeInMillis;
    protected CountDownTimer timer;

    public Timer(Game game) {
        this.game = game;
        this.timeInMillis = 0;
    }

    public void startTimer() {
        timeInMillis = 16000; //waktu kita set menjadi 15 detik per game
        timer = new CountDownTimer(timeInMillis, 1000) {
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

    public void updateTime() {
        int seconds = (int) (timeInMillis / 1000) % 60;
        this.printTime = "Time : " + seconds;
        this.game.updateTimeToPresenter(this.printTime);
    }

    public void stopTimer() {
        timer.cancel();
    }


    public void timerFinish() {
        this.game.timerEnd(true);
    }
}
