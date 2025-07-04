package com.example.clondenetflix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.clondenetflix.Entidades.Usuario;
import com.example.clondenetflix.Services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InicioSesionActivity extends AppCompatActivity {

    SharedPreferences preferences;

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

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(InicioSesionActivity.this, PerfilesActivity.class));
            finish();
        }

        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        Button btnSuscribir = findViewById(R.id.btnSuscribir);
        EditText etEmail = findViewById(R.id.etEmail);

        EditText etPassword = findViewById(R.id.etPassword);

        btnIniciarSesion.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://681161953ac96f7119a469f6.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService userService = retrofit.create(UserService.class);
            userService.getUsuarios().enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    if (response.isSuccessful()) {
                        List<Usuario> usuarios = response.body();
                        boolean encontrado = false;
                        for (Usuario usuario : usuarios) {
                            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                                encontrado = true;
                                break;
                            }
                        }
                        if (encontrado) {
                            SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.apply();
                            Intent intent = new Intent(InicioSesionActivity.this, PerfilesActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(InicioSesionActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(InicioSesionActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable throwable) {
                    Toast.makeText(InicioSesionActivity.this, "Error de conexión: ", Toast.LENGTH_LONG).show();
                }
            });
        });

        btnSuscribir.setOnClickListener(v -> {
            Intent intent = new Intent(InicioSesionActivity.this, CrearCuentaActivity.class);
            startActivity(intent);
        });
    }
}