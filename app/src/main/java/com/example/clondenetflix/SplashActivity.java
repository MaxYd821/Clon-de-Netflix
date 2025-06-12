package com.example.clondenetflix;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // milisegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logoNetflix);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.scale_in);
        logo.setVisibility(View.VISIBLE);
        logo.startAnimation(fadeIn);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, InicioSesionActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}