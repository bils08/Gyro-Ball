package com.example.reggi.gamebola.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.reggi.gamebola.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetting extends Fragment implements View.OnClickListener {

    protected ListenerSetting listener;
    protected Button apply;
    protected EditText difficulty, jmlBola;

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
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_settings, container, false);

        this.apply = result.findViewById(R.id.apply);
        this.difficulty = result.findViewById(R.id.difficulty);
        this.jmlBola = result.findViewById(R.id.jumlahBola);
        this.apply.setOnClickListener(this);

        return result;
    }

    @Override
    public void onClick(View v) {
        if (v == apply) {
            if (this.difficulty.getText().length() == 0) {
                this.listener.setDifficulty(1);
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
        }
    }

}
