package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.t3_fn.Adapters.T4Adapter;
import com.example.t3_fn.Clases.T4;
import com.example.t3_fn.Services.T4Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoT4Activity extends AppCompatActivity {

    public List<T4> pokemon = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_t4);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T4Service services = retrofit.create(T4Service.class);
        Call<List<T4>> call = services.getAllUser();
        call.enqueue(new Callback<List<T4>>() {
            @Override
            public void onResponse(Call<List<T4>> call, Response<List<T4>> response) {
                pokemon = response.body();
                T4Adapter ctPp = new T4Adapter(pokemon, ListadoT4Activity.this);

                RecyclerView rvLista =  findViewById(R.id.rvListaSimple);
                rvLista.setLayoutManager(new LinearLayoutManager(ListadoT4Activity.this));
                rvLista.setAdapter(ctPp);

            }

            @Override
            public void onFailure(Call<List<T4>> call, Throwable t) {

            }

        });

        Button regresarP = findViewById(R.id.btnRegresarPoke);
        regresarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoT4Activity.this, T4Activity.class);
                startActivity(intent);
            }
        });
    }
}