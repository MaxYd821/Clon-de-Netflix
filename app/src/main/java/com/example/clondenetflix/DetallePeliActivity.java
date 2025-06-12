package com.example.clondenetflix;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clondenetflix.Adapters.PelisSimilAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.bumptech.glide.Glide;

public class DetallePeliActivity extends AppCompatActivity {
    TextView tvTitulo, tvAnio, tvEdad, tvDuracion, tvSinopsis, tvActores, tvDirector;
    ImageView ivBack, ivIconHomePeli;
    ImageButton btnVer;
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
        setupView();

        String firebaseId = getIntent().getStringExtra("firebaseId");

        if (firebaseId != null) {
            DatabaseReference peliculaRef = FirebaseDatabase.getInstance()
                    .getReference("peliculas")
                            .child(firebaseId);
                    //.child("-OQLd8mnQH-uBoA1UDQb");

            peliculaRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Pelicula pelicula = snapshot.getValue(Pelicula.class);

                    if (pelicula == null) {
                        Log.e("DetallePeliActivity", "No se pudo convertir el snapshot a Pelicula");
                        return;
                    }
                    Log.i("DetallePeliActivity", "Titulo: "+ pelicula.getTitulo());
                    if (pelicula != null) {
                        // Mostrar los datos
                        tvTitulo.setText(pelicula.getTitulo());
                        tvAnio.setText(String.valueOf(pelicula.getAnio()));
                        tvEdad.setText(pelicula.getEdad());
                        tvDuracion.setText(pelicula.getDuracion());
                        tvSinopsis.setText(pelicula.getSinopsis());
                        tvActores.setText(pelicula.getActores());
                        tvDirector.setText(pelicula.getDirector());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DetallePeliActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            });
        }

        RecyclerView rvPelisSimilares1 = findViewById(R.id.rvPelisSimilares1);
        RecyclerView rvPelisSimilares2 = findViewById(R.id.rvPelisSimilares2);
        RecyclerView rvPelisSimilares3 = findViewById(R.id.rvPelisSimilares3);
        RecyclerView rvPelisSimilares4 = findViewById(R.id.rvPelisSimilares4);

        List<Pelicula> pelissimil1 = Arrays.asList(
                new Pelicula(R.drawable.adolescencia,"serie",""),
                new Pelicula(R.drawable.blackmirror,"serie",""),
                new Pelicula(R.drawable.eljardinero,"serie","")
        );

        List<Pelicula> pelissimil2 = Arrays.asList(
                new Pelicula(R.drawable.silavida,"serie",""),
                new Pelicula(R.drawable.twd,"serie",""),
                new Pelicula(R.drawable.breakingbad,"serie","")
        );

        List<Pelicula> pelissimil3 = Arrays.asList(
                new Pelicula(R.drawable.anaconda2,"pelicula",""),
                new Pelicula(R.drawable.hellboy,"pelicula",""),
                new Pelicula(R.drawable.drhouse,"serie","")
        );

        List<Pelicula> pelissimil4 = Arrays.asList(
                new Pelicula(R.drawable.tomraider,"pelicula",""),
                new Pelicula(R.drawable.inframundo,"pelicula",""),
                new Pelicula(R.drawable.life,"pelicula","")
        );

        setupRecyclerView(rvPelisSimilares1, pelissimil1);
        setupRecyclerView(rvPelisSimilares2, pelissimil2);
        setupRecyclerView(rvPelisSimilares3, pelissimil3);
        setupRecyclerView(rvPelisSimilares4, pelissimil4);

        vvPrevista = findViewById(R.id.vvPrevista);
        Uri previewUri;
        if(Objects.equals(firebaseId, "-OQLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_vanhelsing);
        }
        else if(Objects.equals(firebaseId, "-1QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_life);
        }
        else if(Objects.equals(firebaseId, "-2QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_tombraider);
        }
        else if(Objects.equals(firebaseId, "-3QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_anaconda2);
        }
        else if(Objects.equals(firebaseId, "-4QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_bastardos);
        }
        else if(Objects.equals(firebaseId, "-5QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_gladiador);
        }
        else if(Objects.equals(firebaseId, "-OQLd8n3MmuHAhdkmoA_")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_inframundo);
        }
        else if(Objects.equals(firebaseId, "-6QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_hellboy);
        }
        else if(Objects.equals(firebaseId, "-7QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_ejerladro);
        }
        else if(Objects.equals(firebaseId, "-8QLd8mnQH-uBoA1UDQb")) {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_spiderman);
        }
        else {
            previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_vanhelsing);
        }

        vvPrevista.setVideoURI(previewUri);
        vvPrevista.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            vvPrevista.start();
        });
        MediaController mediaController = new MediaController(this);
        vvPrevista.setMediaController(mediaController);
        mediaController.setAnchorView(vvPrevista);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivIconHomePeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnVer.setOnClickListener(v -> {
            Intent intent = new Intent(DetallePeliActivity.this, ReproducirActivity.class);
            intent.putExtra("firebaseId", firebaseId);
            startActivity(intent);
        });

    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Pelicula> peliculas) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PelisSimilAdapter adapter = new PelisSimilAdapter(peliculas);
        recyclerView.setAdapter(adapter);
    }

    private void setupView() {
        tvTitulo = findViewById(R.id.tvTitulo);
        tvAnio = findViewById(R.id.tvAnio);
        tvEdad = findViewById(R.id.tvEdad);
        tvDuracion = findViewById(R.id.tvDuracion);
        tvSinopsis = findViewById(R.id.tvSinopsis);
        tvActores = findViewById(R.id.tvActores);
        tvDirector = findViewById(R.id.tvDirector);
        ivBack = findViewById(R.id.ivBack);
        ivIconHomePeli = findViewById(R.id.ivIconHomePeli);
        btnVer = findViewById(R.id.btnVer);
    }
}