package com.shareit.shareit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shareit.shareit.model.Usuario;
import com.shareit.shareit.fragment.AcercaDeFragment;
import com.shareit.shareit.fragment.AddFragment;
import com.shareit.shareit.fragment.AjustesFragment;
import com.shareit.shareit.fragment.ComunidadesFragment;
import com.shareit.shareit.fragment.ContentFragment;
import com.shareit.shareit.fragment.DemandasFragment;
import com.shareit.shareit.fragment.OfertasFragment;
import com.shareit.shareit.fragment.PerfilFragment;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,PerfilFragment.OnFragmentInteractionListener,
        AjustesFragment.OnFragmentInteractionListener,AcercaDeFragment.OnFragmentInteractionListener,
        ComunidadesFragment.OnFragmentInteractionListener,ContentFragment.OnFragmentInteractionListener,
        OfertasFragment.OnFragmentInteractionListener,DemandasFragment.OnFragmentInteractionListener,
        AddFragment.OnFragmentInteractionListener{

    //RecyclerView recyclerdemandas;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private ImageView ivProfileNavHeader;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Se recupera el usuario con el que se ha iniciado sesion
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //recyclerdemandas = findViewById(R.id.recyclerDemandas);
        //recyclerdemandas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((v) -> {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Fragment f = new AddFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content,f).commit();
            });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Quitamos la tinta por defecto para que las imagenes se vean bien
        navigationView.setItemIconTintList(null);

        // Se cargan los datos del Nav Header
        View header = navigationView.getHeaderView(0);
        ivProfileNavHeader = header.findViewById(R.id.ivProfileNavHeader);
        tvUsername = header.findViewById(R.id.tvUsername);
        if (currentUser.getPhotoUrl() == null) {
            ivProfileNavHeader.setImageResource(R.drawable.persona);
        }
        else {
            Picasso.with(getApplicationContext()).load(currentUser.getPhotoUrl()).into(ivProfileNavHeader);
        }
        tvUsername.setText(currentUser.getEmail());

        checkUser(); // Se comprueba el estado del usuario en Real-Time Database
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_perfil) {
            fragmentSeleccionado=true;
            fragment = new PerfilFragment();

        } else if (id == R.id.nav_comunidades) {
            fragmentSeleccionado=true;
            fragment=new ComunidadesFragment();

        } else if (id == R.id.nav_productos) {
            fragmentSeleccionado=true;
            fragment = new ContentFragment();

        } else if (id == R.id.nav_ajustes) {
            fragmentSeleccionado=true;
            fragment = new AjustesFragment();

        } else if (id == R.id.nav_acerdaDe) {
            fragmentSeleccionado=true;
            fragment = new AcercaDeFragment();

        } else if (id == R.id.nav_salir) {
            mAuth.signOut();  // Cerramos sesion

            Intent i = new Intent(this, SignIn.class);
            startActivity(i);
            finish();
        }
        if (fragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void checkUser() {
        // Se comprueba si es la primera vez que inicia sesion y se le asigna un perfil en Real-Time Database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() == null) {
                    reference.child("users").child(currentUser.getUid()).setValue(new Usuario(currentUser.getEmail()));
                    Log.d("USER", "Creando el id " + currentUser.getUid());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("USER", databaseError.getMessage());
            }
        });
    }
}