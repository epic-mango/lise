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
import com.chustle.lise.files.FileSecuencia;
import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.entrada_datos.DialogFragmentEntradaDatos;
import com.chustle.lise.ui.entrada_datos.EntradaDato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentMisSecuencias extends Fragment {

    FileSecuencia files;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mis_secuencias, container, false);

        files = new FileSecuencia(getContext());

        inicializarComponentes(root);
        return root;
    }


    private void inicializarComponentes(View root) {

        //Recycler View secuencias--------------------------------------------------------------------------

        final RecyclerView rVSecuencias = root.findViewById(R.id.listaSecuencias);
        rVSecuencias.setHasFixedSize(true);
        rVSecuencias.setLayoutManager(new LinearLayoutManager(getContext()));

        final List<ListModelSecuencias> modelosListaSecuencias = files.getSecuenciasListModel();
        rVSecuencias.setAdapter(new AdapterListaSecuencias(modelosListaSecuencias, new AdapterListaSecuencias.SecuenciasAdapterListener() {
            @Override
            public void onClic(int position) {

                //Get the clicked sequence to be send to the FragmentSecuencia
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

                final ArrayList<EntradaDato> listaDatos = new ArrayList<>();
                listaDatos.add(new EntradaDato(getString(R.string.nombre), "", getString(R.string.ejemplo_nombre_secuencia)));
                listaDatos.add(new EntradaDato(getString(R.string.artista), "", getString(R.string.ejemplo_artista_secuencia)));


                //Create and show a dialog fragment to get the initial information of a sequence
                DialogFragment propiedadesSecuencia = new DialogFragmentEntradaDatos(new DialogFragmentEntradaDatos.EntradaDatosListener() {

                    //On Accept
                    @Override
                    public void aceptar() {

                        //Create a file on the device with the information from the dialog fragment
                        Secuencia secuencia = new Secuencia();
                        secuencia.setNombreSecuencia(listaDatos.get(0).getDato());
                        secuencia.setArtistaSecuencia(listaDatos.get(1).getDato());
                        secuencia.setIdSecuencia(Calendar.getInstance().getTimeInMillis());

                        files.guardarSecuencia(secuencia);

                        //Add the new sequence to the list to fill the recyclerView @rVSecuencias
                        modelosListaSecuencias.add(new ListModelSecuencias(secuencia.getNombreSecuencia()
                                , secuencia.getArtistaSecuencia(), secuencia.getIdSecuencia() + ".json"));

                        //Nototify that a sequence has been added
                        rVSecuencias.getAdapter().notifyItemInserted(modelosListaSecuencias.size() - 1);


                    }
                }, getString(R.string.propiedades_secuencia), listaDatos);

                //Show the dialog fragment
                propiedadesSecuencia.show(getActivity().getSupportFragmentManager(), "PROPIEDADES_SECUENCIA");
            }
        });
    }

    private void navegarASecuencia(ListModelSecuencias secuencia) {
        Bundle bundle = new Bundle();

        //Fill the extra information to the FragmentSecuencia

        bundle.putString(ListModelSecuencias.NOMBRE_SECUENCIA, secuencia.getNombreSecuencia());
        bundle.putString(ListModelSecuencias.ARTISTA_SECUENCIA, secuencia.getArtistaSecuencia());
        bundle.putString(ListModelSecuencias.ID_SECUENCIA, secuencia.getRutaArchivo());

        //Navigate to FragmentSecuencia
        Navigation.findNavController(getView()).navigate(R.id.nav_dst_mis_secuencias_to_nav_dst_secuencia, bundle);
    }
}