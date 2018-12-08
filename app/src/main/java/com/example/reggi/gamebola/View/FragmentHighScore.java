package com.example.reggi.gamebola.View;


import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.reggi.gamebola.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHighScore extends Fragment {
    protected ListenerHighScore listener;
    protected ListView listView;
    protected Adapter adapter;
    protected TextView tvHighScore,tvRank,tvScore;
    protected RelativeLayout myLayout;
    protected AnimationDrawable animationDrawable;
    protected SQLiteDatabase sql;

    public FragmentHighScore() {
    }

    public static FragmentHighScore newInstance(ListenerHighScore listener, LayoutInflater inflater){
        FragmentHighScore result = new FragmentHighScore();
        result.listener = listener;
        result.adapter = Adapter.newInstance(inflater);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_high_score,null);
        this.tvHighScore=result.findViewById(R.id.showSkor);
        this.tvRank=result.findViewById(R.id.rank);
        this.tvScore=result.findViewById(R.id.score);

        sql = getActivity().openOrCreateDatabase("Gyro Ball",MODE_PRIVATE,null);

        Typeface myCustomeFont= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            myCustomeFont = getResources().getFont(R.font.fipps_regular);
        }
        this.tvHighScore.setTypeface(myCustomeFont);
        this.tvRank.setTypeface(myCustomeFont);
        this.tvScore.setTypeface(myCustomeFont);
        this.listView = result.findViewById(R.id.listView);

        ArrayList<Integer> listScore = new ArrayList<>();
        Cursor resultSet = sql.rawQuery("Select * from Highscore",null);
        int n = resultSet.getCount();
        if (n > 0){
            resultSet.moveToFirst();
        }
        for (int i = 0; i < n; i++){
            listScore.add(resultSet.getInt(0));
            resultSet.moveToNext();
        }
        adapter.setData(listScore);

        this.listView.setAdapter(adapter);

        myLayout=result.findViewById(R.id.scoreFragment);
        animationDrawable=(AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        return result;
    }

}
