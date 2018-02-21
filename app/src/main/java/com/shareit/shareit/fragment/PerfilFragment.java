package com.shareit.shareit.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shareit.shareit.R;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

public class PerfilFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etUser;
    private FloatingActionButton fabEdit;
    private FloatingActionButton fabCamera;
    private FloatingActionButton fabCambiar;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private ImageView imagen;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflamos la vista con todos sus componentes
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);


        etNombre = vista.findViewById(R.id.etNombre);
        etEmail = vista.findViewById(R.id.etEmail);
        etPassword = vista.findViewById(R.id.etPasswordUsuario);
        etTelefono = vista.findViewById(R.id.etTelefono);
        etUser = vista.findViewById(R.id.etUserName);
        imagen = vista.findViewById(R.id.civ);


        //Iniciamos los edit text en false para no poder escribir
        etNombre.setEnabled(false);
        etEmail.setEnabled(false);
        etPassword.setEnabled(false);
        etTelefono.setEnabled(false);
        etUser.setEnabled(false);

        //Cargamos los datos del usuario
        etEmail.setText(currentUser.getEmail());
        Picasso.with(getApplicationContext()).load(currentUser.getPhotoUrl()).into(imagen);


        //Floating action button encargado de seleccionar la foto
        fabCamera = vista.findViewById(R.id.fabFoto);
        fabCamera.setOnClickListener(v -> {
            //Accedemos a la galeria del telefono
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
                });

        // Floating action button encargado de editar los campos
        fabEdit = vista.findViewById(R.id.fabEditar);
        fabCambiar = vista.findViewById(R.id.fabCambiar);
        fabCambiar.setVisibility(View.GONE);

        fabEdit.setOnClickListener(v ->  {
            fabEdit.setVisibility(View.GONE);
            //Activamos los edit text para poder modificar datos
            etNombre.setEnabled(true);
            etEmail.setEnabled(true);
            etPassword.setEnabled(true);
            etTelefono.setEnabled(true);
            etUser.setEnabled(true);
            //Cambiamos el icono del boton
            fabCambiar.setVisibility(View.VISIBLE);
        });
        fabCambiar.setOnClickListener(v -> {
            fabCambiar.setVisibility(View.GONE);
            //Guardamos los nuevos datos
            etNombre.setText(etNombre.getText());
            etEmail.setText(etEmail.getText());
            etPassword.setText(etPassword.getText());
            etTelefono.setText(etTelefono.getText());
            etUser.setText(etUser.getText());
            //los volvemos a desactivar
            etNombre.setEnabled(false);
            etEmail.setEnabled(false);
            etPassword.setEnabled(false);
            etTelefono.setEnabled(false);
            etUser.setEnabled(false);
            //Cambiamos los iconos del boton
            fabEdit.setVisibility(View.VISIBLE);


        });
        return vista;
    }

    //Metodo para cagar la imagen seleccionada desde la galeria
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
