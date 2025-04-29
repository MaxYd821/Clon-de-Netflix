package com.example.clondenetflix.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clondenetflix.Entidades.Episodio;
import com.example.clondenetflix.Pelicula;
import com.example.clondenetflix.R;

import java.util.List;

public class EpisodioAdapter extends RecyclerView.Adapter<EpisodioAdapter.EpisodioViewHolder> {

    private List<Episodio> episodios;

    public EpisodioAdapter(List<Episodio> episodios){
        this.episodios = episodios;
    }
    @NonNull
    @Override
    public EpisodioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episodios, parent, false);
        return new EpisodioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodioAdapter.EpisodioViewHolder holder, int position) {
        Episodio episodio = episodios.get(position);
        ImageView ivEpisodios =holder.itemView.findViewById(R.id.ivEpisodios);
        ivEpisodios.setImageResource(episodio.getIdEpisodio());
        TextView tvEpisodiosNombre = holder.itemView.findViewById(R.id.tvEpisodiosNombre);
        TextView tvEpisodioduracion = holder.itemView.findViewById(R.id.tvEpisodioduracion);
        TextView tvEpisodiodes = holder.itemView.findViewById(R.id.tvEpisodiodes);
        tvEpisodiosNombre.setText(episodio.getNombre());
        tvEpisodioduracion.setText(episodio.getDuracion());
        tvEpisodiodes.setText(episodio.getSinopsis());

    }

    @Override
    public int getItemCount() {
        return episodios.size();
    }

    public class EpisodioViewHolder extends RecyclerView.ViewHolder{
        public EpisodioViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
