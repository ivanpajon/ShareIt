package com.shareit.shareit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignIn extends AppCompatActivity {
    private static final int RC_EMAIL_SIGN_IN = 100;
    private static final int RC_GOOGLE_SIGN_IN = 200;
    private Button btnSignInEmail;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private SignInButton btnSignInGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private LoginManager mLoginManager;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Configure layout elements
        btnSignInEmail = findViewById(R.id.btnGoSignInEmail);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);

        // Configure Firebase modules
        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnSignInGoogle.setOnClickListener((v) -> accederConGoogle());  // Se le añade un listener al boton ya que sino no detecta el click

        // Configure Facebook Sign In
        mLoginManager = LoginManager.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.btnSignInFacebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override public void onSuccess(LoginResult loginResult) {accederConFacebook(loginResult);}
            @Override public void onCancel() {}
            @Override public void onError(FacebookException error) {}
        });

        // Importante para evitar problemas de accesibilidad con la contraseña
        ViewCompat.setImportantForAccessibility(btnSignInEmail.getRootView(), ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO);
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();  // Se obtiene el usuario actual

        // Si hay algun usuario con la sesion iniciada se accede a su perfil
        if (currentUser != null) {
            Intent i = new Intent(this, Home.class);
            startActivity(i);
            finish();
        }
        // Si no lo hay significa que es la primera vez que se abre la app
        // o que se ha cerrado sesion implicitamente, por lo que se limpia el inicio de sesion
        else {
            mGoogleSignInClient.signOut();
            mLoginManager.logOut();
            mAuth.signOut();
        }
    }

    // Se inicia el intento de iniciar sesion con Google
    private void accederConGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    // TODO: Implementar un progress bar
    // Se procesa el intento de iniciar sesion con Facebook
    private void accederConFacebook(LoginResult loginResult) {
        if (loginResult.getAccessToken() != null) {
            //pbSignIn.setVisibility(getCurrentFocus().VISIBLE);
            handleFacebookAccessToken(loginResult.getAccessToken());
        }
    }

    // Iniciar sesion con email y contraseña
    public void accederConEmail(View v) {
        Intent i = new Intent(this, SignInEmail.class);
        startActivity(i);
        finish();
    }

    // Se procesa el token recibido del inicio de sesion con Facebook
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener((task) -> signInOnCompleteFacebook(task));
    }

    // TODO: Implementar un progress bar
    // Cuando se completa la accion de iniciar sesion para Email y Google
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
            //pbSignIn.setVisibility(View.GONE);
        }
    }

    // TODO: Implementar un progress bar
    // Cuando se completa la accion de iniciar sesion para Facebook
    private void signInOnCompleteFacebook(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {  // Si se ha iniciado sesion correctamente
            //pbSignIn.setVisibility(getCurrentFocus().GONE);
            currentUser = mAuth.getCurrentUser();  // Se obtiene el usuario con el que se ha iniciado sesion

            Toast.makeText(getApplicationContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();

            // Se abre el home de la aplicacion
            Intent i = new Intent(this, Home.class);
            startActivity(i);
            finish();
        }
        else {  // Se muestra por que ha fallado el inicio de sesion
            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            //pbSignIn.setVisibility(View.GONE);
        }
    }

    // TODO: Implementar un progress bar
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);  // Se obtienen los datos del inicio de sesion con google
            if (result.isSuccess()) {  // Si se inicio sesion correctamente
                //pbSignIn.setVisibility(View.VISIBLE);  // Se muestra el progressbar
                // Se recupera la cuenta google y se inicia sesion con ella
                GoogleSignInAccount account = result.getSignInAccount();
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                mAuth.signInWithCredential(credential).addOnCompleteListener((task) -> signInOnComplete(task));
            }
            else {  // Si fallo el inicio de sesion
                Toast.makeText(getApplicationContext(), "Signing in failed", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == RC_EMAIL_SIGN_IN) {
            Toast.makeText(getApplicationContext(), "Please, verify your email before sign in", Toast.LENGTH_SHORT).show();
        }
        else {  // Process Facebook login
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
