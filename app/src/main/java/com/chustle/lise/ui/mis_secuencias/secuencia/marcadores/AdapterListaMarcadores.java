package com.chustle.lise.ui.mis_secuencias.secuencia.marcadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Marcador;

import java.util.ArrayList;

public class AdapterListaMarcadores extends RecyclerView.Adapter<AdapterListaMarcadores.ViewHolderMarcador> {

    ArrayList<Marcador> listaMarcadores;

    public AdapterListaMarcadores(ArrayList<Marcador> listaMarcadores) {
        this.listaMarcadores = listaMarcadores;
    }

    @NonNull
    @Override
    public ViewHolderMarcador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_marcador, parent, false);

        return new ViewHolderMarcador(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMarcador holder, int position) {
        holder.lblCompasInicio.setText(listaMarcadores.get(position).getInicio()+"");
        holder.lblNombreMarcador.setText(listaMarcadores.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return listaMarcadores.size();
    }

    class ViewHolderMarcador extends RecyclerView.ViewHolder {

        TextView lblCompasInicio, lblNombreMarcador;

        public ViewHolderMarcador(@NonNull View itemView) {
            super(itemView);


            lblCompasInicio = itemView.findViewById(R.id.lblCompasInicio_CardMarcador);
            lblNombreMarcador = itemView.findViewById(R.id.lblNombreMarcador_cardMarcador);
        }
    }
}


