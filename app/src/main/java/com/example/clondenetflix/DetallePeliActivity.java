package com.example.clondenetflix;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clondenetflix.Adapters.PelisSimilAdapter;

import java.util.Arrays;
import java.util.List;

public class DetallePeliActivity extends AppCompatActivity {

    VideoView vvPrevista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_peli);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detpeli), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvPelisSimilares1 = findViewById(R.id.rvPelisSimilares1);
        RecyclerView rvPelisSimilares2 = findViewById(R.id.rvPelisSimilares2);
        RecyclerView rvPelisSimilares3 = findViewById(R.id.rvPelisSimilares3);
        RecyclerView rvPelisSimilares4 = findViewById(R.id.rvPelisSimilares4);

        List<Pelicula> pelissimil1 = Arrays.asList(
                new Pelicula(R.drawable.adolescencia,"serie"),
                new Pelicula(R.drawable.blackmirror,"serie"),
                new Pelicula(R.drawable.eljardinero,"serie")
        );

        List<Pelicula> pelissimil2 = Arrays.asList(
                new Pelicula(R.drawable.silavida,"serie"),
                new Pelicula(R.drawable.twd,"serie"),
                new Pelicula(R.drawable.breakingbad,"serie")
        );

        List<Pelicula> pelissimil3 = Arrays.asList(
                new Pelicula(R.drawable.anaconda2,"pelicula"),
                new Pelicula(R.drawable.hellboy,"pelicula"),
                new Pelicula(R.drawable.drhouse,"serie")
        );

        List<Pelicula> pelissimil4 = Arrays.asList(
                new Pelicula(R.drawable.tomraider,"pelicula"),
                new Pelicula(R.drawable.inframundo,"pelicula"),
                new Pelicula(R.drawable.life,"pelicula")
        );

        setupRecyclerView(rvPelisSimilares1, pelissimil1);
        setupRecyclerView(rvPelisSimilares2, pelissimil2);
        setupRecyclerView(rvPelisSimilares3, pelissimil3);
        setupRecyclerView(rvPelisSimilares4, pelissimil4);

        vvPrevista = findViewById(R.id.vvPrevista);
        Uri previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_vanhelsing);
        vvPrevista.setVideoURI(previewUri);
        vvPrevista.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            vvPrevista.start();
        });
        MediaController mediaController = new MediaController(this);
        vvPrevista.setMediaController(mediaController);
        mediaController.setAnchorView(vvPrevista);

    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Pelicula> peliculas) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PelisSimilAdapter adapter = new PelisSimilAdapter(peliculas);
        recyclerView.setAdapter(adapter);
    }

}