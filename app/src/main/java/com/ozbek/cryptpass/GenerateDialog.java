package com.ozbek.cryptpass;

import androidx.annotation.NonNull;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Random;

class GenerateDialog extends Dialog {
    private EditText usernameEditText, passwordEditText, hintEditText;
    private CheckBox passwordABCD, passwordabcd, password0123, passwordSymbols;
    private RadioGroup password_radio_container;
    private RadioButton radio4, radio8, radio12, radio16;
    private Button generatePassword, saveEntry;

    private String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private String numbers = "0123456789";
    private String characters = "!@#$%^&*()";

    GenerateDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_NoTitleBar_Fullscreen);
        setContentView(R.layout.dialog_layout);

        usernameEditText = findViewById(R.id.username_field);
        passwordEditText = findViewById(R.id.password_field);
        hintEditText = findViewById(R.id.hint_field);

        passwordABCD = findViewById(R.id.upp_checkbox);
        passwordabcd = findViewById(R.id.low_checkbox);
        password0123 = findViewById(R.id.num_checkbox);
        passwordSymbols = findViewById(R.id.sym_checkbox);

        password_radio_container = findViewById(R.id.radiobutton_password_container);

        radio4 = findViewById(R.id.four);
        radio8 = findViewById(R.id.eight);
        radio12 = findViewById(R.id.twelve);
        radio16 = findViewById(R.id.sixteen);

        generatePassword = findViewById(R.id.btn_password_generate);
        saveEntry = findViewById(R.id.btn_save);

        generatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {passwordEditText.setText(generatedPassword());}
        });

        saveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
    }

    private String generatedPassword(){

        /*
        * There is problem with this.  The method returns a string even if the user makes no selection.
        * Create a fail-safe for a case like that.
        * */


        int length = 0;
        StringBuilder generatedString = new StringBuilder();
        Random rand = new Random();

        if(radio4.isChecked()){length = 4;}
        else if(radio8.isChecked()){length = 8;}
        else if(radio12.isChecked()){length = 12;}
        else if(radio16.isChecked()){length = 16;}
        if(length == 0){throw new IllegalArgumentException("You must select a password size!");}

        String totalCharacters = "";
        if(passwordABCD.isChecked()){totalCharacters += capitalLetters;}
        if(passwordabcd.isChecked()){totalCharacters += lowercaseLetters;}
        if(password0123.isChecked()){totalCharacters += numbers;}
        if(passwordSymbols.isChecked()){totalCharacters += characters;}
        if (totalCharacters.isEmpty()) {throw new IllegalArgumentException("You must select at least 1 password characteristic.");}

        for(int i = 0; i < length; i++){generatedString.append(totalCharacters.charAt(rand.nextInt(totalCharacters.length())));}

        return generatedString.toString();
    }
}
