package com.example.clondenetflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clondenetflix.Adapters.PeliculaAdapter;
import com.example.clondenetflix.Entidades.Episodio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.text.TextWatcher;
import android.text.Editable;

public class CarteleraActivity extends AppCompatActivity {

    private RecyclerView rvCategorias;
    private EditText edtBuscar;
    private ImageView btnBuscar;
    private LinearLayout contenedorResultado;
    private List<CategoriaPeliculas> categorias;
    private CategoriaAdapter categoriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);

        rvCategorias = findViewById(R.id.rvCartelera);
        edtBuscar = findViewById(R.id.edtBuscar);
        btnBuscar = findViewById(R.id.btnBuscar);
        contenedorResultado = findViewById(R.id.contenedorResultado);

        // Simula la carga de datos
        categorias = new ArrayList<>();
        categoriaAdapter = new CategoriaAdapter(categorias);
        rvCategorias.setLayoutManager(new LinearLayoutManager(this));
        rvCategorias.setAdapter(categoriaAdapter);
        cargarPeliculasDesdeFirebase(); // <-- llamado final

        edtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscarPelicula(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ImageView ivPerfilCartelera = findViewById(R.id.ivPerfilCartelera);

        // Obtener extras
        Intent intent = getIntent();
        String nombrePerfil = intent.getStringExtra("nombrePerfil");
        String urlFoto = intent.getStringExtra("urlFoto");

        if (urlFoto != null && !urlFoto.isEmpty()) {
            Glide.with(this).load(urlFoto).into(ivPerfilCartelera);
        } else {
            ivPerfilCartelera.setImageResource(R.drawable.icon_miperfil); // tu imagen por defecto
        }

        ivPerfilCartelera.setOnClickListener(v -> {
            Intent perfilIntent = new Intent(CarteleraActivity.this, PerfilDetalleActivity.class);
            perfilIntent.putExtra("nombrePerfil", nombrePerfil);
            perfilIntent.putExtra("urlFoto", urlFoto);
            startActivity(perfilIntent);
        });

        TextView tvNombrePerfil = findViewById(R.id.tvNombrePerfilCartelera);
        tvNombrePerfil.setText(nombrePerfil);
    }

    private void buscarPelicula(String textoBuscado) {
        String tituloBuscado = textoBuscado.trim().toLowerCase();

        contenedorResultado.removeAllViews();

        if (tituloBuscado.isEmpty()) {
            contenedorResultado.setVisibility(View.GONE);
            rvCategorias.setVisibility(View.VISIBLE);
            return;
        }

        rvCategorias.setVisibility(View.GONE);
        contenedorResultado.setVisibility(View.VISIBLE);

        boolean encontrada = false;

        for (CategoriaPeliculas categoria : categorias) {
            for (Pelicula p : categoria.getListpelis()) {
                String tituloPelicula = p.getTitulo().toLowerCase();
                if (tituloPelicula.contains(tituloBuscado)) {
                    encontrada = true;
                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            700
                    ));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    if (p.getImagenUrl() != null && !p.getImagenUrl().isEmpty()) {
                        Glide.with(this)
                                .load(p.getImagenUrl())
                                .into(imageView);
                    } else {
                        imageView.setImageResource(p.getIdpeli());
                    }

                    imageView.setOnClickListener(v -> {
                        Intent intent;
                        if (p.getTipo().equalsIgnoreCase("pelicula")) {
                            intent = new Intent(this, DetallePeliActivity.class);
                        } else {
                            intent = new Intent(this, DetalleSerieActivity.class);
                        }
                        intent.putExtra("firebaseId", p.getId());
                        startActivity(intent);
                    });

                    contenedorResultado.addView(imageView);
                }
            }
        }

        if (!encontrada) {
            Toast.makeText(this, "Película no encontrada", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarResultado(Pelicula pelicula) {
        // Oculta el RecyclerView principal de categorías
        rvCategorias.setVisibility(View.GONE);
        contenedorResultado.setVisibility(View.VISIBLE);
        contenedorResultado.removeAllViews();

        // Crear una nueva ImageView para mostrar la portada de la película encontrada
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                700
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (pelicula.getImagenUrl() != null && !pelicula.getImagenUrl().isEmpty()) {
            Glide.with(this)
                    .load(pelicula.getImagenUrl())
                    .into(imageView);
        } else {
            imageView.setImageResource(pelicula.getIdpeli());
        }

        imageView.setOnClickListener(v -> {
            Intent intent = null;
            String titulo = pelicula.getTitulo();

            switch (titulo) {
                case "Van Helsing":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-OQLd8mnQH-uBoA1UDQb");
                    break;
                case "Life":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-1QLd8mnQH-uBoA1UDQb");
                    break;
                case "Tomb Raider":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-2QLd8mnQH-uBoA1UDQb");
                    break;
                case "Anaconda 2":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-3QLd8mnQH-uBoA1UDQb");
                    break;
                case "Bastardos sin gloria":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-4QLd8mnQH-uBoA1UDQb");
                    break;
                case "Gladiador":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-5QLd8mnQH-uBoA1UDQb");
                    break;
                case "Inframundo":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-OQLd8n3MmuHAhdkmoA_");
                    break;
                case "Hellboy":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-6QLd8mnQH-uBoA1UDQb");
                    break;
                case "Ejercito de ladrones":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-7QLd8mnQH-uBoA1UDQb");
                    break;
                case "Spiderman":
                    intent = new Intent(this, DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-8QLd8mnQH-uBoA1UDQb");
                    break;
                default:
                    if (pelicula.getTipo().equals("serie")) {
                        intent = new Intent(this, DetalleSerieActivity.class);
                        intent.putExtra("firebaseId", "-OQLd8n4D5_VYyMiJ21_");
                    }
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });

        contenedorResultado.addView(imageView);
    }


    private void cargarPeliculasDesdeFirebase() {
        DatabaseReference refPeliculas = FirebaseDatabase.getInstance().getReference("peliculas");

        refPeliculas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Pelicula> listaPeliculas = new ArrayList<>();

                for (DataSnapshot peliSnap : snapshot.getChildren()) {
                    Pelicula pelicula = peliSnap.getValue(Pelicula.class);
                    if (pelicula != null) {
                        pelicula.setId(peliSnap.getKey());
                        listaPeliculas.add(pelicula);
                    }
                }


                // Reparto en 4 categorías de forma equitativa
                int totalPeliculas = listaPeliculas.size();
                int numCategorias = 4;
                int baseSize = totalPeliculas / numCategorias;
                int extra = totalPeliculas % numCategorias;

                String[] nombresCategorias = {
                        "Nuestra selección de hoy para ti",
                        "Tu próxima historia",
                        "Porque viste Resident Evil",
                        "Mi lista                                            Ver Todos>"
                };

                List<CategoriaPeliculas> categoriasFirebase = new ArrayList<>();
                int index = 0;

                for (int i = 0; i < numCategorias; i++) {
                    int cantidad = baseSize + (i < extra ? 1 : 0); // Distribuye el sobrante
                    int end = Math.min(index + cantidad, listaPeliculas.size());
                    List<Pelicula> sublista = listaPeliculas.subList(index, end);
                    categoriasFirebase.add(new CategoriaPeliculas(nombresCategorias[i], sublista));
                    index = end;
                }

                categorias.clear();
                categorias.addAll(categoriasFirebase);
                categoriaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CarteleraActivity.this, "Error al cargar películas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}