package com.chustle.lise.ui.mis_secuencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.Files;
import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.secuencia.DialogFragmentPropiedadesSecuencia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentMisSecuencias extends Fragment {

    Files files;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mis_secuencias, container, false);

        files = new Files(getContext());

        inicializarComponentes(root);
        return root;
    }


    private void inicializarComponentes(View root) {

        //Recycler View secuencias--------------------------------------------------------------------------

        final RecyclerView rVSecuencias = root.findViewById(R.id.listaSecuencias);
        rVSecuencias.setHasFixedSize(true);
        rVSecuencias.setLayoutManager(new LinearLayoutManager(getContext()));

        final List<ListModelSecuencias> modelosListaSecuencias = files.getSecuencias();
        rVSecuencias.setAdapter(new AdapterListaSecuencias(modelosListaSecuencias, new AdapterListaSecuencias.SecuenciasAdapterListener() {
            @Override
            public void onClic(int position) {
                ListModelSecuencias clicada = modelosListaSecuencias.get(position);
                navegarASecuencia(clicada);
            }

            @Override
            public void onLongClic(int position) {
                ListModelSecuencias clicada = modelosListaSecuencias.get(position);
            }
        }));

        //Floating Action Button Nueva Secuencia----------------------------------------------------

        FloatingActionButton fabNuevaSecuencia = root.findViewById(R.id.fabNuevaSecuencia);
        fabNuevaSecuencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment propiedadesSecuencia = new DialogFragmentPropiedadesSecuencia(new DialogFragmentPropiedadesSecuencia.PropiedadesSecuenciaListener() {
                    @Override
                    public void aceptar(String nombre, String artista) {
                        Secuencia secuencia = files.crearSecuencia(nombre, artista);

                        modelosListaSecuencias.add(new ListModelSecuencias(secuencia.getNombreSecuencia()
                                , secuencia.getArtistaSecuencia(), secuencia.getIdSecuencia() + ".json"));


                        rVSecuencias.getAdapter().notifyItemInserted(modelosListaSecuencias.size() - 1);
                    }
                }, null);


                propiedadesSecuencia.show(getActivity().getSupportFragmentManager(), "PROPIEDADES_SECUENCIA");
            }
        });
    }

    private void navegarASecuencia(ListModelSecuencias secuencia) {
        Bundle bundle = new Bundle();

        bundle.putString(ListModelSecuencias.NOMBRE_SECUENCIA, secuencia.getNombreSecuencia());
        bundle.putString(ListModelSecuencias.ARTISTA_SECUENCIA, secuencia.getArtistaSecuencia());
        bundle.putString(ListModelSecuencias.ID_SECUENCIA, secuencia.getRutaArchivo());

        Navigation.findNavController(getView()).navigate(R.id.nav_dst_mis_secuencias_to_nav_dst_secuencia, bundle);
    }
}