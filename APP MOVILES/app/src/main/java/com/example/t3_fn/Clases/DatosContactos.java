package com.example.t3_fn.Clases;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

//Guardamos los datos
public class DatosContactos extends Application {
    private List<Contactos> contactos = new ArrayList<>();

    public List<Contactos> getContactosList() {
        return contactos;
    }
    public void setContactosList(Contactos contacto) {
        contactos.add(contacto);
    }

}
