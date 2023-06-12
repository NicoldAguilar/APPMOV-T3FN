package com.example.t3_fn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_fn.Adapters.PokemonAdapter;
import com.example.t3_fn.Clases.Pokemon;
import com.example.t3_fn.Services.PokemonService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListadoPokemonesActivity extends AppCompatActivity {

    public List<Pokemon> pokemon = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_pokemones);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService services = retrofit.create(PokemonService.class);
        Call<List<Pokemon>> call = services.getAllUser();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                pokemon = response.body();
                PokemonAdapter ctPp = new PokemonAdapter(pokemon, ListadoPokemonesActivity.this);

                RecyclerView rvLista =  findViewById(R.id.rvListaSimple);
                rvLista.setLayoutManager(new LinearLayoutManager(ListadoPokemonesActivity.this));
                rvLista.setAdapter(ctPp);

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

            }
        });

        Button regresarP = findViewById(R.id.btnRegresarPoke);
        regresarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoPokemonesActivity.this, PokemonActivity.class);
                startActivity(intent);
            }
        });


    }

}