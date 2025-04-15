package com.example.clondenetflix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarteleraAdapter extends RecyclerView.Adapter<CarteleraAdapter.CarteleraViewHolder> {

    private List<Pelicula> peli;

    public CarteleraAdapter(List<Pelicula> peli){
        this.peli = peli;
    }
    @NonNull
    @Override
    public CarteleraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cartelera, parent, false);
        return new CarteleraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarteleraAdapter.CarteleraViewHolder holder, int position) {
        Pelicula pelicula = peli.get(position);
        ImageView ivPelicula =holder.itemView.findViewById(R.id.ivPelicula);
        ivPelicula.setImageResource(pelicula.getIdpeli());
    }

    @Override
    public int getItemCount() {
        return peli.size();
    }

    public class CarteleraViewHolder extends RecyclerView.ViewHolder{
        public CarteleraViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
