package com.shareit.shareit;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.shareit.shareit.fragment.AcercaDeFragment;
import com.shareit.shareit.fragment.AjustesFragment;
import com.shareit.shareit.fragment.ComunidadesFragment;
import com.shareit.shareit.fragment.ContentFragment;
import com.shareit.shareit.fragment.DemandasFragment;
import com.shareit.shareit.fragment.OfertasFragment;
import com.shareit.shareit.fragment.PerfilFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,PerfilFragment.OnFragmentInteractionListener,
        AjustesFragment.OnFragmentInteractionListener,AcercaDeFragment.OnFragmentInteractionListener,
        ComunidadesFragment.OnFragmentInteractionListener,ContentFragment.OnFragmentInteractionListener,
        OfertasFragment.OnFragmentInteractionListener,DemandasFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Quitamos la tinta por defecto para que las imagenes se vean bien
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.action_buscar) {
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
                finish();
        }
        if (fragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
