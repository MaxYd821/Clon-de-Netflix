package com.example.clondenetflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class PerfilDetalleActivity extends AppCompatActivity {

    ImageView ivIconHomePerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);

        ImageView ivPerfil = findViewById(R.id.ivPerfilDetalle);
        TextView tvNombrePerfil = findViewById(R.id.tvNombrePerfil);
        ivIconHomePerfil = findViewById(R.id.ivIconHomePerfil);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombrePerfil");
        String fotoUrl = intent.getStringExtra("urlFoto");

        tvNombrePerfil.setText(nombre);
        if (fotoUrl != null && !fotoUrl.isEmpty()) {
            Glide.with(this).load(fotoUrl).into(ivPerfil);
        } else {
            ivPerfil.setImageResource(R.drawable.icon_miperfil);
        }

        ivIconHomePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}