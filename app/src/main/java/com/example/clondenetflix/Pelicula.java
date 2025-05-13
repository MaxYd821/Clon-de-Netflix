package com.example.clondenetflix;

import com.example.clondenetflix.Entidades.Episodio;

import java.io.Serializable;
import java.util.List;

public class Pelicula implements Serializable {
    private String id;
    private int idpeli;
    private String tipo;
    public String titulo;
    public int anio;
    public String edad;
    public String duracion;
    public String sinopsis;
    public String actores;
    public String director;
    private String imagenUrl;
    private List<Episodio> episodios;

    public Pelicula(int idpeli, String tipo) {
        this.idpeli = idpeli;
        this.tipo = tipo;
    }

    public Pelicula() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getIdpeli() {
        return idpeli;
    }

    public void setIdpeli(int idpeli) {
        this.idpeli = idpeli;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    public String getImagenUrl(){
        return imagenUrl;
    }
    public void setImagenUrl(String imagenUrl){
        this.imagenUrl = imagenUrl;
    }
    public List<Episodio> getEpisodios() {
        return episodios;
    }
    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }
}
