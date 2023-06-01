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
import com.example.t3_fn.Services.ContactosService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarContactosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contactos);

        Button registrarC = findViewById(R.id.btnRegistrar);
        Button showC = findViewById(R.id.btnShowContacts);

        EditText regNom = findViewById(R.id.etNombre);
        EditText regNum = findViewById(R.id.etNumero);
        EditText regFot = findViewById(R.id.etPhoto);

        registrarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = regNom.getText().toString();
                String numero = regNum.getText().toString();
                String imagen = regFot.getText().toString();

                Contactos contacto = new Contactos();
                contacto.setNombre(nombre);
                contacto.setNumero(numero);
                contacto.setFoto(imagen);

                //Crear contacto
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ContactosService services = retrofit.create(ContactosService.class);
                Call<Void> call = services.create(contacto);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent =  new Intent(RegistrarContactosActivity.this, ListadoActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

                //((DatosContactos) getApplicationContext()).setContactosList(contacto);
            }
        });

        showC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarContactosActivity.this, ListadoActivity.class);
                startActivity(intent);
            }
        });

    }

}