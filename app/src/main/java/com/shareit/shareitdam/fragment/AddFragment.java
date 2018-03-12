package com.shareit.shareitdam.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shareit.shareitdam.R;
import com.shareit.shareitdam.model.Demanda;
import com.shareit.shareitdam.model.Oferta;

import static android.app.Activity.RESULT_OK;


public class AddFragment extends Fragment {

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private ImageView imagen;
    private OnFragmentInteractionListener mListener;
    FloatingActionButton fab;
    private EditText etNombre, etDescripcion;
    private RadioGroup rgDemanda;
    private Button btnAdd;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        etNombre = v.findViewById(R.id.etNombreProducto);
        etDescripcion = v.findViewById(R.id.etDescripcionProducto);
        btnAdd = v.findViewById(R.id.btnAddDemanda);
        btnAdd.setOnClickListener((view) -> addDemanda(view));
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_add, container, false);
        imagen = vista.findViewById(R.id.imageProducto);
        rgDemanda = vista.findViewById(R.id.rgDemanda);
        fab = vista.findViewById(R.id.fabFotoProducto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return vista;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imagen.setImageURI(imageUri);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void addDemanda(View v) {
        // Obtenemos el indice del radiobutton seleccionado
        int radioButtonID = rgDemanda.getCheckedRadioButtonId();
        View radioButton = rgDemanda.findViewById(radioButtonID);
        int index = rgDemanda.indexOfChild(radioButton);

        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        if (!nombre.equals("") && !descripcion.equals("")) {
            if (index == 0) {
                String key = mDatabase.child("offers").push().getKey();
                Oferta o = new Oferta(nombre, descripcion, index);

                mDatabase.child("offers").child(key).setValue(o);
            }
            else {
                String key = mDatabase.child("demands").push().getKey();
                Demanda d = new Demanda(nombre, descripcion, index);

                mDatabase.child("demands").child(key).setValue(d);
            }
            // Volvemos al fragment de inicio
            Fragment f = new ContentFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();
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

        void onFragmentInteraction(Uri uri);
    }
}
