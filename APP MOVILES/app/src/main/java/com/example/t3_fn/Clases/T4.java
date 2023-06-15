package com.example.t3_fn.Clases;

import java.util.List;

public class T4 {

    String nombre;
    String foto;
    String id;
    String descripcion;
    String urlCamara;
    List<String> comentarios;

    public T4() {
    }

    public T4(String nombre, String foto, String id, String numero, String urlCamara, List<String> comentarios) {
        this.nombre = nombre;
        this.foto = foto;
        this.id = id;
        this.descripcion = numero;
        this.urlCamara =urlCamara;
        this.comentarios = comentarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlCamara() {
        return urlCamara;
    }

    public void setUrlCamara(String urlCamara) {
        this.urlCamara = urlCamara;
    }

    public List<String> getComentarios() {
        return comentarios;
    }
    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }
}
