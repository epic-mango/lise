package com.chustle.lise.ui.mis_secuencias.secuencia.anotaciones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Marcador;
import com.chustle.lise.files.models.PistaMarcadores;
import com.chustle.lise.files.models.Secuencia;
import com.chustle.lise.ui.mis_secuencias.secuencia.ViewHolderPista;

import java.util.ArrayList;

;

public class ViewHolderPistaAnotaciones extends ViewHolderPista {


    RecyclerView recyclerViewAnotaciones;
    PistaMarcadores infoPistaAnotaciones;
    ArrayList<Marcador> listaAnotaciones;
    FragmentManager supportFragmentManager;
    private LinearLayout layoutAgregarAnotacion;
    private TextView lblRestante,
            lblTituloPista;

    private String titulo;

    public ViewHolderPistaAnotaciones(@NonNull final View v, FragmentManager supportFragmentManager, Secuencia.SecuenciaChangedListener listener) {
        super(v);
        this.supportFragmentManager = supportFragmentManager;
        this.listener = listener;
        inicializarComponentes(v);
    }

    public void inicializarComponentes(final View root) {
        recyclerViewAnotaciones = root.findViewById(R.id.recyclerViewAnotaciones_cardPistaAnotacion);

        layoutAgregarAnotacion = root.findViewById(R.id.linearLayoutAgregarAnotacion_CardPistaAnotacion);
        lblRestante = root.findViewById(R.id.txtRestante_CardPistaAnotacion);
        lblTituloPista = root.findViewById(R.id.lblTituloPista_CardPistaAnotacion);



        //-----------------------------------------Recycler view------------------------------------


        recyclerViewAnotaciones.setLayoutManager(new LinearLayoutManager(root.getContext()));

        //------------------------------lblAgregarAnotacion------------------------------------------


        layoutAgregarAnotacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragmentEditarAnotacion dialog = new DialogFragmentEditarAnotacion(
                        null, titulo, 1, 0, new DialogFragmentEditarAnotacion.DialogFragmentEditarAnotacionListener() {
                    @Override
                    public void onAccept(Marcador marcador) {
                        listaAnotaciones.add(marcador);
                        recyclerViewAnotaciones.getAdapter().notifyItemInserted(listaAnotaciones.size() - 1);
                        listener.onChange();
                    }
                });
                dialog.show(supportFragmentManager, "EditarAnotacion");
            }
        });


        //-------------------------------------

    }

    public void setInfoPistaAnotaciones(PistaMarcadores infoPistaAnotaciones) {
        this.infoPistaAnotaciones = infoPistaAnotaciones;
        this.listaAnotaciones = infoPistaAnotaciones.getListaMarcadores();
        this.lblTituloPista.setText(infoPistaAnotaciones.getTitulo());
        this.titulo = infoPistaAnotaciones.getTitulo();

        if (this.infoPistaAnotaciones.isExpandido())
            expandir();
        else
            contraer();

        recyclerViewAnotaciones.setAdapter(new AdapterListaAnotaciones(listaAnotaciones, new AdapterListaAnotaciones.AnotacionListener() {
            @Override
            public void onClick(int position) {
                //Edit bookmark
                DialogFragmentEditarAnotacion dialog = new DialogFragmentEditarAnotacion(
                        listaAnotaciones.get(position), titulo, 1, 0, new DialogFragmentEditarAnotacion.DialogFragmentEditarAnotacionListener() {
                    @Override
                    public void onAccept(Marcador marcador) {
                        recyclerViewAnotaciones.getAdapter().notifyItemChanged(listaAnotaciones.indexOf(marcador));
                        listener.onChange();
                    }
                });
                dialog.show(supportFragmentManager, "EditarMarcador");
            }

            @Override
            public void onLongClick(final int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                alert.setTitle(itemView.getResources().getString(R.string.marcadores));
                alert.setMessage(itemView.getResources().getString(R.string.eliminar_anotacion));
                alert.setPositiveButton(itemView.getResources().getString(R.string.eliminar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaAnotaciones.remove(position);
                        recyclerViewAnotaciones.getAdapter().notifyItemRemoved(position);
                        listener.onChange();
                    }
                });

                alert.setNegativeButton(itemView.getResources().getString(R.string.cancelar), null);

                alert.show();
            }
        }));
    }

    @Override
    public void expandir() {
        recyclerViewAnotaciones.setVisibility(View.VISIBLE);
        layoutAgregarAnotacion.setVisibility(View.VISIBLE);
        infoPistaAnotaciones.setExpandido(true);
        this.expandido = true;
    }

    @Override
    public void contraer() {
        recyclerViewAnotaciones.setVisibility(View.GONE);
        layoutAgregarAnotacion.setVisibility(View.GONE);
        infoPistaAnotaciones.setExpandido(false);
        this.expandido = false;
    }


}