package com.example.t3_fn.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_fn.R;

import java.util.List;

public class ComentariosT4Adapter extends RecyclerView.Adapter{
    List<String> comentarios;
    private Context context;

    public ComentariosT4Adapter(List<String> comentarios, Context context) {
        this.comentarios = comentarios;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comentarios, parent, false);
        ComentariosT4Adapter.NameViewHolder viewHolder = new ComentariosT4Adapter.NameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        TextView coments = view.findViewById(R.id.tvComentarios);

        String coment = comentarios.get(position);
        coments.setText(coment);
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
