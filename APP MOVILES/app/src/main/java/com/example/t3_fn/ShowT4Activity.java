package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t3_fn.Clases.Pokemon;
import com.example.t3_fn.Services.PokemonService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowT4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_t4);

        int position = getIntent().getIntExtra("position", 0);

        Button goBackC = findViewById(R.id.btnGoBackPoke);

        TextView regEDNomPoke = findViewById(R.id.tvNombrePoke);
        TextView regEDNumPoke = findViewById(R.id.tvNumeroPoke);
        TextView regEDTipoPoke = findViewById(R.id.tvtipoPoke);
        ImageView regEDImgPoke = findViewById(R.id.imFotoPokemon);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://648a929117f1536d65e948f6.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokemonService services = retrofit.create(PokemonService.class);
        Call<Pokemon> call = services.findUser(position);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokepoke = new Pokemon();
                pokepoke = response.body();

                regEDNomPoke.setText(pokepoke.getNombre());
                // regEDNumPoke.setText(pokepoke.getNumero());
                regEDTipoPoke.setText(pokepoke.getTipo());
                Picasso.get().load(pokepoke.getFoto())
                        .resize(300, 400) //tamaño específico
                        .into(regEDImgPoke);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

        Button goBackP = findViewById(R.id.btnGoBackPoke);
        goBackP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowT4Activity.this, ListadoT4Activity.class);
                startActivity(intent);
            }
        });
    }
}