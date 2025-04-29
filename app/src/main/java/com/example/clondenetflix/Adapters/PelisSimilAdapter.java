package com.example.clondenetflix.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clondenetflix.CarteleraActivity;
import com.example.clondenetflix.CarteleraAdapter;
import com.example.clondenetflix.DetallePeliActivity;
import com.example.clondenetflix.Pelicula;
import com.example.clondenetflix.R;

import java.util.List;

public class PelisSimilAdapter extends RecyclerView.Adapter<PelisSimilAdapter.PelisSimilViewHolder> {

    private List<Pelicula> peli;

    public PelisSimilAdapter(List<Pelicula> peli){
        this.peli = peli;
    }
    @NonNull
    @Override
    public PelisSimilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelis_similares, parent, false);
        return new PelisSimilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PelisSimilAdapter.PelisSimilViewHolder holder, int position) {
        Pelicula pelicula = peli.get(position);
        ImageView ivPelisSimil =holder.itemView.findViewById(R.id.ivPelisSimilares);
        ivPelisSimil.setImageResource(pelicula.getIdpeli());

    }

    @Override
    public int getItemCount() {
        return peli.size();
    }

    public class PelisSimilViewHolder extends RecyclerView.ViewHolder{
        public PelisSimilViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
