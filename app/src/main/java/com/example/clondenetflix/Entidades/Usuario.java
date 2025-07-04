package com.example.clondenetflix.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String email;
    private String password;
    private List<Perfil> perfiles = new ArrayList<>();

    public Usuario(int id, String email, String password, List<Perfil> perfiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.perfiles = perfiles;
    }
    public Usuario(String email, String password, List<Perfil> perfiles) {
        this.email = email;
        this.password = password;
        this.perfiles = perfiles;
    }
    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
}
