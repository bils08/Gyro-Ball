package com.example.reggi.gamebola.View;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reggi.gamebola.Model.Bola;
import com.example.reggi.gamebola.Model.BolaStatic;
import com.example.reggi.gamebola.Model.Obstacle;
import com.example.reggi.gamebola.Presenter;
import com.example.reggi.gamebola.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListenerFragmentGame, ListenerHighScore, ListenerSetting, NavigationView.OnNavigationItemSelectedListener {
    protected TextView gameTitle;
    protected Button game, exit;
    protected FragmentGame fragmentGame;
    protected FragmentHighScore fragmentHighScore;
    protected FragmentSetting fragmentSetting;
    protected FragmentManager fragmentManager;
    protected Presenter p;
    protected DrawerLayout drawer;
    protected RelativeLayout myLayout;
    protected AnimationDrawable animationDrawable;
    protected NavigationView navigationView;
    protected SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_open, R.string.navigation_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.game = findViewById(R.id.btnNewGame);
        this.exit = findViewById(R.id.btnExit);
        this.gameTitle = findViewById(R.id.title);
        Typeface customFont= null;
        Typeface customFont2= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            customFont = getResources().getFont(R.font.dimis);
            customFont2=getResources().getFont(R.font.dimitri);
        }
        this.gameTitle.setTypeface(customFont);
        this.game.setTypeface(customFont2);
        this.exit.setTypeface(customFont2);

        this.p = new Presenter(this);
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentGame = FragmentGame.newInstance(this, p);
        this.fragmentSetting = FragmentSetting.newInstance(this);
        this.fragmentHighScore = FragmentHighScore.newInstance(this, this.getLayoutInflater());

        this.BuatTabel();

        this.game.setOnClickListener(this);
        this.exit.setOnClickListener(this);

        myLayout=findViewById(R.id.myLayout);
        animationDrawable=(AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
    }

    @Override
    public void onClick(View v) {
        if(v == game){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, this.fragmentGame).commit();
            this.gameTitle.setVisibility(View.GONE);
            this.game.setVisibility(View.GONE);
            this.exit.setVisibility(View.GONE);
            navigationView.setCheckedItem(R.id.game);
        }
        else if(v == exit){
            finish();
            System.exit(1);
        }
    }

    public void BuatTabel(){
        sql = openOrCreateDatabase("Gyro Ball",MODE_PRIVATE,null); //database
        sql.execSQL("CREATE TABLE IF NOT EXISTS Highscore(Score Integer);");
    }

    public ArrayList<Bola> getBola(){
        return p.getBola();
    }

    @Override
    public ArrayList<Obstacle> getObstacles() {
        return p.getObstacles();
    }

    @Override
    public BolaStatic getBolaStatic() {
        return p.getBolaStatic();
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

    @Override
    public void addScoreToAdapter(int x) {
        this.fragmentHighScore.adapter.addLine(x);
    }

    public void increaseScore(){
        this.fragmentGame.score++;
    }

    public void decreaseScore(){
        this.fragmentGame.score--;
    }

    public void updateTimeToFragment(String time){
        this.fragmentGame.timer.setText(time);
    }

    public void timeEnd(boolean end){
        this.fragmentGame.finishGame(end);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.game:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, this.fragmentGame).commit();
                break;
            case R.id.highscore:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, this.fragmentHighScore).commit();
                break;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, this.fragmentSetting).commit();
                break;
            case R.id.exit:
                finish();
                System.exit(1);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeToGame(){
        navigationView.setCheckedItem(R.id.game);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, this.fragmentGame).commit();
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.p.setJmlObstacle(difficulty);
    }

    @Override
    public void setJumlahBola(int jumlahBola) {
        this.p.jmlBola(jumlahBola);
    }
}
