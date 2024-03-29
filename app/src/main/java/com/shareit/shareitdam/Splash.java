package com.shareit.shareitdam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Splash extends AppCompatActivity implements AnimationListener {
    private GifImageView gifSplash;
    private GifDrawable gifDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Se oculta el status bar antes de cargar el layout
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        gifSplash = findViewById(R.id.gifSplash);
        gifDrawable = (GifDrawable) gifSplash.getDrawable();
        gifDrawable.addAnimationListener(this);  // Se añade un escuchado al gif para saber cuando se ejecuta entero
        new Handler().postDelayed(() -> gifDrawable.reset(), 500);  // Se inicia el gif con un delay
    }

    @Override
    public void onAnimationCompleted(final int loopNumber) {
        gifDrawable.stop();  // Se detiene el gif una vez se haya ejecutado por primera vez
        Bundle b = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.alpha_aparicion, R.anim.alpha_desaparicion).toBundle();
        Intent i = new Intent(getApplicationContext(), SignIn.class);
        startActivity(i, b);
        finish();
    }
}
