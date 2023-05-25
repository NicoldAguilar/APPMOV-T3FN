package com.example.t3_fn.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_fn.Clases.Contactos;
import com.example.t3_fn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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

        //llamar a la vista
        View view = holder.itemView;

        TextView nomC = view.findViewById(R.id.tvNombre);
        TextView numC = view.findViewById(R.id.tvNumero);
        ImageView foC = view.findViewById(R.id.imFoto);
        Button llamadaC = view.findViewById(R.id.btnLlamada);

        //Enviar datos
        Picasso.get().load(fotoContacto).into(foC); //mandar img
        nomC.setText(nombreContacto);
        numC.setText(numeroContacto);

        //Acceder a la llamada
        llamadaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialer(numeroContacto);
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
