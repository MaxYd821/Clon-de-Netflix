package com.example.clondenetflix;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clondenetflix.Entidades.Episodio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        //crearPeliculasEnFirebase();

        //cargarPeliculasDesdeFirebase();

        List<Pelicula> selechoy = Arrays.asList(
                new Pelicula(R.drawable.adolescencia,"serie", "Adolescencia"),
                new Pelicula(R.drawable.life,"pelicula","Life"),
                new Pelicula(R.drawable.tomraider,"pelicula","Tomb Raider"),
                new Pelicula(R.drawable.devil,"serie",""),
                new Pelicula(R.drawable.rym,"serie",""),
                new Pelicula(R.drawable.htsdo,"serie","")
        );

        List<Pelicula> proxhis = Arrays.asList(
                new Pelicula(R.drawable.anaconda2,"pelicula","Anaconda 2"),
                new Pelicula(R.drawable.bastardos,"pelicula","Bastardos sin gloria"),
                new Pelicula(R.drawable.gladiador,"pelicula","Gladiador"),
                new Pelicula(R.drawable.prision,"serie",""),
                new Pelicula(R.drawable.drhouse,"serie",""),
                new Pelicula(R.drawable.lost,"serie","")
        );

        List<Pelicula> porqviste = Arrays.asList(
                new Pelicula(R.drawable.vanhel,"pelicula","Van Helsing"),
                new Pelicula(R.drawable.inframundo,"pelicula","Inframundo"),
                new Pelicula(R.drawable.hellboy, "pelicula","Hellboy"),
                new Pelicula(R.drawable.tomraider, "pelicula",""),
                new Pelicula(R.drawable.anaconda2, "pelicula",""),
                new Pelicula(R.drawable.life, "pelicula","")
        );

        List<Pelicula> milista = Arrays.asList(
                new Pelicula(R.drawable.ejercitoladrones, "pelicula","Ejercito de ladrones"),
                new Pelicula(R.drawable.spiderman, "pelicula","Spiderman"),
                new Pelicula(R.drawable.adolescencia, "serie","")
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

    private void crearPeliculasEnFirebase(){
        DatabaseReference peliculaRef = FirebaseDatabase.getInstance().getReference("peliculas");


        peliculaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {

                    Pelicula pelicula1 = new Pelicula();
                    pelicula1.setTitulo("Van Helsing");
                    pelicula1.setAnio(2004);
                    pelicula1.setEdad("13+");
                    pelicula1.setDuracion("2h 11m");
                    pelicula1.setSinopsis("El cazador de vampiros Van Helsing...");
                    pelicula1.setActores("Hugh Jackman, Kate Beckinsale...");
                    pelicula1.setDirector("Stephen Sommers");
                    pelicula1.setTipo("pelicula");
                    pelicula1.setImagenUrl("https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/Peliculas%2Fvanhel.png?alt=media&token=93fe3658-0710-4976-a548-1859734d0a0f");

                    String Key = peliculaRef.push().getKey();
                    pelicula1.setId(Key);
                    peliculaRef.child(Key).setValue(pelicula1);

                    Pelicula pelicula2 = new Pelicula();
                    pelicula2.setTitulo("Inframundo");
                    pelicula2.setAnio(2003);
                    pelicula2.setEdad("16+");
                    pelicula2.setDuracion("2h 1m");
                    pelicula2.setSinopsis("Una guerra entre vampiros y hombres lobo...");
                    pelicula2.setActores("Kate Beckinsale, Scott Speedman...");
                    pelicula2.setDirector("Len Wiseman");
                    pelicula2.setTipo("pelicula");
                    pelicula2.setImagenUrl("https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/Peliculas%2Finframundo.png?alt=media&token=4fa4e6a1-6147-4e52-a630-a7901d079d29");

                    pelicula2.setId(Key);

                    Pelicula serie1 = new Pelicula();
                    serie1.setTitulo("Adolescencia");
                    serie1.setAnio(2023);
                    serie1.setEdad("16+");
                    serie1.setDuracion("1 temporada");
                    serie1.setSinopsis("Una serie que explora los desafíos de la adolescencia...");
                    serie1.setActores("Actor 1, Actor 2...");
                    serie1.setDirector("Jack Thorne, Stephen Graham");
                    serie1.setTipo("serie");
                    serie1.setImagenUrl("https://firebasestorage.googleapis.com/v0/b/netflixclone-61496.firebasestorage.app/o/Peliculas%2Fadolescencia.png?alt=media&token=91805d2d-3fe2-4269-abde-769509e5e36a");
                    serie1.setId(Key);
                    List<Episodio> episodios = Arrays.asList(
                            new Episodio("1. Episodio 1", R.drawable.adoles1, "Sinopsis del episodio 1", "45 min"),
                            new Episodio("2. Episodio 2", R.drawable.adoles2, "Sinopsis del episodio 2", "50 min"),
                            new Episodio("3. Episodio 3", R.drawable.adoles3, "Sinopsis del episodio 3", "48 min"),
                            new Episodio("4. Episodio 4", R.drawable.adoles4, "Sinopsis del episodio 4", "52 min")
                    );
                    serie1.setEpisodios(episodios);

                    Pelicula blackmirror = new Pelicula();
                    blackmirror.setTitulo("Black Mirror");
                    blackmirror.setAnio(2025);
                    blackmirror.setEdad("16+");
                    blackmirror.setDuracion("7 temporadas");
                    blackmirror.setSinopsis("Aclamada por Vulture como «sorprendentemente buena y bastante aterradora»...");
                    blackmirror.setActores("Awkwafina, Anthony Mackie...");
                    blackmirror.setDirector("Director de la serie");
                    blackmirror.setTipo("serie");
                    blackmirror.setId(Key);


                    //peliculaRef.push().setValue(pelicula1);
                    peliculaRef.push().setValue(pelicula2);
                    peliculaRef.push().setValue(serie1);
                    peliculaRef.push().setValue(blackmirror);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores
            }
        });
    }

    /*private void cargarPeliculasDesdeFirebase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("peliculas");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Pelicula> listaPeliculas = new ArrayList<>();
                for (DataSnapshot peliSnapshot : snapshot.getChildren()) {
                    Pelicula pelicula = peliSnapshot.getValue(Pelicula.class);
                    listaPeliculas.add(pelicula);
                }

                // Agruparlas por categoría si es necesario (aquí simple ejemplo)
                List<CategoriaPeliculas> categorias = Arrays.asList(
                        new CategoriaPeliculas("Películas desde Firebase", listaPeliculas)
                );

                RecyclerView rvCartelera = findViewById(R.id.rvCartelera);
                rvCartelera.setLayoutManager(new LinearLayoutManager(CarteleraActivity.this));
                rvCartelera.setAdapter(new CategoriaAdapter(categorias));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CarteleraActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}