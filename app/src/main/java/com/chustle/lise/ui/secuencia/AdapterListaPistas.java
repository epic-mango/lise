package com.chustle.lise.ui.secuencia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Marcador;
import com.chustle.lise.files.models.PistaSecuencia;
import com.chustle.lise.ui.secuencia.marcadores.ViewHolderMarcadores;

import java.util.List;

public class AdapterListaPistas extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int CLASE_MARCADOR = 0;
    private List<PistaSecuencia> listaPistas;

    public AdapterListaPistas(List<PistaSecuencia> listaPistas) {
        this.listaPistas = listaPistas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType) {
            case CLASE_MARCADOR:
                return new ViewHolderMarcadores(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pista_marcador, parent,
                        false), listaPistas.get(getItemCount()-1).isExpandido());
            default:
                return new ViewHolderPistas(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_secuencia, parent,
                        false));

        }


    }

    @Override
    public int getItemViewType(int position) {
        int tipo = -1;
        String claseActual = listaPistas.get(position).getClass().getName();

        if (claseActual.equals(Marcador.class.getName()))
            tipo = CLASE_MARCADOR;

        return tipo;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return listaPistas.size();
    }

    static class ViewHolderPistas extends RecyclerView.ViewHolder {


        public ViewHolderPistas(@NonNull final View v) {
            super(v);


        }
    }



}

