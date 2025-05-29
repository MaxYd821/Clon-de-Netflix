package com.example.clondenetflix;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clondenetflix.Entidades.Episodio;

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
        //ivPelicula.setImageResource(pelicula.getIdpeli());

        if (pelicula.getImagenUrl() != null && !pelicula.getImagenUrl().isEmpty()) {
            // Cargar desde Firebase Storage con Glide
            Glide.with(holder.itemView.getContext())
                    .load(pelicula.getImagenUrl())
                    .into(ivPelicula);
        } else {
            // Cargar imagen desde recursos locales
            ivPelicula.setImageResource(pelicula.getIdpeli());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pelicula.getTitulo().equals("Van Helsing")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-OQLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Life")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-1QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Tomb Raider")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-2QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Anaconda 2")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-3QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Bastardos sin gloria")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-4QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Gladiador")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-5QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Inframundo")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-OQLd8n3MmuHAhdkmoA_");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Hellboy")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-6QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Ejercito de ladrones")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-7QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                }else if (pelicula.getTitulo().equals("Spiderman")) {
                    Intent intent = new Intent(v.getContext(), DetallePeliActivity.class);
                    intent.putExtra("firebaseId", "-8QLd8mnQH-uBoA1UDQb");
                    v.getContext().startActivity(intent);
                } else if(pelicula.getTipo().equals("serie")){
                    Intent intent = new Intent(v.getContext(), DetalleSerieActivity.class);
                    intent.putExtra("firebaseId", "-OQLd8n4D5_VYyMiJ21_");
                    v.getContext().startActivity(intent);
                }
            }
        });
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
