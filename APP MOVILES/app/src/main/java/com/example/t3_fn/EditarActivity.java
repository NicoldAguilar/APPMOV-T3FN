package com.example.t3_fn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.t3_fn.Clases.Contactos;
import com.example.t3_fn.Services.ContactosService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        int position = getIntent().getIntExtra("position", 0); //recibe los datos según id

        Button editadoC = findViewById(R.id.btnEditado);
        Button goBackC = findViewById(R.id.btnGoBack);

        EditText regEDNom = findViewById(R.id.etEDNombre);
        EditText regEDNum = findViewById(R.id.etEDNumero);
        EditText regEDFot = findViewById(R.id.etEDPhoto);


        //Editar contacto
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactosService services = retrofit.create(ContactosService.class);
        Call<Contactos> call = services.findUser(position);
        call.enqueue(new Callback<Contactos>() {
            @Override
            public void onResponse(Call<Contactos> call, Response<Contactos> response) {
                //recibo el contacto a editar
                Contactos contacto = new Contactos();
                contacto = response.body();

                //seteamos los nuevos valores
                regEDNom.setText(contacto.getNombre());
                regEDNum.setText(contacto.getNumero());
                regEDFot.setText(contacto.getFoto());
            }

            @Override
            public void onFailure(Call<Contactos> call, Throwable t) {

            }
        });


        editadoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contactos contacto = new Contactos();
                String nombre = regEDNom.getText().toString();
                String numero = regEDNum.getText().toString();
                String imagen = regEDFot.getText().toString();

                contacto.setNombre(nombre);
                contacto.setNumero(numero);
                contacto.setFoto(imagen);

                Call<Contactos> call = services.update(contacto, position);
                call.enqueue(new Callback<Contactos>() {
                    @Override
                    public void onResponse(Call<Contactos> call, Response<Contactos> response) {
                        Intent intent =  new Intent(EditarActivity.this, ListadoActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Contactos> call, Throwable t) {

                    }
                });
            }
            //((DatosContactos) getApplicationContext()).setContactosList(contacto);
        });

        goBackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarActivity.this, ListadoActivity.class);
                startActivity(intent);
            }
        });
    }
}