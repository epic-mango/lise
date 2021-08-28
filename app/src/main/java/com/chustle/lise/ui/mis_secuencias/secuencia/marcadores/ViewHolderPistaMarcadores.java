package com.chustle.lise.ui.mis_secuencias.secuencia.marcadores;

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
import com.chustle.lise.ui.mis_secuencias.secuencia.FragmentSecuencia;
import com.chustle.lise.ui.mis_secuencias.secuencia.ViewHolderPista;
;

import java.util.ArrayList;

public class ViewHolderPistaMarcadores extends ViewHolderPista {


    RecyclerView recyclerViewMarcadores;
    PistaMarcadores infoPistaMarcadores;
    ArrayList<Marcador> listaMarcadores;
    FragmentManager supportFragmentManager;
    private LinearLayout layoutAgregarMarcador;
    private TextView lblMarcadorActual,
            lblTituloPista;

    private String titulo;

    public ViewHolderPistaMarcadores(@NonNull final View v, FragmentManager supportFragmentManager, Secuencia.SecuenciaChangedListener listener) {
        super(v);
        this.supportFragmentManager = supportFragmentManager;
        this.listener = listener;
        inicializarComponentes(v);
    }

    public void inicializarComponentes(final View root) {
        recyclerViewMarcadores = root.findViewById(R.id.recyclerViewMarcadores_cardPistaMarcador);

        layoutAgregarMarcador = root.findViewById(R.id.linearLayoutAgregarMarcador_CardPistaMarcador);
        lblMarcadorActual = root.findViewById(R.id.txtMarcadorActual_CardPistaMarcador);
        lblTituloPista = root.findViewById(R.id.lblTituloPista_CardPistaMarcador);



        //-----------------------------------------Recycler view------------------------------------


        recyclerViewMarcadores.setLayoutManager(new LinearLayoutManager(root.getContext()));

        //------------------------------lblAgregarMarcador------------------------------------------


        layoutAgregarMarcador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragmentEditarMarcador dialog = new DialogFragmentEditarMarcador(
                        null, titulo, 1, 0, new DialogFragmentEditarMarcador.DialogFragmentEditarMarcadorListener() {
                    @Override
                    public void onAccept(Marcador marcador) {
                        listaMarcadores.add(marcador);
                        recyclerViewMarcadores.getAdapter().notifyItemInserted(listaMarcadores.size() - 1);
                        listener.onChange();
                    }
                });
                dialog.show(supportFragmentManager, "EditarMarcador");
            }
        });


        //-------------------------------------

    }

    public void setInfoPistaMarcadores(PistaMarcadores infoPistaMarcadores) {
        this.infoPistaMarcadores = infoPistaMarcadores;
        this.listaMarcadores = infoPistaMarcadores.getListaMarcadores();
        this.lblTituloPista.setText(infoPistaMarcadores.getTitulo());
        this.titulo = infoPistaMarcadores.getTitulo();

        if (this.infoPistaMarcadores.isExpandido())
            expandir();
        else
            contraer();

        recyclerViewMarcadores.setAdapter(new AdapterListaMarcadores(listaMarcadores, new AdapterListaMarcadores.MarcadorListener() {
            @Override
            public void onClick(int position) {
                //Edit bookmark
                DialogFragmentEditarMarcador dialog = new DialogFragmentEditarMarcador(
                        listaMarcadores.get(position), titulo, 1, 0, new DialogFragmentEditarMarcador.DialogFragmentEditarMarcadorListener() {
                    @Override
                    public void onAccept(Marcador marcador) {
                        recyclerViewMarcadores.getAdapter().notifyItemChanged(listaMarcadores.indexOf(marcador));
                        listener.onChange();
                    }
                });
                dialog.show(supportFragmentManager, "EditarMarcador");
            }

            @Override
            public void onLongClick(final int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                alert.setTitle(itemView.getResources().getString(R.string.marcadores));
                alert.setMessage(itemView.getResources().getString(R.string.eliminar_marcador));
                alert.setPositiveButton(itemView.getResources().getString(R.string.eliminar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaMarcadores.remove(position);
                        recyclerViewMarcadores.getAdapter().notifyItemRemoved(position);
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
        recyclerViewMarcadores.setVisibility(View.VISIBLE);
        layoutAgregarMarcador.setVisibility(View.VISIBLE);
        infoPistaMarcadores.setExpandido(true);
        this.expandido = true;
    }

    @Override
    public void contraer() {
        recyclerViewMarcadores.setVisibility(View.GONE);
        layoutAgregarMarcador.setVisibility(View.GONE);
        infoPistaMarcadores.setExpandido(false);
        this.expandido = false;
    }


}