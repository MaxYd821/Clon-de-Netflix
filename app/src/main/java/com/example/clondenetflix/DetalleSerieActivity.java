package com.example.clondenetflix;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clondenetflix.Adapters.EpisodioAdapter;
import com.example.clondenetflix.Entidades.Episodio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class DetalleSerieActivity extends AppCompatActivity {

    TextView tvTitulo, tvAnio, tvEdad, tvDuracion, tvSinopsis, tvActores, tvDirector;
    ImageView ivBack, ivIconHomeSer;
    VideoView vvPrevistaEpisodio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_serie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detalleserie), (v, insets) -> {
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

            peliculaRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Pelicula pelicula = snapshot.getValue(Pelicula.class);

                    if (pelicula == null) {
                        Log.e("DetalleSerieActivity", "No se pudo convertir el snapshot a Pelicula");
                        return;
                    }
                    Log.i("DetalleSerieActivity", "Titulo: "+ pelicula.getTitulo());
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
                    Toast.makeText(DetalleSerieActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            });
        }



        vvPrevistaEpisodio = findViewById(R.id.vvPrevistaEpisodio);
        Uri previewUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.adolescencia);
        vvPrevistaEpisodio.setVideoURI(previewUri);
        vvPrevistaEpisodio.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            vvPrevistaEpisodio.start();
        });
        MediaController mediaController = new MediaController(this);
        vvPrevistaEpisodio.setMediaController(mediaController);
        mediaController.setAnchorView(vvPrevistaEpisodio);

        String sinopsis1 = "La pocilia derriba la puerta del hogar de los Miller. El " +
                "adolescente Jamie es arrestado y llevado a la comisaría para ser interrogado, pero " +
                "insiste en que no ha hecho nada malo.";
        String sinopsis2 = "La pocilia busca respuestas --y el arma homicida-- en la escuela" +
                "de Jamie. No obtienen información de los amigos del chico, hasta que el hijo" +
                "del detective Bascombe ofrece su ayuda.";
        String sinopsis3 = "Jamie se reune con una psicóloga. Al principio no quiere hablar, pero" +
                "después se sincera sobre sus complejos sentimientos hacia Katie.";
        String sinopsis4 = "En el cumpleaños de Edie, los Miller intentan festejar como si todo estuviera bien, pero" +
                "una serie de sucesos improvistos amenazan con desestabilizarlos.";

        List<Episodio> episodios = Arrays.asList(
                new Episodio("1. Episodio 1", R.drawable.adoles1, sinopsis1, "45 min"),
                new Episodio("2. Episodio 2", R.drawable.adoles2, sinopsis2, "50 min"),
                new Episodio("3. Episodio 3", R.drawable.adoles3, sinopsis3, "48 min"),
                new Episodio("4. Episodio 4", R.drawable.adoles4, sinopsis4, "52 min")
        );

        RecyclerView rvEpisodios = findViewById(R.id.rvEpisodios);

        rvEpisodios.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        EpisodioAdapter adapter = new EpisodioAdapter(episodios);
        rvEpisodios.setAdapter(adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivIconHomeSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupView() {
        tvTitulo = findViewById(R.id.tvTituloserie);
        tvAnio = findViewById(R.id.tvAnio);
        tvEdad = findViewById(R.id.tvEdad);
        tvDuracion = findViewById(R.id.tvDuracion);
        tvSinopsis = findViewById(R.id.tvSinopsis);
        tvActores = findViewById(R.id.tvActores);
        tvDirector = findViewById(R.id.tvDirector);
        ivBack = findViewById(R.id.ivBack);
        ivIconHomeSer = findViewById(R.id.ivIconHomeSer);
    }
}