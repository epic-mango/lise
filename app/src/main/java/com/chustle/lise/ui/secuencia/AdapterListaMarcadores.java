package com.chustle.lise.ui.secuencia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.PistaMarcadores;
import com.chustle.lise.files.models.Pista;
import com.chustle.lise.ui.secuencia.marcadores.ViewHolderMarcadores;

import java.util.List;

public class AdapterListaMarcadores extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Static Object class identifiers
    public static final int CLASE_MARCADOR = 0;

    //List of information for the Adapter
    private List<Pista> listaPistas;

    public AdapterListaMarcadores(List<Pista> listaPistas) {
        this.listaPistas = listaPistas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Switch between view types to inflate the right layout
        switch (viewType) {
            case CLASE_MARCADOR:
                int i = getItemCount();
                return new ViewHolderMarcadores(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pista_marcador, parent,
                        false));
            default:
                return new ViewHolderPistas(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_secuencia, parent,
                        false));

        }


    }

    @Override
    public int getItemViewType(int position) {
        int tipo = -1;

        //Class name of the actual element
        String claseActual = listaPistas.get(position).getClass().getName();

        //returning the class int identifiers
        if (claseActual.equals(PistaMarcadores.class.getName()))
            tipo = CLASE_MARCADOR;

        return tipo;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case CLASE_MARCADOR:
                ((ViewHolderMarcadores) holder).setInfoPistaMarcadores((PistaMarcadores) listaPistas.get(position));
                break;
        }
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

