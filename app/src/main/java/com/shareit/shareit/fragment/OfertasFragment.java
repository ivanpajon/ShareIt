package com.shareit.shareit.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shareit.shareit.R;
import com.shareit.shareit.adapters.AdapterRecylerOfertas;
import com.shareit.shareit.model.Oferta;

import java.util.ArrayList;

public class OfertasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    //Creamos los atributos para el RecyclerView
    private RecyclerView recyclerOferta;
    private AdapterRecylerOfertas adapter;
    private LinearLayoutManager llm;
    private ArrayList<Oferta> lista;

    public OfertasFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OfertasFragment newInstance(String param1, String param2) {
        OfertasFragment fragment = new OfertasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Cargamos todos los componentes de la vista (Recycler,Adaptador,Array,LinerLayout)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_ofertas, container, false);

        //Instanciamos los atributos
        lista = new ArrayList<>();
        recyclerOferta = view.findViewById(R.id.recyclerOfertas);
        recyclerOferta.setHasFixedSize(true);
        llm = new LinearLayoutManager(getContext());
        recyclerOferta.setLayoutManager(llm);

        //Generamos el adaptador
        adapter = new AdapterRecylerOfertas(lista);
        recyclerOferta.setAdapter(adapter);

        llenarLista();

        return view;
    }

    //Llenamos el recyclerView
    private void llenarLista() {
        lista.add(new Oferta("nombre","descripcion ",R.drawable.persona));
        lista.add(new Oferta("nombre 1","descripcion 1",R.drawable.persona));
        lista.add(new Oferta("nombre 2","descripcion 2",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Oferta("nombre 3","descripcion 3",R.drawable.persona));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
