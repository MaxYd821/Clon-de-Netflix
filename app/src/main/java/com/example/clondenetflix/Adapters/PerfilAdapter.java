package com.example.clondenetflix.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.clondenetflix.CarteleraActivity;
import com.example.clondenetflix.Entidades.Perfil;
import com.example.clondenetflix.R;

import java.util.List;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {

    private Context context;
    private List<Perfil> listaPerfiles;

    public PerfilAdapter(Context context, List<Perfil> listaPerfiles) {
        this.context = context;
        this.listaPerfiles = listaPerfiles;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_perfil, parent, false);
        return new PerfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        Perfil perfil = listaPerfiles.get(position);
        holder.txtNombre.setText(perfil.getNombre());
        Glide.with(context)
                .load(perfil.getUrlFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgPerfil);

        holder.itemView.setOnClickListener(v -> {
            if (!perfil.getNombre().equalsIgnoreCase("Agregar perfil")) {
                Intent intent = new Intent(context, CarteleraActivity.class);
                intent.putExtra("nombrePerfil", perfil.getNombre());
                intent.putExtra("urlFoto", perfil.getUrlFoto());
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Agregar nuevo perfil (función aún no implementada)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPerfiles.size();
    }

    public class PerfilViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPerfil;
        TextView txtNombre;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPerfil = itemView.findViewById(R.id.imgPerfil);
            txtNombre = itemView.findViewById(R.id.txtNombrePerfil);
        }
    }
}

