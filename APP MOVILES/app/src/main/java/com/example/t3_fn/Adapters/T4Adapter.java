package com.example.t3_fn.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_fn.Clases.Pokemon;
import com.example.t3_fn.Clases.T4;
import com.example.t3_fn.ListadoPokemonesActivity;
import com.example.t3_fn.R;
import com.example.t3_fn.Services.PokemonService;
import com.example.t3_fn.Services.T4Service;
import com.example.t3_fn.ShowT4Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class T4Adapter extends RecyclerView.Adapter{
    private List<T4> listaT4;
    private Context context;

    public T4Adapter(List<T4> listaT4, Context context) {
        this.listaT4 = listaT4;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_t4_string, parent, false);
        T4Adapter.NameViewHolder viewHolder = new T4Adapter.NameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String nombreP = listaT4.get(position).getNombre();
        String tipoP = listaT4.get(position).getTipo();
        String fotoP = listaT4.get(position).getFoto();
        String numeroP = listaT4.get(position).getNumero();
        int idPokemon = Integer.parseInt(listaT4.get(position).getId());

        View view = holder.itemView;
        TextView nomP = view.findViewById(R.id.tvNombrePokemon);
        TextView tipP = view.findViewById(R.id.tvtipoPokemon);
        TextView numP = view.findViewById(R.id.tvNumeroPokemon);
        ImageView foP = view.findViewById(R.id.imFotoPokemon);
        Button eliminarP = view.findViewById(R.id.btnEliminarP);
        Button detalle = view.findViewById(R.id.btnDetalle);


        //Enviar datos
        //Imagen
        Picasso.get().load(fotoP)
                .resize(300, 400) //tamaño específico
                .into(foP);
        nomP.setText(nombreP);
        numP.setText(numeroP);
        tipP.setText(tipoP);


        eliminarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                T4Service services = retrofit.create(T4Service.class);
                Call<T4> call = services.delete(idPokemon);
                call.enqueue(new Callback<T4>() {
                    @Override
                    public void onResponse(Call<T4> call, Response<T4> response) {
                        System.out.println("Pokemon eliminado");
                        Intent intent = new Intent(context, ListadoPokemonesActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<T4> call, Throwable t) {

                    }
                });

            }
        });

        detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowT4Activity.class);
                intent.putExtra("position", idPokemon);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaT4.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
