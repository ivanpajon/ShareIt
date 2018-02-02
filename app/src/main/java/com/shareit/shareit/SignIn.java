package com.shareit.shareit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void accederConGoogle(View v){

    }
    public void accederConFacebook(View v){

    }
    public void accederConEmail(View v){
        Intent i = new Intent(this, SignInEmail.class);
        startActivity(i);
        finish();
    }
}
