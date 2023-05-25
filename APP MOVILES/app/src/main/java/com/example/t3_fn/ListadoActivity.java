package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.t3_fn.Adapters.ContactosAdapter;
import com.example.t3_fn.Clases.Contactos;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        ContactosAdapter ctAp = new ContactosAdapter(data(),this);

        RecyclerView rvLista =  findViewById(R.id.rvListaSimple);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(ctAp);
    }

    public List<Contactos> data (){
        List<Contactos> contactos = new ArrayList<>();
        Contactos C01 = new Contactos("Luis", "928513245", "https://img.freepik.com/vector-gratis/hombre-muestra-gesto-gran-idea_10045-637.jpg");
        contactos.add(C01);

        Contactos C02 = new Contactos("Nuria", "924567890", "https://img.freepik.com/foto-gratis/retrato-hermoso-mujer-joven-posicion-pared-gris_231208-10760.jpg?q=10&h=200");
        contactos.add(C02);

        return contactos;
    }
}