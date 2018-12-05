package com.example.reggi.gamebola.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.reggi.gamebola.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHighScore extends Fragment {
    protected ListenerHighScore listener;
    protected ListView listView;
    protected Adapter adapter;


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
        this.listView = result.findViewById(R.id.listView);
        this.listView.setAdapter(adapter);
        return result;
    }

}
