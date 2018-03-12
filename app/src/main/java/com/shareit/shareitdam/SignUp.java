package com.shareit.shareitdam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shareit.shareitdam.model.Usuario;

public class SignUp extends AppCompatActivity {
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private Usuario newUser;

    private EditText etEmail, etPassword;
    private Button btnSignUp;
    private ProgressBar pbSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmailSignUp);
        etPassword = findViewById(R.id.etPasswordSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        pbSignUp = findViewById(R.id.pbSignUp);

        // Importante para evitar problemas de accesibilidad con la contraseña
        ViewCompat.setImportantForAccessibility(btnSignUp.getRootView(), ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (currentUser != null) {
            currentUser = mAuth.getCurrentUser();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void signUp (View v) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        newUser = new Usuario();  // Se crea un objeto Usuario

        // Se comprueba que esten rellenos todos los campos
        if (!email.isEmpty() && !password.isEmpty()) {
            if (password.length() < 6) {  // Se comprueba que la contraseña tenga al menos 6 caracteres
                Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            }
            else {
                pbSignUp.setVisibility(v.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task) -> signUpOnComplete(task));  // Se registra el nuevo usuario
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "You must fill all the fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void signUpOnComplete(@NonNull Task<AuthResult> task) {
        pbSignUp.setVisibility(View.GONE);
        if (task.isSuccessful()) {
            currentUser = mAuth.getCurrentUser();  // Se obtiene el usuario que se acaba de crear
            currentUser.sendEmailVerification().addOnCompleteListener((t) -> emailOnComplete(t));  // Enviar mensaje de confirmacion
        }
        else {
            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void emailOnComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            Toast.makeText(getApplicationContext(), "Please, verify your email before sign in", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Signing up failure: " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
