package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Button registrarP = findViewById(R.id.btnRegistrarP);
        Button showP = findViewById(R.id.btnMisPokemones);

        registrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PokemonActivity.this, RegistrarPokemonActivity.class);
                startActivity(intent);
            }
        });

        showP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PokemonActivity.this, ListadoPokemonesActivity.class);
                startActivity(intent);
            }
        });
    }
}