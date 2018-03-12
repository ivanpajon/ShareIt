package com.shareit.shareitdam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shareit.shareitdam.R;
import com.shareit.shareitdam.model.Comunidad;

import java.util.ArrayList;

public class AdapterRecyclerComunidades extends RecyclerView.Adapter<AdapterRecyclerComunidades.ViewHolderDatos>  {
    ArrayList<Comunidad> lista;

    public AdapterRecyclerComunidades(ArrayList<Comunidad> lista) {
        this.lista = lista;
    }

    @Override
    public AdapterRecyclerComunidades.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new AdapterRecyclerComunidades.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerComunidades.ViewHolderDatos holder, int position) {
        holder.nombre.setText(lista.get(position).getNombreComunidad());
        holder.imageView.setImageResource(R.drawable.comunidades);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nombre;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fotoProducto);
            nombre = itemView.findViewById(R.id.textNombre);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getNombre() {
            return nombre;
        }
    }
}
