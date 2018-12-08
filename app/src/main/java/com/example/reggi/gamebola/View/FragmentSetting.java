package com.example.reggi.gamebola.View;


import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.reggi.gamebola.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetting extends Fragment implements View.OnClickListener {

    protected ListenerSetting listener;
    protected TextView difficult, jumlahBola;
    protected Button apply;
    protected EditText difficulty, jmlBola;
    protected RelativeLayout myLayout;
    protected AnimationDrawable animationDrawable;

    public FragmentSetting() {
        // Required empty public constructor
    }

    public static FragmentSetting newInstance(ListenerSetting listener) {
        FragmentSetting result = new FragmentSetting();
        result.listener = listener;
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_settings, container, false);

        this.apply = result.findViewById(R.id.apply);
        this.difficulty = result.findViewById(R.id.difficulty);
        this.jmlBola = result.findViewById(R.id.jumlahBola);
        this.difficult=result.findViewById(R.id.tvDifficulty);
        this.jumlahBola=result.findViewById(R.id.tvJumlahBola);

        Typeface myCustomeFont= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            myCustomeFont = getResources().getFont(R.font.dimitri);
        }
        this.apply.setTypeface(myCustomeFont);
        this.difficult.setTypeface(myCustomeFont);
        this.jumlahBola.setTypeface(myCustomeFont);
        this.apply.setOnClickListener(this);

        myLayout=result.findViewById(R.id.settings);
        animationDrawable=(AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

        return result;
    }

    @Override
    public void onClick(View v) {
        if (v == apply) {
            try {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(myLayout.getWindowToken(), 0);
            }catch (Exception e){
                e.printStackTrace();
            }
            if (this.difficulty.getText().length() == 0) {
                this.listener.setDifficulty(0);
            } else {
                this.listener.setDifficulty(Integer.parseInt(this.difficulty.getText().toString()));
            }
            if (this.jmlBola.getText().length() == 0){
                this.listener.setJumlahBola(1);
            }
            else{
                int input = Integer.parseInt(this.jmlBola.getText().toString());
                this.listener.setJumlahBola(input);
            }
            MainActivity ma = (MainActivity)getActivity();
            ma.changeToGame();
        }
    }

}
