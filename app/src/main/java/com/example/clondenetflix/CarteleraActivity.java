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
import java.util.List;

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
            categorias = cargarCategorias();
            categoriaAdapter = new CategoriaAdapter(categorias);
            rvCategorias.setLayoutManager(new LinearLayoutManager(this));
            rvCategorias.setAdapter(categoriaAdapter);

            btnBuscar.setOnClickListener(v -> buscarPelicula());

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
    private void buscarPelicula() {
        String tituloBuscado = edtBuscar.getText().toString().trim().toLowerCase();

        if (tituloBuscado.isEmpty()) {
            contenedorResultado.setVisibility(View.GONE);
            rvCategorias.setVisibility(View.VISIBLE);
            return;
        }

        for (CategoriaPeliculas categoria : categorias) {
            for (Pelicula p : categoria.getListpelis()) {
                String tituloPelicula = p.getTitulo().toLowerCase();
                if (tituloPelicula.contains(tituloBuscado)) {
                    mostrarResultado(p);
                    return;
                }
            }
        }

        Toast.makeText(this, "Película no encontrada", Toast.LENGTH_SHORT).show();
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

        // 👉 Aquí se agrega la misma funcionalidad de click que ya tenías
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


    private List<CategoriaPeliculas> cargarCategorias() {
            List<Pelicula> selechoy = Arrays.asList(
                    new Pelicula(R.drawable.adolescencia, "serie", "Adolescencia"),
                    new Pelicula(R.drawable.life, "pelicula", "Life"),
                    new Pelicula(R.drawable.tomraider, "pelicula", "Tomb Raider"),
                    new Pelicula(R.drawable.devil, "serie", ""),
                    new Pelicula(R.drawable.rym, "serie", ""),
                    new Pelicula(R.drawable.htsdo, "serie", "")
            );

            List<Pelicula> proxhis = Arrays.asList(
                    new Pelicula(R.drawable.anaconda2, "pelicula", "Anaconda 2"),
                    new Pelicula(R.drawable.bastardos, "pelicula", "Bastardos sin gloria"),
                    new Pelicula(R.drawable.gladiador, "pelicula", "Gladiador"),
                    new Pelicula(R.drawable.prision, "serie", ""),
                    new Pelicula(R.drawable.drhouse, "serie", ""),
                    new Pelicula(R.drawable.lost, "serie", "")
            );

            List<Pelicula> porqviste = Arrays.asList(
                    new Pelicula(R.drawable.vanhel, "pelicula", "Van Helsing"),
                    new Pelicula(R.drawable.inframundo, "pelicula", "Inframundo"),
                    new Pelicula(R.drawable.hellboy, "pelicula", "Hellboy"),
                    new Pelicula(R.drawable.tomraider, "pelicula", ""),
                    new Pelicula(R.drawable.anaconda2, "pelicula", ""),
                    new Pelicula(R.drawable.life, "pelicula", "")
            );

            List<Pelicula> milista = Arrays.asList(
                    new Pelicula(R.drawable.ejercitoladrones, "pelicula", "Ejercito de ladrones"),
                    new Pelicula(R.drawable.spiderman, "pelicula", "Spiderman"),
                    new Pelicula(R.drawable.adolescencia, "serie", "")
            );

            List<CategoriaPeliculas> categorias = Arrays.asList(
                    new CategoriaPeliculas("Nuestra selección de hoy para ti", selechoy),
                    new CategoriaPeliculas("Tu próxima historia", proxhis),
                    new CategoriaPeliculas("Porque viste Resident Evil", porqviste),
                    new CategoriaPeliculas("Mi lista                                            Ver Todos>", milista)
            );

            return categorias;
        }
    }