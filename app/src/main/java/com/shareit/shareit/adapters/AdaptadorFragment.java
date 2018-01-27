package com.shareit.shareit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 27/01/2018.
 */

public class AdaptadorFragment extends FragmentPagerAdapter {

    private final List<Fragment> listaFragments = new ArrayList<>();
    private final List<String> listaTitutlos = new ArrayList<>();

    public AdaptadorFragment (FragmentManager fm){
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listaTitutlos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }

    public void addFragment(Fragment f,String titulo){
        listaFragments.add(f);
        listaTitutlos.add(titulo);
    }
}
