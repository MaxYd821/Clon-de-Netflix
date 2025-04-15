package com.example.clondenetflix;

import java.util.List;

public class CategoriaPeliculas {
    private String titulo;
    private List<Pelicula> listpelis;

    public CategoriaPeliculas(String titulo, List<Pelicula> listpelis) {
        this.titulo = titulo;
        this.listpelis = listpelis;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Pelicula> getListpelis() {
        return listpelis;
    }

    public void setListpelis(List<Pelicula> listpelis) {
        this.listpelis = listpelis;
    }
}
