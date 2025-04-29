package com.example.clondenetflix;

public class Pelicula {
    private int idpeli;
    private String tipo;

    public Pelicula(int idpeli, String tipo) {
        this.idpeli = idpeli;
        this.tipo = tipo;
    }

    public Pelicula() {
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
}
