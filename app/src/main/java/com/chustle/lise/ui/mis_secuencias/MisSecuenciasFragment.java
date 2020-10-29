package com.chustle.lise.ui.mis_secuencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.Files;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MisSecuenciasFragment extends Fragment {

    //Interfaz de usuario---------------------------------------------------------------------------
    private RecyclerView listaSecuencias;
    private ListaSecuenciasAdapter adapterSecuencias;
    private FloatingActionButton fabNuevaSecuencia;

    private MisSecuenciasViewModel misSecuenciasViewModel;
    Files files;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mis_secuencias, container, false);

        files = new Files(getContext());

        inicializarComponentes(root);
        return root;
    }



    private void inicializarComponentes(View root){

        //Lista secuencias----------------------------------------------------------------------------------------------------------------

        listaSecuencias = root.findViewById(R.id.listaSecuencias);
        listaSecuencias.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());


        listaSecuencias.setLayoutManager(llm);

        listaSecuencias.setAdapter(new ListaSecuenciasAdapter(files.getSecuencias()));

        //Floating Action Button Nueva Secuencia-----------------------------------------------------------------------------------------

        fabNuevaSecuencia = root.findViewById(R.id.fabNuevaSecuencia);
        fabNuevaSecuencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                files.crearSecuencia("Prueba","Prueba artista");
            }
        });
    }
}