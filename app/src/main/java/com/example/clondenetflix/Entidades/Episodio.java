package com.example.clondenetflix.Entidades;

public class Episodio {
    private String nombre;
    private String idUrl;
    private String sinopsis;
    private String duracion;

    public Episodio() {
    }
    public Episodio(String nombre, String idUrl, String sinopsis, String duracion) {
        this.nombre = nombre;
        this.idUrl = idUrl;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(String idUrl) {
        this.idUrl = idUrl;
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
