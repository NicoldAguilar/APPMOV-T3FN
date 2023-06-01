package com.example.t3_fn.Clases;

public class Animes {
    String nombre;
    String descripción;
    String foto;
    Boolean favoritos;

    public Animes() {
    }

    public Animes(String nombre, String descripción, String foto, Boolean favoritos) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.foto = foto;
        this.favoritos = favoritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Boolean favoritos) {
        this.favoritos = favoritos;
    }
}
