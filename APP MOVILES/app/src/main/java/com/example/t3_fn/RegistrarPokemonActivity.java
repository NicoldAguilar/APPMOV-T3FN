package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.t3_fn.Clases.Pokemon;
import com.example.t3_fn.Services.PokemonService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarPokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pokemon);

        Button registrarP = findViewById(R.id.btnRegistrarPoke);

        EditText regPNom = findViewById(R.id.etNombreP);
        EditText regPNum = findViewById(R.id.etNumeroP);
        EditText regPTipo = findViewById(R.id.etTipoP);

        registrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = regPNom.getText().toString();
                String numero = regPNum.getText().toString();
                String tipo = regPTipo.getText().toString();

                Pokemon pokemon = new Pokemon();
                pokemon.setNombre(nombre);
                pokemon.setNumero(numero);
                pokemon.setTipo(tipo);
                pokemon.setFoto("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numero + ".png");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PokemonService services = retrofit.create(PokemonService.class);
                Call<Void> call = services.create(pokemon);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent =  new Intent(RegistrarPokemonActivity.this, ListadoPokemonesActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });
    }
}