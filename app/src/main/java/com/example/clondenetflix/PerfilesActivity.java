package com.example.clondenetflix;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clondenetflix.Adapters.PerfilAdapter;
import com.example.clondenetflix.Entidades.Perfil;
import com.example.clondenetflix.Entidades.Usuario;
import com.example.clondenetflix.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PerfilesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PerfilAdapter perfilAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);

        recyclerView = findViewById(R.id.recyclerPerfiles);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        UserService apiService = ApiClient.getRetrofit().create(UserService.class);
        Call<Usuario> call = apiService.getUsuarioPorId(3);  // 🔥 Asegúrate de tener este endpoint

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Perfil> perfiles = response.body().getPerfiles();
                    perfilAdapter = new PerfilAdapter(PerfilesActivity.this, perfiles);
                    recyclerView.setAdapter(perfilAdapter);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Maneja error
            }
        });
    }
}
