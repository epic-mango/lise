package com.chustle.lise.ui.mis_secuencias.secuencia;

import static com.chustle.lise.ui.mis_secuencias.secuencia.AdapterListaPistas.CLASE_MARCADOR;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
import java.util.Calendar;


public class FragmentSecuencia extends Fragment implements Secuencia.SecuenciaChangedListener {


    //Objects
    Secuencia secuencia;
    FileSecuencia files;
    RecyclerView rVPistas;
    ArrayList<Pista> listaPistas;
    OnBackPressedCallback isSavedCallback;
    MenuItem mnuGuardar;

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

        setHasOptionsMenu(true);

        inicializarComponentes(root);


        return root;
    }


    private void inicializarComponentes(final View root) {
        //--------------RECYCLER VIEW PISTAS----------------------------------------------------

        rVPistas = root.findViewById(R.id.recyclerViewPistas_fragmentSecuencia);
        rVPistas.setHasFixedSize(true);
        rVPistas.setLayoutManager(new LinearLayoutManager(getContext()));

        listaPistas = secuencia.getListaPistas();
        rVPistas.setAdapter(new AdapterListaPistas(listaPistas, getActivity()
                .getSupportFragmentManager(), this));

        //------------------- Handle Back button behaviour-----------------------------------------
        isSavedCallback = new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage(getString(R.string.guardar_preguntar));
                dialog.setTitle(getString(R.string.menu_mis_secuencias));
                dialog.setPositiveButton(getString(R.string.guardar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        guardar();
                        Navigation.findNavController(root).navigateUp();
                    }
                });
                dialog.setNegativeButton(getString(R.string.no_guardar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(root).navigateUp();
                    }
                });
                dialog.setNeutralButton(getString(R.string.no_salir), null);

                dialog.show();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), isSavedCallback);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment_secuencia, menu);

        //--------------------------------Inicializar Menu guardar---------------------------------------------
        mnuGuardar = menu.findItem(R.id.mnu_guardar_fragmentSecuencia);
        mnuGuardar.setVisible(false);
    }


    private void guardar() {
        //Escribimos la hora de guardado
        //Write the saving time
        secuencia.setVersionSecuencia(Calendar.getInstance().getTimeInMillis());

        //Save the file
        files.guardarSecuencia(secuencia);

        //Mark as saved
        isSavedCallback.setEnabled(false);
        mnuGuardar.setVisible(false);

        Toast.makeText(getContext(), getString(R.string.guardado_correctamente), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnu_agregar_pista_fragmentSecuencia:
                agregarPista();
                return true;
            case R.id.mnu_guardar_fragmentSecuencia:
                guardar();
                return true;

            //Handle UP Button (Navigation Back button)
            case android.R.id.home:
                if (isSavedCallback.isEnabled()) {
                    isSavedCallback.handleOnBackPressed();
                    return true;
                } else break;
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
                getString(R.string.ejemplo_pista_marcador),
                EntradaDato.TIPO_STRING
        ));

        DialogFragmentEntradaDatos entradaDatos = new DialogFragmentEntradaDatos(new DialogFragmentEntradaDatos.EntradaDatosListener() {
            @Override
            public void aceptar() {

                PistaMarcadores pistaMarcadores = new PistaMarcadores(listaPistas.size(), true, listaDatos.get(0).getDato(), new ArrayList<Marcador>());

                listaPistas.add(pistaMarcadores);
                secuencia.getListaPistasMarcadores().add(pistaMarcadores);
                int index = listaPistas.indexOf(pistaMarcadores);
                rVPistas.getAdapter().notifyItemInserted(index);

                onChange();
            }
        }, getString(R.string.nueva_pista_marcadores), listaDatos);

        entradaDatos.show(getActivity().getSupportFragmentManager(), "Nombre_marcador");
    }


    @Override
    public void onChange() {
        mnuGuardar.setVisible(true);
        isSavedCallback.setEnabled(true);
    }

    @Override
    public void onLongClicPista(int pistaPosition, int tipoPista) {
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getContext());
        alert.setTitle(getString(R.string.eliminar) + " " + listaPistas.get(pistaPosition).getTitulo());
        alert.setMessage(getString(R.string.eliminar_preguntar));
        alert.setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (tipoPista == CLASE_MARCADOR)
                    secuencia.getListaPistasMarcadores().remove(listaPistas.get(pistaPosition));

                //TODO: Agregar los diferentes tipos de pistas para que se eliminen correctamente

                listaPistas.remove(pistaPosition);
                rVPistas.getAdapter().notifyItemRemoved(pistaPosition);

                onChange();

            }
        });

        alert.setNegativeButton(getString(R.string.cancelar), null);

        alert.setNeutralButton(getString((R.string.renombrar)), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                final ArrayList<EntradaDato> listaDatos = new ArrayList<>();
                listaDatos.add(new EntradaDato(
                        getString(R.string.nombre),
                        "",
                        getString(R.string.ejemplo_texto),
                        EntradaDato.TIPO_STRING
                ));

                DialogFragmentEntradaDatos entradaDatos = new DialogFragmentEntradaDatos(new DialogFragmentEntradaDatos.EntradaDatosListener() {
                    @Override
                    public void aceptar() {

                        Pista pistaMarcadores = listaPistas.get(pistaPosition);

                        String titulo = listaDatos.get(0).getDato();
                        pistaMarcadores.setTitulo(titulo);

                        int index = listaPistas.indexOf(pistaMarcadores);
                        if (tipoPista == CLASE_MARCADOR) {
                            secuencia.getListaPistasMarcadores().get(secuencia.getListaPistasMarcadores().indexOf(pistaMarcadores)).setTitulo(titulo);
                        }

                        //TODO:Agregar los otros tipos de pistas

                        rVPistas.getAdapter().notifyItemChanged(index);

                        onChange();
                    }
                }, getString(R.string.renombrar), listaDatos);

                entradaDatos.show(getActivity().getSupportFragmentManager(), "Nombre_marcador");

            }
        });
        alert.show();
    }
}