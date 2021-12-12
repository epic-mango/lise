package com.chustle.lise.ui.mis_secuencias.secuencia.anotaciones;

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


public class AdapterListaAnotaciones extends RecyclerView.Adapter<AdapterListaAnotaciones.ViewHolderAnotacion> {

    private static final int TIPO_TIEMPO = 1;
    private static final int TIPO_COMPAS = 2;
    ArrayList<Marcador> listaAnotaciones;
    AnotacionListener listener;

    public AdapterListaAnotaciones(ArrayList<Marcador> listaAnotaciones, AnotacionListener listener) {
        this.listaAnotaciones = listaAnotaciones;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {

        return listaAnotaciones.get(position).isTiempo() ? TIPO_TIEMPO : TIPO_COMPAS;
    }

    @NonNull
    @Override
    public ViewHolderAnotacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_anotacion, parent, false);


        //Retorna un View con la imagen correspondiente al TIPO_TIEMPO o TIPO_COMPAS
        return new ViewHolderAnotacion(root, viewType == TIPO_TIEMPO ?
                root.getResources().getDrawable(R.drawable.ic_time_black) :
                root.getResources().getDrawable(R.drawable.ic_compas), listener);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderAnotacion holder, int position) {
        if (listaAnotaciones.get(position).isTiempo()) {


            int inicio = listaAnotaciones.get(position).getInicio();
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
            holder.lblCompasInicio.setText(listaAnotaciones.get(position).getInicio() + "");
        holder.lblAnotacion.setText(listaAnotaciones.get(position).getNombre());

        holder.setImgTipo();


    }

    @Override
    public int getItemCount() {
        return listaAnotaciones.size();
    }

    public interface AnotacionListener {
        public void onClick(int position);

        public void onLongClick(int position);
    }

    class ViewHolderAnotacion extends RecyclerView.ViewHolder {


        TextView lblCompasInicio, lblAnotacion;
        ImageView imgTipo;

        Drawable resDrawableTipo;

        public ViewHolderAnotacion(@NonNull View itemView, Drawable resDrawableTipo, final AnotacionListener listener) {
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
            lblCompasInicio = itemView.findViewById(R.id.lblCompasInicio_CardAnotacion);
            lblAnotacion = itemView.findViewById(R.id.lblNombreMarcador_cardAnotacion);
            imgTipo = itemView.findViewById(R.id.imgTipo_cardAnotacion);

            this.resDrawableTipo = resDrawableTipo;
        }

        public void setImgTipo() {
            imgTipo.setImageDrawable(resDrawableTipo);
        }


    }
}


