package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.t3_fn.Clases.Contactos;
import com.example.t3_fn.Clases.DatosContactos;

import java.util.ArrayList;
import java.util.List;

public class RegistrarContactosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contactos);

        Button registrarC = findViewById(R.id.btnRegistrar);

        EditText regNom = findViewById(R.id.etNombre);
        EditText regNum = findViewById(R.id.etNumero);
        EditText regFot = findViewById(R.id.etPhoto);

        registrarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = regNom.getText().toString();
                String numero = regNum.getText().toString();
                String imagen = regFot.getText().toString();

                Contactos contacto = new Contactos(nombre, numero, imagen);
                ((DatosContactos) getApplicationContext()).setContactosList(contacto);

                Intent intent =  new Intent(RegistrarContactosActivity.this, ListadoActivity.class);

                startActivity(intent);
                finish();
            }
        });

    }

}