package com.example.reggi.gamebola.View;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.reggi.gamebola.Model.Bola;
import com.example.reggi.gamebola.Presenter;
import com.example.reggi.gamebola.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListenerFragmentGame {
    protected TextView gameTitle;
    protected Button game, exit;
    protected FragmentGame fragmentGame;
    protected FragmentManager fragmentManager;
    protected Presenter p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.game = findViewById(R.id.btnNewGame);
        this.exit = findViewById(R.id.btnExit);
        this.gameTitle = findViewById(R.id.title);

        this.p = new Presenter(this);
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentGame = FragmentGame.newInstance(this, p);

        this.game.setOnClickListener(this);
        this.exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == game){
            FragmentTransaction transaction = this.fragmentManager.beginTransaction();
            transaction.replace(R.id.container, this.fragmentGame);
            transaction.commit();
            this.gameTitle.setVisibility(View.GONE);
            this.game.setVisibility(View.GONE);
            this.exit.setVisibility(View.GONE);
        }
        else if(v == exit){
            finish();
            System.exit(1);
        }
    }

    public Bola getBola(){
        return p.getBola();
    }

    public void draw(float x, float y){
        this.fragmentGame.drawBall();
    }

    public void startGameTrue(){
        this.p.startGame();
    }

    public void stopGameTrue(){
        this.p.stopGame();
    }

    public void startTimer(){
        this.p.startTimer();
    }

    public void increaseScore(){
        this.fragmentGame.score++;
    }

    public void updateTimeToFragment(String time){
        this.fragmentGame.timer.setText(time);
    }

    public void timeEnd(boolean end){
        this.fragmentGame.finishGame(end);
    }

}
