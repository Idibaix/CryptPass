package com.ozbek.cryptpass;

import androidx.annotation.NonNull;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;;

class GenerateDialog extends Dialog {
    EditText usernameEditText, passwordEditText;
    CheckBox usernameABCD, usernameabcd, username0123, usernameSymbols, passwordABCD, passwordabcd, password0123, passwordSymbols;
    RadioGroup password_radio_container;
    RadioButton radio4, radio8, radio12, radio16;
    Button generateUsername, generatePassword, saveEntry;

    GenerateDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_NoTitleBar_Fullscreen);
        setContentView(R.layout.dialog_layout);

        usernameEditText = findViewById(R.id.username_field);
        passwordEditText = findViewById(R.id.password_field);

        usernameABCD = findViewById(R.id.uppercase_checkbox);
        usernameabcd = findViewById(R.id.lowercase_checkbox);
        username0123 = findViewById(R.id.number_checkbox);
        usernameSymbols = findViewById(R.id.symbol_checkbox);

        passwordABCD = findViewById(R.id.upp_checkbox);
        passwordabcd = findViewById(R.id.low_checkbox);
        password0123 = findViewById(R.id.num_checkbox);
        passwordSymbols = findViewById(R.id.sym_checkbox);

        password_radio_container = findViewById(R.id.radiobutton_password_container);

        radio4 = findViewById(R.id.four);
        radio8 = findViewById(R.id.eight);
        radio12 = findViewById(R.id.twelve);
        radio16 = findViewById(R.id.sixteen);

        generateUsername = findViewById(R.id.btn_username_generate);
        generatePassword = findViewById(R.id.btn_password_generate);
        saveEntry = findViewById(R.id.btn_save);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
    }
}
