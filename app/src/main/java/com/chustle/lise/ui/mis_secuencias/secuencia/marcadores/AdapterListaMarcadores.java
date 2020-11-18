package com.chustle.lise.ui.mis_secuencias.secuencia.marcadores;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.files.models.Marcador;

import java.util.ArrayList;


public class AdapterListaMarcadores extends RecyclerView.Adapter<AdapterListaMarcadores.ViewHolderMarcador> {

    private static final int TIPO_TIEMPO = 1;
    private static final int TIPO_MARCADOR = 2;


    @Override
    public int getItemViewType(int position) {
        return listaMarcadores.get(position).isTiempo() ? TIPO_TIEMPO : TIPO_MARCADOR;
    }

    ArrayList<Marcador> listaMarcadores;

    public AdapterListaMarcadores(ArrayList<Marcador> listaMarcadores) {
        this.listaMarcadores = listaMarcadores;
    }

    @NonNull
    @Override
    public ViewHolderMarcador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_marcador, parent, false);

        return new ViewHolderMarcador(root, viewType == TIPO_TIEMPO ?
                root.getResources().getDrawable(R.drawable.ic_time_black) :
                root.getResources().getDrawable(R.drawable.ic_compas));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderMarcador holder, int position) {
        holder.lblCompasInicio.setText(listaMarcadores.get(position).getInicio() + "");
        holder.lblNombreMarcador.setText(listaMarcadores.get(position).getNombre());

        holder.setImgTipo();
    }

    @Override
    public int getItemCount() {
        return listaMarcadores.size();
    }

    class ViewHolderMarcador extends RecyclerView.ViewHolder {

        TextView lblCompasInicio, lblNombreMarcador;
        ImageView imgTipo;

        Drawable resDrawableTipo;

        public ViewHolderMarcador(@NonNull View itemView, Drawable resDrawableTipo) {
            super(itemView);


            lblCompasInicio = itemView.findViewById(R.id.lblCompasInicio_CardMarcador);
            lblNombreMarcador = itemView.findViewById(R.id.lblNombreMarcador_cardMarcador);
            imgTipo = itemView.findViewById(R.id.imgTipo_cardMarcador);

            this.resDrawableTipo = resDrawableTipo;
        }

        public void setImgTipo() {
            imgTipo.setImageDrawable(resDrawableTipo);
        }


    }
}


