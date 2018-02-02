package com.shareit.shareit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInEmail extends AppCompatActivity {
    private static final int RC_EMAIL_SIGN_IN = 100;
    private EditText etUser;
    private EditText etPassword;
    private Button btnSignInEmail, btnGoSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_email);

        etUser = findViewById(R.id.etEmailSignIn);
        etPassword = findViewById(R.id.etPasswordSignIn);
        btnSignInEmail = findViewById(R.id.btnSignInEmail);
        btnGoSignUp = findViewById(R.id.btnGoSignUp);
    }

    public void signIn(View v){
        Intent i = new Intent(getApplicationContext(), Home.class);
        startActivity(i);
        finish();
    }

    // Inicia el activity para registrarse
    public void goSignUp(View v) {
        Intent i = new Intent(getApplicationContext(), SignUp.class);
        startActivityForResult(i, RC_EMAIL_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_EMAIL_SIGN_IN) {

        }
    }
}
