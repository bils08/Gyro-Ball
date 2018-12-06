package com.example.reggi.gamebola.View;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reggi.gamebola.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHighScore extends Fragment {
    protected ListenerHighScore listener;
    protected ListView listView;
    protected Adapter adapter;
    protected TextView tvHighScore,tvRank,tvScore;


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
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_high_score,null);
        this.tvHighScore=result.findViewById(R.id.showSkor);
        this.tvRank=result.findViewById(R.id.rank);
        this.tvScore=result.findViewById(R.id.score);
        Typeface myCustomeFont= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            myCustomeFont = getResources().getFont(R.font.fipps_regular);
        }
        this.tvHighScore.setTypeface(myCustomeFont);
        this.tvRank.setTypeface(myCustomeFont);
        this.tvScore.setTypeface(myCustomeFont);
        this.listView = result.findViewById(R.id.listView);
        this.listView.setAdapter(adapter);
        return result;
    }

}
