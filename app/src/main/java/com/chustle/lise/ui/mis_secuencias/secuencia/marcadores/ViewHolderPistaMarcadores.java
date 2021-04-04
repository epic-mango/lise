package com.chustle.lise.ui.mis_secuencias.secuencia.marcadores;

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

import java.util.ArrayList;

public class ViewHolderPistaMarcadores extends RecyclerView.ViewHolder {

    RecyclerView recyclerViewMarcadores;
    PistaMarcadores infoPistaMarcadores;
    ArrayList<Marcador> listaMarcadores;
    FragmentManager supportFragmentManager;
    private LinearLayout layoutCabecera,
            layoutAgregarMarcador;
    private TextView lblMarcadorActual,
            lblTituloPista;
    private boolean expandido;

    private String titulo;


    public ViewHolderPistaMarcadores(@NonNull final View v, FragmentManager supportFragmentManager) {
        super(v);
        this.supportFragmentManager = supportFragmentManager;
        inicializarComponentes(v);
    }

    public void inicializarComponentes(final View root) {
        recyclerViewMarcadores = root.findViewById(R.id.recyclerViewMarcadores_cardPistaMarcador);
        layoutCabecera = root.findViewById(R.id.linearLayoutCabecera_CardPistaMarcador);
        layoutAgregarMarcador = root.findViewById(R.id.linearLayoutAgregarMarcador_CardPistaMarcador);
        lblMarcadorActual = root.findViewById(R.id.txtMarcadorActual_CardPistaMarcador);
        lblTituloPista = root.findViewById(R.id.lblTituloPista_CardPistaMarcador);

        //Alternate the expansion of the components-------------------------------------------------
        layoutCabecera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandido)
                    contraer();
                else
                    expandir();

            }
        });

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
                        recyclerViewMarcadores.getAdapter().notifyItemInserted(listaMarcadores.size()-1);
                    }
                });
                dialog.show(supportFragmentManager, "EditarMarcador");
            }
        });


        //-------------------------------------

    }

    public void setInfoPistaMarcadores(PistaMarcadores infoPistaMarcadores) {
        this.infoPistaMarcadores = infoPistaMarcadores;
        this.listaMarcadores  = infoPistaMarcadores.getListaMarcadores();
        this.lblTituloPista.setText(infoPistaMarcadores.getTitulo());
        this.titulo = infoPistaMarcadores.getTitulo();

        if (this.infoPistaMarcadores.isExpandido())
            expandir();
        else
            contraer();

        recyclerViewMarcadores.setAdapter(new AdapterListaMarcadores(listaMarcadores));
    }

    private void expandir() {
        recyclerViewMarcadores.setVisibility(View.VISIBLE);
        layoutAgregarMarcador.setVisibility(View.VISIBLE);

        this.expandido = true;
    }

    private void contraer() {
        recyclerViewMarcadores.setVisibility(View.GONE);
        layoutAgregarMarcador.setVisibility(View.GONE);

        this.expandido = false;
    }
}