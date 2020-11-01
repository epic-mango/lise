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
    //Interfaz de usuario---------------------------------------------------------------------------
    private RecyclerView listaSecuencias;
    private ListaSecuenciasAdapter adapterSecuencias;
    private FloatingActionButton fabNuevaSecuencia;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mis_secuencias, container, false);

        files = new Files(getContext());

        inicializarComponentes(root);
        return root;
    }


    private void inicializarComponentes(View root) {

        //Lista secuencias--------------------------------------------------------------------------

        listaSecuencias = root.findViewById(R.id.listaSecuencias);
        listaSecuencias.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());


        listaSecuencias.setLayoutManager(llm);

        final List<SecuenciasListModel> modelosListaSecuencias = files.getSecuencias();
        listaSecuencias.setAdapter(new ListaSecuenciasAdapter(modelosListaSecuencias, new ListaSecuenciasAdapter.SecuenciasAdapterListener() {
            @Override
            public void onClic(int position) {
                SecuenciasListModel clicada = modelosListaSecuencias.get(position);
                navegarASecuencia(clicada);
            }

            @Override
            public void onLongClic(int position) {
                SecuenciasListModel clicada = modelosListaSecuencias.get(position);
            }
        }));

        //Floating Action Button Nueva Secuencia----------------------------------------------------

        fabNuevaSecuencia = root.findViewById(R.id.fabNuevaSecuencia);
        fabNuevaSecuencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment propiedadesSecuencia = new DialogFragmentPropiedadesSecuencia(new DialogFragmentPropiedadesSecuencia.PropiedadesSecuenciaListener() {
                    @Override
                    public void aceptar(String nombre, String artista) {
                        Secuencia secuencia = files.crearSecuencia(nombre, artista);

                        modelosListaSecuencias.add(new SecuenciasListModel(secuencia.getNombreSecuencia()
                                , secuencia.getArtistaSecuencia(), secuencia.getIdSecuencia() + ".json"));


                        listaSecuencias.getAdapter().notifyItemInserted(modelosListaSecuencias.size() - 1);
                    }
                }, null);


                propiedadesSecuencia.show(getActivity().getSupportFragmentManager(), "PROPIEDADES_SECUENCIA");
            }
        });
    }

    private void navegarASecuencia(SecuenciasListModel secuencia) {
        Bundle bundle = new Bundle();

        bundle.putString(SecuenciasListModel.NOMBRE_SECUENCIA, secuencia.getNombreSecuencia());
        bundle.putString(SecuenciasListModel.ARTISTA_SECUENCIA, secuencia.getArtistaSecuencia());
        bundle.putString(SecuenciasListModel.ID_SECUENCIA, secuencia.getRutaArchivo());

        Navigation.findNavController(getView()).navigate(R.id.nav_dst_mis_secuencias_to_nav_dst_secuencia, bundle);
    }
}