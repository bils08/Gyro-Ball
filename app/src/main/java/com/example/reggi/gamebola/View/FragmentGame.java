package com.example.reggi.gamebola.View;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reggi.gamebola.Model.Bola;
import com.example.reggi.gamebola.Model.BolaStatic;
import com.example.reggi.gamebola.Model.Obstacle;
import com.example.reggi.gamebola.Presenter;
import com.example.reggi.gamebola.R;

import java.util.ArrayList;
import java.util.Random;

public class FragmentGame extends Fragment implements View.OnClickListener, Switch.OnCheckedChangeListener {
    protected ListenerFragmentGame listenerFragmentGame;
    protected TextView timer, scoreText;
    protected Switch nightMode;
    protected Button newGame, exitGame;
    protected ImageView ivCanvas;
    protected Bitmap bitmap;
    protected Canvas canvas;
    protected Paint paint;
    protected Random r;
    protected PointF startPoint;
    protected Path path;
    protected int width, height, initiateRadius, score;
    protected Presenter p;
    protected ArrayList<Bola> movingBall;
    protected ArrayList<Obstacle> obstacles;
    protected BolaStatic bolaStatic;

    public FragmentGame() {
    }

    public static FragmentGame newInstance(ListenerFragmentGame listener, Presenter p) {
        FragmentGame result = new FragmentGame();
        result.listenerFragmentGame = listener;
        //result.jmlBola = 1;
        //result.jmlObstacle = 1;
        result.p = p;
        return result;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_game, null);
        this.timer = result.findViewById(R.id.time);
        this.scoreText = result.findViewById(R.id.score);
        this.ivCanvas = result.findViewById(R.id.canvas);
        this.newGame = result.findViewById(R.id.newGame);
        this.exitGame = result.findViewById(R.id.exitGame);
        this.nightMode = result.findViewById(R.id.nightMode);

        this.paint = new Paint();
        this.r = new Random();
        this.path = new Path();
        this.initiateRadius = 50;
        //Log.d("coba","bola : "+jmlBola);
        //Log.d("coba","obst : "+jmlObstacle);

        movingBall = listenerFragmentGame.getBola();

        obstacles = listenerFragmentGame.getObstacles();

        this.bolaStatic = listenerFragmentGame.getBolaStatic();
        //this.bola = new Bola[this.jmlhBola];

        Log.d("coba",movingBall.size()+" moving");
        Log.d("coba",obstacles.size()+" obst");
        Log.d("coba",bolaStatic+" static");

        this.newGame.setOnClickListener(this);
        this.exitGame.setOnClickListener(this);
        this.nightMode.setOnCheckedChangeListener(this);

        return result;
    }

    @Override
    public void onClick(View v) {
        if (v == newGame) {
            this.newGame.setTextColor(Color.BLACK);
            this.initiateCanvas();
            this.p.getPosition(width, height);
            this.listenerFragmentGame.startGameTrue();
            this.startTime();
        } else if (v == exitGame) {
            //this.startTime();
        }
        else if(v == exitGame){
            System.exit(1);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void initiateCanvas() {
        width = ivCanvas.getMeasuredWidth();
        height = ivCanvas.getMeasuredHeight();
        this.bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.ivCanvas.setImageBitmap(bitmap);
        this.canvas = new Canvas(bitmap);
    }

    public void resetGame() {
        this.listenerFragmentGame.stopGameTrue();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        ivCanvas.invalidate();
        this.timer.setText("TIME : -");
        this.scoreText.setText("SCORE : 0");
        this.listenerFragmentGame.addScoreToAdapter(this.score);
        this.score = 0;
    }

    public void finishGame(boolean finish) {
        if (finish) {
            Toast toast = Toast.makeText(getActivity(), "Time is up! Your last score is : " + String.valueOf(this.score), Toast.LENGTH_SHORT);
            this.resetGame();
            toast.show();
        }
    }

    public void drawStaticBall() {
        float x = bolaStatic.getX();
        float y = bolaStatic.getY();
        int radius = bolaStatic.getRadius();
        this.paint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, radius, paint);
        ivCanvas.invalidate();
    }

    public void drawObstacle() {
        for (int i = 0; i < this.obstacles.size(); i++) {
            int x = obstacles.get(i).getX();
            int y = obstacles.get(i).getY();
            this.paint.setColor(Color.RED);
            Rect rect = new Rect(x, y, x + 100, y + 100);
            canvas.drawRect(rect, paint);
            ivCanvas.invalidate();
        }
    }

    public void drawBall() {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        this.drawStaticBall();
        this.drawObstacle();
        for (int i = 0; i<movingBall.size(); i++) {
            float x = movingBall.get(i).getX();
            float y = movingBall.get(i).getY();
            int radius = movingBall.get(i).getRadius();
            this.paint.setColor(Color.BLUE);
            canvas.drawCircle(x, y, radius, paint);
        }
        ivCanvas.invalidate();
        this.updateScore();
    }

    public void updateScore() {
        this.scoreText.setText("SCORE : " + String.valueOf(score));
    }

    public void startTime() {
        this.listenerFragmentGame.startTimer();
    }
}
