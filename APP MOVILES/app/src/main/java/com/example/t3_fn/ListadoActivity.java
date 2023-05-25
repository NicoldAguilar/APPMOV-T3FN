package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.t3_fn.Adapters.ContactosAdapter;
import com.example.t3_fn.Clases.Contactos;
import com.example.t3_fn.Clases.DatosContactos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    public List<Contactos> contactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        contactos = ((DatosContactos) getApplicationContext()).getContactosList();

        ContactosAdapter ctAp = new ContactosAdapter(contactos,this);

        RecyclerView rvLista =  findViewById(R.id.rvListaSimple);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(ctAp);

        Button regresarC = findViewById(R.id.btnRegresar);
        regresarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoActivity.this, RegistrarContactosActivity.class);
                startActivity(intent);
            }
        });
    }


}