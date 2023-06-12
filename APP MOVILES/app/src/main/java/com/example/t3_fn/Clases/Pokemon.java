package com.example.t3_fn.Clases;

public class Pokemon {

    String nombre;
    String tipo;
    String foto;
    String id;
    String numero;

    public Pokemon() {
    }

    public Pokemon(String nombre, String tipo, String foto, String numero) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.foto = foto;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
