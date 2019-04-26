package com.ozbek.cryptpass;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class GenerateDialog extends Dialog implements View.OnClickListener {

    public GenerateDialog(@NonNull Context context) {super(context);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
    }

    @Override
    public void onClick(View v) {}
}
