package com.chustle.lise.ui.secuencia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.mis_secuencias.ListModelSecuencias;

import java.util.ArrayList;

public class FragmentSecuencia extends Fragment {

    //Components


    //Object
    Secuencia secuencia;

    public FragmentSecuencia() {
        // Required empty public constructor
    }

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

        String nombre = bundle.getString(ListModelSecuencias.NOMBRE_SECUENCIA);
        String artista = bundle.getString(ListModelSecuencias.ARTISTA_SECUENCIA);
        String id = bundle.getString(ListModelSecuencias.ID_SECUENCIA);


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(nombre + " - " + artista);

        inicializarComponentes(root);


        return root;
    }

    private void inicializarComponentes(View root){
        //--------------RECYCLER VIEW PISTAS----------------------------------------------------

        RecyclerView rVPistas = root.findViewById(R.id.recyclerViewPistas_fragmentSecuencia);
        rVPistas.setHasFixedSize(true);
        rVPistas.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<ListModelPista> listaPistas = new ArrayList<>();
        listaPistas.add(new ListModelPista());
        listaPistas.add(new ListModelPista());
        listaPistas.add(new ListModelPista());
        listaPistas.add(new ListModelPista());
        listaPistas.add(new ListModelPista());

        rVPistas.setAdapter(new AdapterListaPistas(listaPistas));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment_secuencia, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnu_agregar_pista:

                return true;
        }
        return false;

    }
}