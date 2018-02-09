package com.shareit.shareit;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

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
        int delay = gifDrawable.getDuration() + 500;  // El tiempo que se muestre el splash es el que dure el gif mas un poco de margen
        gifDrawable.addAnimationListener(this);  // Se añade un escuchado al gif para saber cuando se ejecuta entero
        new Handler().postDelayed(() -> gifDrawable.reset(), 500);  // Se inicia el gif con un delay

        // Se cierra este activity cuando finalice el gif
        new Handler().postDelayed(() -> close(), delay);
    }

    private void close() {
        Bundle b = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.alpha_aparicion, R.anim.alpha_desaparicion).toBundle();
        Intent i = new Intent(getApplicationContext(), SignIn.class);
        startActivity(i, b);
        finish();
    }

    @Override
    public void onAnimationCompleted(final int loopNumber) {
        gifDrawable.stop();  // Se detiene el gif una vez se haya ejecutado por primera vez
    }
}
