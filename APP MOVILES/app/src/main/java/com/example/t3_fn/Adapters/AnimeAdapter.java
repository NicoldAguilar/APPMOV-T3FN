package com.example.t3_fn.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_fn.Clases.Animes;
import com.example.t3_fn.Clases.Contactos;
import com.example.t3_fn.ListadoActivity;
import com.example.t3_fn.R;
import com.example.t3_fn.RegistrarContactosActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter {

    private List<Animes> listaAnimes;
    private Context context; //contexto de la lista


    //constructor
    public AnimeAdapter(List<Animes> animes, Context context) {
        this.listaAnimes = animes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_animes_string, parent, false);
        AnimeAdapter.NameViewHolder viewHolder = new AnimeAdapter.NameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Animes anime = listaAnimes.get(position);
        String nombreAnime = listaAnimes.get(position).getNombre();
        String descAnimes = listaAnimes.get(position).getDescripción();
        String fotoAnimes = listaAnimes.get(position).getFoto();

        //llamar a la vista
        View view = holder.itemView;

        TextView nomA = view.findViewById(R.id.tvNombreAnime);
        TextView dscA = view.findViewById(R.id.tvDescripción);
        ImageView foA = view.findViewById(R.id.imFotoAnime);
        ImageView favA = view.findViewById(R.id.imFotoAnime4);

        //Enviar datos
        Log.i("foto", fotoAnimes);
        Picasso.get().load(fotoAnimes).into(foA); //mandar img
        nomA.setText(nombreAnime);
        dscA.setText(descAnimes);

        //Picasso.get().load("https://png.pngtree.com/png-vector/20190129/ourmid/pngtree-vector-star-icon-png-image_420017.jpg").into(favA); //mandar img
        if(anime.getFavoritos() == true){
            Picasso.get().load("https://w7.pngwing.com/pngs/1004/936/png-transparent-blackstar-star-angle-triangle-symmetry-thumbnail.png").into(favA);
        }
        else{
            Picasso.get().load("https://png.pngtree.com/png-vector/20190129/ourmid/pngtree-vector-star-icon-png-image_420017.jpg").into(favA);
        }

        favA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(anime.getFavoritos() == true){
                    anime.setFavoritos(false);
                    Picasso.get().load("https://w7.pngwing.com/pngs/1004/936/png-transparent-blackstar-star-angle-triangle-symmetry-thumbnail.png").into(favA);
                }
                else{
                    Picasso.get().load("https://png.pngtree.com/png-vector/20190129/ourmid/pngtree-vector-star-icon-png-image_420017.jpg").into(favA);
                    anime.setFavoritos(true);
                }
            }

        });

    }

    @Override
    public int getItemCount() {
        return listaAnimes.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
