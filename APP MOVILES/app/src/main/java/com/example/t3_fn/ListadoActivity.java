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
import com.example.t3_fn.Services.ContactosService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoActivity extends AppCompatActivity {

    public List<Contactos> contactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        //contactos = ((DatosContactos) getApplicationContext()).getContactosList();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactosService services = retrofit.create(ContactosService.class);
        Call<List<Contactos>> call = services.getAllUser();
        call.enqueue(new Callback<List<Contactos>>() {
            @Override
            public void onResponse(Call<List<Contactos>> call, Response<List<Contactos>> response) {
                contactos = response.body();
                ContactosAdapter ctAp = new ContactosAdapter(contactos,ListadoActivity.this);

                RecyclerView rvLista =  findViewById(R.id.rvListaSimple);
                rvLista.setLayoutManager(new LinearLayoutManager(ListadoActivity.this));
                rvLista.setAdapter(ctAp);
            }

            @Override
            public void onFailure(Call<List<Contactos>> call, Throwable t) {

            }
        });


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