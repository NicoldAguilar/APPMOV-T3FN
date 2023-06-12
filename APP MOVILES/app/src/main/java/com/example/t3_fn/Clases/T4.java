package com.example.t3_fn.Clases;

public class T4 {

    String nombre;
    String tipo;
    String foto;
    String id;
    String numero;
    String urlCamara;

    public T4() {
    }

    public T4(String nombre, String tipo, String foto, String id, String numero, String urlCamara) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.foto = foto;
        this.id = id;
        this.numero = numero;
        this.urlCamara =urlCamara;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUrlCamara() {
        return urlCamara;
    }

    public void setUrlCamara(String urlCamara) {
        this.urlCamara = urlCamara;
    }
}
