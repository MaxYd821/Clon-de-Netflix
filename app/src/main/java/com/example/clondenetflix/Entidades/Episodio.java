package com.example.clondenetflix.Entidades;

public class Episodio {
    private String nombre;
    private int IdEpisodio;
    private String sinopsis;
    private String duracion;

    public Episodio(String nombre, int idEpisodio, String sinopsis, String duracion) {
        this.nombre = nombre;
        IdEpisodio = idEpisodio;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
    }

    public Episodio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEpisodio() {
        return IdEpisodio;
    }

    public void setIdEpisodio(int idEpisodio) {
        IdEpisodio = idEpisodio;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
