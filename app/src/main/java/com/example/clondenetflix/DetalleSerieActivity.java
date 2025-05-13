package com.example.clondenetflix;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
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

        String peliculaId = getIntent().getStringExtra("peliculaId");
        DatabaseReference peliculaRef = FirebaseDatabase.getInstance().getReference("peliculas").child(peliculaId);

        peliculaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Pelicula pelicula = snapshot.getValue(Pelicula.class);
                if (pelicula != null) {
                    tvTitulo.setText(pelicula.getTitulo());
                    tvAnio.setText(String.valueOf(pelicula.getAnio()));
                    tvEdad.setText(pelicula.getEdad());
                    tvDuracion.setText(pelicula.getDuracion());
                    tvSinopsis.setText(pelicula.getSinopsis());
                    tvActores.setText(pelicula.getActores());
                    tvDirector.setText(pelicula.getDirector());

                    /*ImageView ivPelicula = findViewById(R.id.ivPelicula);
                    Glide.with(DetalleSerieActivity.this)
                            .load(pelicula.getImagenUrl())
                            .into(ivPelicula);*/

                    Glide.with(DetalleSerieActivity.this)
                            .load(pelicula.getImagenUrl())
                            .into((ImageView) findViewById(R.id.ivPelicula));

                    if (pelicula.getEpisodios() != null) {
                        RecyclerView rvEpisodios = findViewById(R.id.rvEpisodios);
                        rvEpisodios.setLayoutManager(new LinearLayoutManager(DetalleSerieActivity.this, LinearLayoutManager.VERTICAL, false));
                        EpisodioAdapter adapter = new EpisodioAdapter(pelicula.getEpisodios());
                        rvEpisodios.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DetalleSerieActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });

        setupView();

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

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finaliza la actividad actual y regresa a la anterior
            }
        });
    }

    private void setupView() {
        tvTitulo = findViewById(R.id.tvTitulo);
        tvAnio = findViewById(R.id.tvAnio);
        tvEdad = findViewById(R.id.tvEdad);
        tvDuracion = findViewById(R.id.tvDuracion);
        tvSinopsis = findViewById(R.id.tvSinopsis);
        tvActores = findViewById(R.id.tvActores);
        tvDirector = findViewById(R.id.tvDirector);
    }
}