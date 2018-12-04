package com.example.reggi.gamebola.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.reggi.gamebola.R;

public class DialogBox extends DialogFragment {
    protected FragmentGame fragmentGame;

    public DialogBox(FragmentGame fragmentGame){
        this.fragmentGame = fragmentGame;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialogMessage) ;
        builder.setTitle(R.string.dialogTitle);
        builder.setPositiveButton(R.string.dialogBtnYES, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton(R.string.dialogBtnNO, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        return builder.create();
    }
}
