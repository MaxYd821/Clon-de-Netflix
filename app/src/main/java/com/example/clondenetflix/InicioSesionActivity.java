package com.example.clondenetflix;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InicioSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        Button btnSuscribir = findViewById(R.id.btnSuscribir);
        EditText etEmail = findViewById(R.id.etEmail);

        EditText etPassword = findViewById(R.id.etPassword);

        btnIniciarSesion.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if(email.equals("admin") && password.equals("admin")){
                Intent intent = new Intent(InicioSesionActivity.this, CarteleraActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        btnSuscribir.setOnClickListener(v -> {
            Intent intent = new Intent(InicioSesionActivity.this, CrearCuentaActivity.class);
            startActivity(intent);
        });
    }
}