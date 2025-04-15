package com.example.clondenetflix;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarteleraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cartelera);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvCartelera = findViewById(R.id.rvCartelera);

        List<Pelicula> selechoy = Arrays.asList(
                new Pelicula(R.drawable.adolescencia),
                new Pelicula(R.drawable.blackmirror),
                new Pelicula(R.drawable.eljardinero),
                new Pelicula(R.drawable.devil),
                new Pelicula(R.drawable.rym),
                new Pelicula(R.drawable.htsdo)
        );

        List<Pelicula> proxhis = Arrays.asList(
                new Pelicula(R.drawable.silavida),
                new Pelicula(R.drawable.twd),
                new Pelicula(R.drawable.breakingbad),
                new Pelicula(R.drawable.prision),
                new Pelicula(R.drawable.drhouse),
                new Pelicula(R.drawable.lost)
        );

        List<Pelicula> milista = Arrays.asList(
                new Pelicula(R.drawable.twd),
                new Pelicula(R.drawable.breakingbad),
                new Pelicula(R.drawable.adolescencia)
        );

        List<CategoriaPeliculas> categorias = Arrays.asList(
                new CategoriaPeliculas("Nuestra selección de hoy para ti", selechoy),
                new CategoriaPeliculas("Tu próxima historia", proxhis),
                new CategoriaPeliculas("Mi lista                                            Ver Todos>", milista)
        );


        rvCartelera.setLayoutManager(new LinearLayoutManager(this));
        CategoriaAdapter adapter = new CategoriaAdapter(categorias);
        rvCartelera.setAdapter(adapter);
    }
}