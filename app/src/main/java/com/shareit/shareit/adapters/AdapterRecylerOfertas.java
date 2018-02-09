package com.shareit.shareit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shareit.shareit.R;
import com.shareit.shareit.model.Oferta;

import java.util.ArrayList;

/**
 * Created by mario on 05/02/2018.
 */

public class AdapterRecylerOfertas extends RecyclerView.Adapter<AdapterRecylerOfertas.ViewHolderDatos> {

    ArrayList<Oferta> lista;

    public AdapterRecylerOfertas(ArrayList<Oferta> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecylerOfertas.ViewHolderDatos holder, int position) {
       // holder.imageView.setImageURI(lista.get(position).getImageOferta());
       // holder.nombre.setText(lista.get(position).getNombreOferta());
        //holder.descripcion.setText(lista.get(position).getDescripcionOferta());
        holder.nombre.setText(R.string.comunidades);
        holder.descripcion.setImageResource(R.drawable.add);
        holder.imageView.setImageResource(R.drawable.comunidades);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nombre;
        ImageView descripcion;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fotoProducto);
            nombre = itemView.findViewById(R.id.textNombre);
            descripcion = itemView.findViewById(R.id.imageMas);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getNombre() {
            return nombre;
        }

        public ImageView getDescripcion() {
            return descripcion;
        }
    }
}