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
import com.shareit.shareit.adapters.AdapterRecyclerDemandas;
import com.shareit.shareit.model.Demanda;

import java.util.ArrayList;


public class ComunidadesFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerDemandas;
    AdapterRecyclerDemandas adapter;
    LinearLayoutManager llm;
    ArrayList<Demanda> lista;

    public ComunidadesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComunidadesFragment newInstance(String param1, String param2) {
        ComunidadesFragment fragment = new ComunidadesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_comunidades, container, false);
        lista = new ArrayList<>();
        recyclerDemandas = view.findViewById(R.id.recyclerDemandas);
        recyclerDemandas.setHasFixedSize(true);
        llm = new LinearLayoutManager(getContext());
        recyclerDemandas.setLayoutManager(llm);

        //Generamos el adaptador
        adapter = new AdapterRecyclerDemandas(lista);
        recyclerDemandas.setAdapter(adapter);
        llenarLista();


        return view;
    }

    private void llenarLista() {
        lista.add(new Demanda("nombre","descripcion",R.drawable.persona));
        lista.add(new Demanda("nombre 1","descripcion 1",R.drawable.persona));
        lista.add(new Demanda("nombre 2","descripcion 2",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
        lista.add(new Demanda("nombre 3","descripcion 3",R.drawable.persona));
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
