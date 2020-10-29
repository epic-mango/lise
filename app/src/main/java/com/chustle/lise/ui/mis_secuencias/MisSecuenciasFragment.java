package com.chustle.lise.ui.mis_secuencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.Files;
import com.chustle.lise.files.models.Secuencia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MisSecuenciasFragment extends Fragment {

    Files files;
    //Interfaz de usuario---------------------------------------------------------------------------
    private RecyclerView listaSecuencias;
    private ListaSecuenciasAdapter adapterSecuencias;
    private FloatingActionButton fabNuevaSecuencia;
    private MisSecuenciasViewModel misSecuenciasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mis_secuencias, container, false);

        files = new Files(getContext());

        inicializarComponentes(root);
        return root;
    }


    private void inicializarComponentes(View root) {

        //Lista secuencias----------------------------------------------------------------------------------------------------------------

        listaSecuencias = root.findViewById(R.id.listaSecuencias);
        listaSecuencias.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());


        listaSecuencias.setLayoutManager(llm);
        final List<SecuenciaListModel> modelosListaSecuencias = files.getSecuencias();
        listaSecuencias.setAdapter(new ListaSecuenciasAdapter(modelosListaSecuencias));

        //Floating Action Button Nueva Secuencia-----------------------------------------------------------------------------------------

        fabNuevaSecuencia = root.findViewById(R.id.fabNuevaSecuencia);
        fabNuevaSecuencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Secuencia secuencia = files.crearSecuencia("Prueba", "Prueba artista");
                modelosListaSecuencias.add(new SecuenciaListModel(secuencia.getNombreSecuencia()
                        , secuencia.getArtistaSecuencia(), secuencia.getIdSecuencia()+".json"));


                listaSecuencias.getAdapter().notifyItemInserted(modelosListaSecuencias.size()-1);
            }
        });
    }
}