package com.chustle.lise.ui.secuencia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.mis_secuencias.SecuenciasListModel;

public class FragmentSecuencia extends Fragment {

    public FragmentSecuencia() {
        // Required empty public constructor
    }

    Secuencia secuencia;

    public static FragmentSecuencia newInstance(Secuencia secuencia) {
        FragmentSecuencia fragment = new FragmentSecuencia();
        fragment.secuencia = secuencia;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_secuencia, container, false);

        Bundle bundle = getArguments();

        String nombre = bundle.getString(SecuenciasListModel.NOMBRE_SECUENCIA);
        String artista = bundle.getString(SecuenciasListModel.ARTISTA_SECUENCIA);
        String id = bundle.getString(SecuenciasListModel.ID_SECUENCIA);


        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(nombre + " - " + artista);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_secuencia, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnu_agregar_pista:

                return  true;
        }

        return false;

    }
}