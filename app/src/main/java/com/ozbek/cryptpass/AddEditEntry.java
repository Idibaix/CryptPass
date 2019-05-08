package com.ozbek.cryptpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Random;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

public class AddEditEntry extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.ozbek.cryptpass.EXTRA_USERNAME";
    public static final String EXTRA_HINT = "com.ozbek.cryptpass.EXTRA_HINT";
    public static final String EXTRA_PASSWORD = "com.ozbek.cryptpass.EXTRA_PASSWORD";
    public static final String EXTRA_ID = "com.ozbek.cryptpass.EXTRA_ID";

    private EditText usernameEditText, passwordEditText, hintEditText;
    private CheckBox passwordABCD, passwordabcd, password0123, passwordSymbols;
    private RadioButton radio4, radio8, radio12, radio16;
    private Button generatePassword, saveEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        usernameEditText = findViewById(R.id.username_field);
        passwordEditText = findViewById(R.id.password_field);
        hintEditText = findViewById(R.id.hint_field);

        passwordABCD = findViewById(R.id.upp_checkbox);
        passwordabcd = findViewById(R.id.low_checkbox);
        password0123 = findViewById(R.id.num_checkbox);
        passwordSymbols = findViewById(R.id.sym_checkbox);

        radio4 = findViewById(R.id.four);
        radio8 = findViewById(R.id.eight);
        radio12 = findViewById(R.id.twelve);
        radio16 = findViewById(R.id.sixteen);

        generatePassword = findViewById(R.id.btn_password_generate);
        saveEntry = findViewById(R.id.btn_save);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Entry");
            usernameEditText.setText(intent.getStringExtra(EXTRA_USERNAME));
            passwordEditText.setText(intent.getStringExtra(EXTRA_PASSWORD));
            hintEditText.setText(intent.getStringExtra(EXTRA_HINT));
        }
        else{setTitle("Add Entry");}

        generatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {passwordEditText.setText(generatedPassword());}});

        saveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(EXTRA_USERNAME, usernameEditText.getText().toString());
                data.putExtra(EXTRA_HINT, hintEditText.getText().toString());
                data.putExtra(EXTRA_PASSWORD, passwordEditText.getText().toString());

                int id = getIntent().getIntExtra(EXTRA_ID, -1);

                if(id != -1){data.putExtra(EXTRA_ID, id);}

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    private String generatedPassword(){

        int length = 0;
        StringBuilder generatedString = new StringBuilder();
        Random rand = new Random();

        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String characters = "!@#$%^&*()";

        if(radio4.isChecked()){length = 4;}
        else if(radio8.isChecked()){length = 8;}
        else if(radio12.isChecked()){length = 12;}
        else if(radio16.isChecked()){length = 16;}

        String totalCharacters = "";
        if(passwordABCD.isChecked()){totalCharacters += capitalLetters;}
        if(passwordabcd.isChecked()){totalCharacters += lowercaseLetters;}
        if(password0123.isChecked()){totalCharacters += numbers;}
        if(passwordSymbols.isChecked()){totalCharacters += characters;}
        
        if((!(totalCharacters.trim().isEmpty())) && ((length > 0))){
            for(int i = 0; i < length; i++){generatedString.append(totalCharacters.charAt(rand.nextInt(totalCharacters.length())));}
            return generatedString.toString();
        }
        else {Toast.makeText(this, "Not a valid password!", Toast.LENGTH_SHORT).show();}

        return null;
    }

    void unsafeMethod() {throw new UnsupportedOperationException("You shouldn't call this!");}

    void logWithStaticAPI() {
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage("User made an action").build());
        Sentry.getContext().setUser(new UserBuilder().setEmail("hello@sentry.io").build());
        Sentry.capture("This is a test");

        try {unsafeMethod();}
        catch (Exception e) {Sentry.capture(e);}
    }
}
