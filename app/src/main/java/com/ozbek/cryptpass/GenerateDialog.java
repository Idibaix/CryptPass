package com.ozbek.cryptpass;

import androidx.annotation.NonNull;
import android.app.Dialog;
import android.content.Context;;

class GenerateDialog extends Dialog {

    //The dialog shows but it covers the whole screen and its black.  Fix it

    GenerateDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_NoTitleBar_Fullscreen);
        setContentView(R.layout.dialog_layout);
    }
}
