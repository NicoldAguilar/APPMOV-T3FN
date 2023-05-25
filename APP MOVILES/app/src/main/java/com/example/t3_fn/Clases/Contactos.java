package com.example.t3_fn.Clases;

public class Contactos {
    String nombre;
    String numero;
    String foto;

    public Contactos() {
    }

    public Contactos(String nombre, String numero, String foto) {
        this.nombre = nombre;
        this.numero = numero;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}


