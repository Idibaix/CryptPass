package com.ozbek.cryptpass;

import androidx.annotation.NonNull;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;;

class GenerateDialog extends Dialog {

    GenerateDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_NoTitleBar_Fullscreen);
        setContentView(R.layout.dialog_layout);

        Button saveButton = findViewById(R.id.btn_save);
    }
}
