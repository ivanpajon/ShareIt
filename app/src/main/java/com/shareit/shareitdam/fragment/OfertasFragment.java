package com.shareit.shareitdam.fragment;

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
import com.shareit.shareitdam.R;
import com.shareit.shareitdam.adapters.AdapterRecylerOfertas;
import com.shareit.shareitdam.model.Oferta;

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

        llenarLista();

        return view;
    }

    //Llenamos el recyclerView
    private void llenarLista() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("offers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("OFERTA" ,"Numero de ofertas: "+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Oferta o = postSnapshot.getValue(Oferta.class);
                    Log.d("OFERTA", o.getNombreOferta());
                    lista.add(o);
                }
                //Generamos el adaptador
                adapter = new AdapterRecylerOfertas(lista);
                recyclerOferta.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("OFERTA" , databaseError.getMessage());
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
