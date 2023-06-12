package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class T4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t4);

        Button registrarP = findViewById(R.id.btnRegistrarP);
        Button showP = findViewById(R.id.btnMisPokemones);

        registrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(T4Activity.this, RegistroT4Activity.class);
                startActivity(intent);
            }
        });

        showP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(T4Activity.this, ListadoT4Activity.class);
                startActivity(intent);
            }
        });
    }
}