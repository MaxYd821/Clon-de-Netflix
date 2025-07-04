package com.example.clondenetflix.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clondenetflix.Pelicula;
import com.example.clondenetflix.R;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private Context context;
    private List<Pelicula> peliculas;

    public PeliculaAdapter(Context context, List<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.imgPelicula.setImageResource(pelicula.getIdpeli());
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPelicula;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPelicula = itemView.findViewById(R.id.imgPelicula);
        }
    }

    // Método para actualizar la lista
    public void actualizarLista(List<Pelicula> nuevaLista) {
        this.peliculas = nuevaLista;
        notifyDataSetChanged();
    }
}

