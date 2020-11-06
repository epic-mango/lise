package com.chustle.lise.ui.secuencia.marcadores;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.PistaMarcadores;

public class ViewHolderMarcadores extends RecyclerView.ViewHolder {

    RecyclerView recyclerViewMarcadores;
    PistaMarcadores infoPistaMarcadores;
    private LinearLayout layoutCabecera,
            layoutColumnasTabla,
            layoutAgregarMarcador;
    private TextView lblMarcadorActual,
            lblCompasActual,
    lblTituloPista;
    private boolean expandido;



    public ViewHolderMarcadores(@NonNull final View v) {
        super(v);

        inicializarComponentes(v);
    }

    public void inicializarComponentes(View root) {
        recyclerViewMarcadores = root.findViewById(R.id.recyclerViewMarcadores_cardPistaMarcador);
        layoutCabecera = root.findViewById(R.id.linearLayoutCabecera_CardPistaMarcador);
        layoutColumnasTabla = root.findViewById(R.id.linearLayoutColumnasTabla_CardPistaMarcador);
        layoutAgregarMarcador = root.findViewById(R.id.linearLayoutAgregarMarcador_CardPistaMarcador);
        lblMarcadorActual = root.findViewById(R.id.txtMarcadorActual_CardPistaMarcador);
        lblCompasActual = root.findViewById(R.id.txtCompasActual_CardPistaMarcador);
        lblTituloPista = root.findViewById(R.id.lblTituloPista_CardPistaMarcador);

        //Alternate the expansion of the components
        layoutCabecera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandido)
                    contraer();
                else
                    expandir();

            }
        });

    }

    public void setInfoPistaMarcadores(PistaMarcadores infoPistaMarcadores) {
        this.infoPistaMarcadores = infoPistaMarcadores;
        this.lblTituloPista.setText(infoPistaMarcadores.getTitulo());

        if (this.infoPistaMarcadores.isExpandido())
            expandir();
        else
            contraer();
    }

    private void expandir() {
        layoutColumnasTabla.setVisibility(View.VISIBLE);
        recyclerViewMarcadores.setVisibility(View.VISIBLE);
        layoutAgregarMarcador.setVisibility(View.VISIBLE);

        this.expandido = true;
    }

    private void contraer() {
        layoutColumnasTabla.setVisibility(View.GONE);
        recyclerViewMarcadores.setVisibility(View.GONE);
        layoutAgregarMarcador.setVisibility(View.GONE);

        this.expandido = false;

    }
}