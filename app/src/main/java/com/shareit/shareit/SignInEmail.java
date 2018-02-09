package com.shareit.shareit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInEmail extends AppCompatActivity {
    private static final int RC_EMAIL_SIGN_UP = 100;
    private EditText etUser;
    private EditText etPassword;
    private Button btnSignInEmail, btnGoSignUp;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_email);

        // Configure Firebase modules
        mAuth = FirebaseAuth.getInstance();

        etUser = findViewById(R.id.etEmailSignIn);
        etPassword = findViewById(R.id.etPasswordSignIn);
        btnSignInEmail = findViewById(R.id.btnSignInEmail);
        btnGoSignUp = findViewById(R.id.btnGoSignUp);
    }

    // TODO: Iniciar sesion con Firebase
    public void signIn(View v){
        String email = etUser.getText().toString();
        String password = etPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {  // Se comprueba que no esten vacios los campos
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((task) -> signInOnComplete(task));
        }
        else {
            Toast.makeText(getApplicationContext(), "You must enter an email and a password", Toast.LENGTH_SHORT).show();
        }
    }

    // Inicia el activity para registrarse
    public void goSignUp(View v) {
        Intent i = new Intent(getApplicationContext(), SignUp.class);
        startActivityForResult(i, RC_EMAIL_SIGN_UP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_EMAIL_SIGN_UP) {

        }
    }

    // Cuando se completa la accion de iniciar sesion con email
    private void signInOnComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {  // Si se ha iniciado sesion correctamente
            currentUser = mAuth.getCurrentUser();  // Se obtiene el usuario con el que se ha iniciado sesion
            boolean emailVerified = currentUser.isEmailVerified();

            if (!emailVerified) {  // Hasta que no se verifica el correo no te deja iniciar sesion
                mAuth.signOut();
                Toast.makeText(getApplicationContext(), "First you must verify the email", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();

            // Se abre el home de la aplicacion
            Intent i = new Intent(this, Home.class);
            startActivity(i);
            finish();
        }
        else {  // Se muestra por que ha fallado el inicio de sesion
            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
