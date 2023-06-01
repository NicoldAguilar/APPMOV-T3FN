package com.example.t3_fn.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_fn.Clases.Contactos;
import com.example.t3_fn.EditarActivity;
import com.example.t3_fn.ListadoActivity;
import com.example.t3_fn.R;
import com.example.t3_fn.RegistrarContactosActivity;
import com.example.t3_fn.Services.ContactosService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactosAdapter extends RecyclerView.Adapter {

    private List<Contactos> listaContactos;
    private Context context; //contexto de la lista

    //constructor
    public ContactosAdapter(List<Contactos> contactos, Context context) {
        this.listaContactos = contactos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_string, parent, false);
        ContactosAdapter.NameViewHolder viewHolder = new ContactosAdapter.NameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String nombreContacto = listaContactos.get(position).getNombre();
        String numeroContacto = listaContactos.get(position).getNumero();
        String fotoContacto = listaContactos.get(position).getFoto();
        int idContacto = Integer.parseInt(listaContactos.get(position).getId());

        //llamar a la vista
        View view = holder.itemView;

        TextView nomC = view.findViewById(R.id.tvNombre);
        TextView numC = view.findViewById(R.id.tvNumero);
        ImageView foC = view.findViewById(R.id.imFoto);
        Button llamadaC = view.findViewById(R.id.btnLlamada);
        Button editarC = view.findViewById(R.id.btnEditar);
        Button eliminarC = view.findViewById(R.id.btnBorrar);


        //Enviar datos
        //Imagen
        Picasso.get().load(fotoContacto)
                .resize(300, 400) //tamaño específico
                .into(foC);
        nomC.setText(nombreContacto);
        numC.setText(numeroContacto);

        //Acceder a la llamada
        llamadaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialer(numeroContacto);
            }
        });

        editarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditarActivity.class);
                intent.putExtra("position", idContacto); //mando el id que quiero editar
                context.startActivity(intent);
            }
        });

        eliminarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ContactosService services = retrofit.create(ContactosService.class);
                Call<Contactos> call = services.delete(idContacto);
                call.enqueue(new Callback<Contactos>() {
                    @Override
                    public void onResponse(Call<Contactos> call, Response<Contactos> response) {
                        System.out.println("Contacto eliminado");
                        Intent intent = new Intent(context, ListadoActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Contactos> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private void openDialer(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }
}
