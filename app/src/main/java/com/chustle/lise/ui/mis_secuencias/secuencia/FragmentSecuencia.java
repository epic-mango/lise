package com.chustle.lise.ui.mis_secuencias.secuencia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.FileSecuencia;
import com.chustle.lise.files.models.Marcador;
import com.chustle.lise.files.models.Pista;
import com.chustle.lise.files.models.PistaMarcadores;
import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.entrada_datos.DialogFragmentEntradaDatos;
import com.chustle.lise.ui.entrada_datos.EntradaDato;
import com.chustle.lise.ui.mis_secuencias.ListModelSecuencias;

import java.util.ArrayList;

public class FragmentSecuencia extends Fragment {

    //Objects
    Secuencia secuencia;
    FileSecuencia files;
    RecyclerView rVPistas;
    ArrayList<Pista> listaPistas;

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

        String id = bundle.getString(ListModelSecuencias.ID_SECUENCIA);
        files = new FileSecuencia(getContext());

        secuencia = files.getSecuencia(id);

        //Set AppBar Title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(
                secuencia.getNombreSecuencia() + " - " +
                        secuencia.getArtistaSecuencia());

        inicializarComponentes(root);


        return root;
    }

    private void inicializarComponentes(View root) {
        //--------------RECYCLER VIEW PISTAS----------------------------------------------------

        rVPistas = root.findViewById(R.id.recyclerViewPistas_fragmentSecuencia);
        rVPistas.setHasFixedSize(true);
        rVPistas.setLayoutManager(new LinearLayoutManager(getContext()));

        listaPistas = secuencia.getListaPistas();

        rVPistas.setAdapter(new AdapterListaPistas(listaPistas, getActivity().getSupportFragmentManager()));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment_secuencia, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnu_agregar_pista_fragmentSecuencia:
                agregarPista();
                return true;
            case R.id.mnu_guardar_fragmentSecuencia:
                files.guardarSecuencia(secuencia);
                return true;
        }
        return false;

    }

    private void agregarPista() {
        AlertDialog.Builder agregarPista = new AlertDialog.Builder(getContext());

        String[] lista = {
                getString(R.string.marcadores),
                getString(R.string.anotaciones),
                getString(R.string.audio)
        };

        agregarPista.setTitle(R.string.agregar_pista)
                .setItems(lista, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                agregarPistaMarcadores();
                                break;
                        }
                    }
                });

        agregarPista.show();
    }

    private void agregarPistaMarcadores() {

        final ArrayList<EntradaDato> listaDatos = new ArrayList<>();
        listaDatos.add(new EntradaDato(
                getString(R.string.nombre),
                "",
                getString(R.string.ejemplo_pista_marcador)
        ));

        DialogFragmentEntradaDatos entradaDatos = new DialogFragmentEntradaDatos(new DialogFragmentEntradaDatos.EntradaDatosListener() {
            @Override
            public void aceptar() {

                PistaMarcadores pistaMarcadores = new PistaMarcadores(listaPistas.size(), true, listaDatos.get(0).getDato(), new ArrayList<Marcador>());

                listaPistas.add(pistaMarcadores);
                secuencia.getListaPistasMarcadores().add(pistaMarcadores);
                int index = listaPistas.indexOf(pistaMarcadores);
                rVPistas.getAdapter().notifyItemInserted(index);

            }
        }, getString(R.string.nueva_pista_marcadores), listaDatos);

        entradaDatos.show(getActivity().getSupportFragmentManager(), "Nombre_marcador");
    }
}