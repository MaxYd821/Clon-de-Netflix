package com.example.clondenetflix;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearCuentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnComienza = findViewById(R.id.btnComienza);
        ImageView ivCerrar = findViewById(R.id.ivCerrar);

        btnComienza.setOnClickListener(v -> {
            Intent intent = new Intent(CrearCuentaActivity.this, CarteleraActivity.class);
            startActivity(intent);
        });
        ivCerrar.setOnClickListener(v->{
            Intent intent = new Intent(CrearCuentaActivity.this, InicioSesionActivity.class);
            startActivity(intent);
        });
    }
}