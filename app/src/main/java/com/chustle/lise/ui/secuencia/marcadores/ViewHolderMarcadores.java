package com.chustle.lise.ui.secuencia.marcadores;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;

public class ViewHolderMarcadores extends RecyclerView.ViewHolder {

    RecyclerView recyclerViewMarcadores;
    private LinearLayout layoutCabecera,
            layoutColumnasTabla,
            layoutAgregarMarcador;
    private TextView txtMarcadorActual,
            txtCompasActual;
    private boolean expandido;


    public ViewHolderMarcadores(@NonNull final View v, final boolean exp) {
        super(v);

        //Initializing the boolean that saves if the track was collapsed or expanded
        this.expandido = exp;

        inicializarComponentes(v);

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

    public void inicializarComponentes(View root) {
        recyclerViewMarcadores = root.findViewById(R.id.recyclerViewMarcadores_cardPistaMarcador);
        layoutCabecera = root.findViewById(R.id.linearLayoutCabecera_CardPistaMarcador);
        layoutColumnasTabla = root.findViewById(R.id.linearLayoutColumnasTabla_CardPistaMarcador);
        layoutAgregarMarcador = root.findViewById(R.id.linearLayoutAgregarMarcador_CardPistaMarcador);
        txtMarcadorActual = root.findViewById(R.id.txtMarcadorActual_CardPistaMarcador);
        txtCompasActual = root.findViewById(R.id.txtCompasActual_CardPistaMarcador);
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