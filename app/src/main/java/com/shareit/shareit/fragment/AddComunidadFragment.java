package com.shareit.shareit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shareit.shareit.R;
import com.shareit.shareit.model.Comunidad;


public class AddComunidadFragment extends Fragment {
    private EditText etNombre;
    private EditText etDescripcion;
    private Button btnAdd;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public AddComunidadFragment() {

    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        etNombre = v.findViewById(R.id.etNombreComunidadAdd);
        etDescripcion = v.findViewById(R.id.etDescripcionComunidadAdd);
        btnAdd = v.findViewById(R.id.btnAddComunidad);
        btnAdd.setOnClickListener((view) -> addComunidad(view));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return inflater.inflate(R.layout.fragment_add_comunidad, container, false);
    }

    public void addComunidad(View v) {
        if (!etNombre.getText().toString().trim().equals("") && !etDescripcion.getText().toString().trim().equals("")) {
            String key = mDatabase.child("communities").push().getKey();
            Comunidad c = new Comunidad(etNombre.getText().toString(), etDescripcion.getText().toString(), "");
            c.addUIDUsuario(currentUser.getUid());

            mDatabase.child("communities").child(key).setValue(c);

            // Volvemos al fragment de comunidades
            Fragment f = new ComunidadesFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();
        }
    }

}
