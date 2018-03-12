package com.shareit.shareitdam.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shareit.shareitdam.R;
import com.shareit.shareitdam.adapters.AdaptadorFragment;


public class ContentFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static int rotacion = 0;

    View vista;
    private AppBarLayout appBarLayout;
    private TabLayout pestañas;
    private ViewPager viewPager;


    public ContentFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Creamos las pestañas de los fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_content, container, false);

        if(rotacion==0) {
            View parent = (View) container.getParent();
            if (appBarLayout == null) {
                appBarLayout = parent.findViewById(R.id.appbar);
                pestañas = new TabLayout(getActivity());
                pestañas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
                appBarLayout.addView(pestañas);

                viewPager = vista.findViewById(R.id.contentFragment);
                llenarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestañas.setupWithViewPager(viewPager);
            }
            pestañas.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            rotacion=1;
        }
        return vista;
    }

    //LLenamos el fragment vacio con los dos que usaremos para deslizar  y le asiganmos los fragment y el titulo de las pestañas
    private void llenarViewPager(ViewPager viewPager) {
        AdaptadorFragment adapter = new AdaptadorFragment(getFragmentManager());
        adapter.addFragment(new OfertasFragment(),"OFERTAS");
        adapter.addFragment(new DemandasFragment(),"DEMANDAS");

        viewPager.setAdapter(adapter);
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

    // Usamos el onDestroyView para destruir las pestañas de ofertas y demandas y que con otros fragment no se vean
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rotacion==0) {
            appBarLayout.removeView(pestañas);
        }
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
