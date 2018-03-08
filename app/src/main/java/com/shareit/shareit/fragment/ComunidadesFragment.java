package com.shareit.shareit.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shareit.shareit.R;
import com.shareit.shareit.adapters.AdapterRecyclerComunidades;
import com.shareit.shareit.adapters.AdapterRecyclerDemandas;
import com.shareit.shareit.model.Comunidad;
import com.shareit.shareit.model.Demanda;

import java.util.ArrayList;


public class ComunidadesFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerDemandas;
    AdapterRecyclerComunidades adapter;
    LinearLayoutManager llm;
    ArrayList<Comunidad> lista;

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
        llenarLista();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comunidades, container, false);
        lista = new ArrayList<>();
        recyclerDemandas = view.findViewById(R.id.recyclerComunidades);
        recyclerDemandas.setHasFixedSize(true);
        llm = new LinearLayoutManager(getContext());
        recyclerDemandas.setLayoutManager(llm);

        return view;
    }

    private void llenarLista() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("communities");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("COMUNIDAD" ,"Numero de comunidades: "+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Comunidad c = postSnapshot.getValue(Comunidad.class);
                    Log.d("COMUNIDAD", c.getNombreComunidad());
                    lista.add(c);
                }
                //Generamos el adaptador
                adapter = new AdapterRecyclerComunidades(lista);
                recyclerDemandas.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("COMUNIDAD" , databaseError.getMessage());
            }
        });
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
