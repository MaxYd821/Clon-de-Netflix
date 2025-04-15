package com.example.clondenetflix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>{

    private List<CategoriaPeliculas> categorias;

    public CategoriaAdapter(List<CategoriaPeliculas> categorias){
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.CategoriaViewHolder holder, int position) {
        TextView tvTituloCategoria = holder.itemView.findViewById(R.id.tvTituloCategoria);
        tvTituloCategoria.setText(categorias.get(position).getTitulo());
        RecyclerView rvCartelera = holder.itemView.findViewById(R.id.rvPeliculasHorizontales);
        Context context = holder.itemView.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        CarteleraAdapter caradapter = new CarteleraAdapter(categorias.get(position).getListpelis());
        rvCartelera.setLayoutManager(layoutManager);
        rvCartelera.setAdapter(caradapter);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder{
        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
