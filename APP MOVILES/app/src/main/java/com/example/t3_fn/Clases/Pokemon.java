package com.example.t3_fn.Clases;

public class Pokemon {

    String nombre;
    String tipo;
    String foto;
    String id;
    //String numero;
    String urlCamara;
    String latitud;
    String longitud;

    public Pokemon() {
    }

    public Pokemon(String nombre, String tipo, String foto, String numero, String urlCamara, String latitud, String longitud) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.foto = foto;
        //this.numero = numero;
        this.urlCamara =urlCamara;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /*public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }*/

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

    public String getUrlCamara() {
        return urlCamara;
    }

    public void setUrlCamara(String urlCamara) {
        this.urlCamara = urlCamara;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
