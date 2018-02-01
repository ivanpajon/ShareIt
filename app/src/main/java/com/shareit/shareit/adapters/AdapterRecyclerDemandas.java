package com.shareit.shareit.adapters;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shareit.shareit.R;
import com.shareit.shareit.clases.Demandas;

import java.util.ArrayList;

/**
 * Created by mario on 01/02/2018.
 */

public class AdapterRecyclerDemandas extends RecyclerView.Adapter<AdapterRecyclerDemandas.ViewHolderDatos> {

    ArrayList<Demandas> lista;

    public AdapterRecyclerDemandas(ArrayList<Demandas> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nombre;
        TextView descripcion;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.fotoProducto);
            nombre = itemView.findViewById(R.id.textNombre);
            descripcion = itemView.findViewById(R.id.textDescripcion);
        }

        public void asignarDatos(Demandas d) {
           // imageView.setImageIcon(d.getImage());
            //nombre.setText(d.getNombreDemandas());
            //descripcion.setText(d.getDescripcionDemandas());
        }
    }
}