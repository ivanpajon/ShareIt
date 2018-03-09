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
import com.shareit.shareit.adapters.AdapterRecyclerDemandas;
import com.shareit.shareit.model.Demanda;

import java.util.ArrayList;

public class DemandasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerDemandas;
    AdapterRecyclerDemandas adapter;
    LinearLayoutManager llm;
    ArrayList<Demanda> lista;

    public DemandasFragment() {
        // Required empty public constructor
    }

    public static DemandasFragment newInstance(String param1, String param2) {
        DemandasFragment fragment = new DemandasFragment();
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
        View view =inflater.inflate(R.layout.fragment_demandas, container, false);
        //Instanciamos los atributos
        lista = new ArrayList<>();
        recyclerDemandas = view.findViewById(R.id.recyclerDemandas);
        recyclerDemandas.setHasFixedSize(true);
        llm = new LinearLayoutManager(getContext());
        recyclerDemandas.setLayoutManager(llm);

        llenarLista();

        return view;
    }
    //Llenamos el recylcerView
    private void llenarLista() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("demands");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("DEMANDA" ,"Numero de demandas: "+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Demanda d = postSnapshot.getValue(Demanda.class);
                    Log.d("DEMANDA", d.getNombreDemanda());
                    lista.add(d);
                }
                //Generamos el adaptador
                adapter = new AdapterRecyclerDemandas(lista);
                recyclerDemandas.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DEMANDA" , databaseError.getMessage());
            }
        });
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
