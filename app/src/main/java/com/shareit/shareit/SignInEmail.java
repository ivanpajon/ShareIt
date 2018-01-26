package com.shareit.shareit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SignInEmail extends AppCompatActivity {
    private EditText etUser;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_email);

        etUser = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void Acceder(View v){

    }
}
