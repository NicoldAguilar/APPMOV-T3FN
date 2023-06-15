package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.t3_fn.Adapters.ComentariosT4Adapter;
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

public class AgregarComentarioActivity extends AppCompatActivity {

    public List<String> aggComentarios = new ArrayList<>();
    public T4 actualizarComentario = new T4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_comentario);

        EditText regComent = findViewById(R.id.etIngresarComentario);

        int position = getIntent().getIntExtra("position", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://648a929117f1536d65e948f6.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T4Service services = retrofit.create(T4Service.class);
        Call<T4> call = services.findUser(position);
        call.enqueue(new Callback<T4>() {
            @Override
            public void onResponse(Call<T4> call, Response<T4> response) {
                actualizarComentario = response.body();
                aggComentarios = actualizarComentario.getComentarios();
                ComentariosT4Adapter ctPp = new ComentariosT4Adapter(actualizarComentario.getComentarios(), AgregarComentarioActivity.this);
                RecyclerView rvLista =  findViewById(R.id.rvListaComentarios);
                rvLista.setLayoutManager(new LinearLayoutManager(AgregarComentarioActivity.this));
                rvLista.setAdapter(ctPp);
            }

            @Override
            public void onFailure(Call<T4> call, Throwable t) {

            }
        });

        Button regristrarComentarios = findViewById(R.id.btnRegistrarComent);
        regristrarComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String comentario = regComent.getText().toString();
                aggComentarios.add(comentario);
                actualizarComentario.setComentarios(aggComentarios);

                Call<T4> call = services.update(actualizarComentario, position);
                call.enqueue(new Callback<T4>() {
                    @Override
                    public void onResponse(Call<T4> call, Response<T4> response) {
                        Intent intent =  new Intent(AgregarComentarioActivity.this, ListadoT4Activity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<T4> call, Throwable t) {

                    }
                });
            }
        });

        Button regresarC = findViewById(R.id.btnRegresarListaPublicaciones);
        regresarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarComentarioActivity.this, ListadoT4Activity.class);
                startActivity(intent);
            }
        });

    }
}