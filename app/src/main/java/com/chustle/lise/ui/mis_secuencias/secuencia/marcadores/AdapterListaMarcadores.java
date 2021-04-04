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
    private static final int TIPO_COMPAS = 2;
    ArrayList<Marcador> listaMarcadores;
    MarcadorListener listener;

    public AdapterListaMarcadores(ArrayList<Marcador> listaMarcadores, MarcadorListener listener) {
        this.listaMarcadores = listaMarcadores;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {

        return listaMarcadores.get(position).isTiempo() ? TIPO_TIEMPO : TIPO_COMPAS;
    }

    @NonNull
    @Override
    public ViewHolderMarcador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_marcador, parent, false);


        //Retorna un View con la imagen correspondiente al TIPO_TIEMPO o TIPO_COMPAS
        return new ViewHolderMarcador(root, viewType == TIPO_TIEMPO ?
                root.getResources().getDrawable(R.drawable.ic_time_black) :
                root.getResources().getDrawable(R.drawable.ic_compas), listener);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderMarcador holder, int position) {
        if (listaMarcadores.get(position).isTiempo()) {


            int inicio = listaMarcadores.get(position).getInicio();
            int horas = inicio / 3600000;
            int minutos = (inicio % 3600000) / 60000;
            int segundos = (inicio % 60000) / 1000;

            String strInicio;

            if (horas > 0)
                strInicio = String.format("%02d", horas) + ":" + String.format("%02d", minutos) + ":" + String.format("%02d", segundos) + ":" + String.format("%04d", inicio % 1000);
            else if (minutos > 0)
                strInicio = String.format("%02d", minutos) + ":" + String.format("%02d", segundos) + ":" + String.format("%04d", inicio % 1000);
            else if (segundos > 0)
                strInicio = String.format("%02d", segundos) + ":" + String.format("%04d", inicio % 1000);
            else
                strInicio = String.format("%04d", inicio % 1000);

            holder.lblCompasInicio.setText(strInicio);
        } else
            holder.lblCompasInicio.setText(listaMarcadores.get(position).getInicio() + "");
        holder.lblNombreMarcador.setText(listaMarcadores.get(position).getNombre());

        holder.setImgTipo();


    }

    @Override
    public int getItemCount() {
        return listaMarcadores.size();
    }

    public interface MarcadorListener {
        public void onClick(int position);

        public void onLongClick(int position);
    }

    class ViewHolderMarcador extends RecyclerView.ViewHolder {


        TextView lblCompasInicio, lblNombreMarcador;
        ImageView imgTipo;

        Drawable resDrawableTipo;

        public ViewHolderMarcador(@NonNull View itemView, Drawable resDrawableTipo, final MarcadorListener listener) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(getAdapterPosition());
                    return true;
                }
            });
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


