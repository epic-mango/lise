package com.chustle.lise.ui.mis_secuencias.secuencia;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Marcador;
import com.chustle.lise.files.models.Pista;
import com.chustle.lise.files.models.PistaMarcadores;
import com.chustle.lise.ui.mis_secuencias.secuencia.marcadores.ViewHolderPistaMarcadores;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaPistas extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    //Static Object class identifiers
    public static final int CLASE_MARCADOR = 0;

    //List of information for the Adapter
    private List<Pista> listaPistas;

    FragmentManager supportFragmentManager;
    FragmentSecuencia.SecuenciaChangedListener listener;

    public AdapterListaPistas(List<Pista> listaPistas, FragmentManager supportFragmentManager, FragmentSecuencia.SecuenciaChangedListener listener) {
        this.listener = listener;
        this.listaPistas = listaPistas;
        this.supportFragmentManager =supportFragmentManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Switch between view types to inflate the right layout

        switch (viewType) {
            case CLASE_MARCADOR:
                return new ViewHolderPistaMarcadores(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pista_marcador, parent,
                        false), supportFragmentManager, listener);
            default:
                return null;

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

        switch (getItemViewType(position)) {
            case CLASE_MARCADOR:
                ((ViewHolderPistaMarcadores) holder).setInfoPistaMarcadores((PistaMarcadores) listaPistas.get(position));
                break;
        }
    }


    @Override
    public int getItemCount() {
        return listaPistas.size();
    }

}





