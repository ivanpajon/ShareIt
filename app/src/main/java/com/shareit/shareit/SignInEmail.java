package com.shareit.shareit;


import android.content.Intent;
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

        etUser = findViewById(R.id.etEmailSignIn);
        etPassword = findViewById(R.id.etPasswordSignIn);
    }

    public void signIn(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}
