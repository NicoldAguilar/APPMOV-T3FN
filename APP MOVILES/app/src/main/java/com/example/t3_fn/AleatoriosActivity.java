package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class AleatoriosActivity extends AppCompatActivity {

    int numeroJ1 = 0, numeroJ2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aleatorios);

        //Aleatorios
        Random random = new Random();

        //Textos
        TextView ptsJugador1 = findViewById(R.id.textViewPtsJugador1);
        TextView ptsJugador2 = findViewById(R.id.textViewPtsJugador2);
        TextView ganador = findViewById(R.id.textViewGanoJugador);

        //Botones
        Button btnEJuga = findViewById(R.id.buttonElijeJugador);
        btnEJuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ptsJugador1.getText().equals("")){
                    numeroJ1 = random.nextInt(10) +1;
                    ptsJugador1.setText(String.valueOf(numeroJ1));
                    btnEJuga.setText("JUGADOR 2");
                }
                else{
                    numeroJ2 = random.nextInt(10) +1;
                    ptsJugador2.setText(String.valueOf(numeroJ2));
                    btnEJuga.setText("JUGADOR 1");

                    if(numeroJ1 > numeroJ2){
                        ganador.setText(" Jugador 1");
                    }
                    else{
                        ganador.setText(" Jugador 2");
                    }
                }
            }
        });

        Button btnEReset = findViewById(R.id.buttonReset);
        btnEReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ptsJugador1.setText("");
                ptsJugador2.setText("");
            }
        });
    }
}