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
                new Pelicula(R.drawable.adolescencia,"serie"),
                new Pelicula(R.drawable.blackmirror,"serie"),
                new Pelicula(R.drawable.eljardinero,"serie"),
                new Pelicula(R.drawable.devil,"serie"),
                new Pelicula(R.drawable.rym,"serie"),
                new Pelicula(R.drawable.htsdo,"serie")
        );

        List<Pelicula> proxhis = Arrays.asList(
                new Pelicula(R.drawable.silavida,"serie"),
                new Pelicula(R.drawable.twd,"serie"),
                new Pelicula(R.drawable.breakingbad,"serie"),
                new Pelicula(R.drawable.prision,"serie"),
                new Pelicula(R.drawable.drhouse,"serie"),
                new Pelicula(R.drawable.lost,"serie")
        );

        List<Pelicula> porqviste = Arrays.asList(
                new Pelicula(R.drawable.vanhel,"pelicula"),
                new Pelicula(R.drawable.inframundo,"pelicula"),
                new Pelicula(R.drawable.hellboy, "pelicula"),
                new Pelicula(R.drawable.tomraider, "pelicula"),
                new Pelicula(R.drawable.anaconda2, "pelicula"),
                new Pelicula(R.drawable.life, "pelicula")
        );

        List<Pelicula> milista = Arrays.asList(
                new Pelicula(R.drawable.twd, "serie"),
                new Pelicula(R.drawable.breakingbad, "serie"),
                new Pelicula(R.drawable.adolescencia, "serie")
        );

        List<CategoriaPeliculas> categorias = Arrays.asList(
                new CategoriaPeliculas("Nuestra selección de hoy para ti", selechoy),
                new CategoriaPeliculas("Tu próxima historia", proxhis),
                new CategoriaPeliculas("Porque viste Resident Evil", porqviste),
                new CategoriaPeliculas("Mi lista                                            Ver Todos>", milista)
        );


        rvCartelera.setLayoutManager(new LinearLayoutManager(this));
        CategoriaAdapter adapter = new CategoriaAdapter(categorias);
        rvCartelera.setAdapter(adapter);
    }
}