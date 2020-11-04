package com.chustle.lise.ui.mis_secuencias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;

import java.util.List;

public class AdapterListaSecuencias extends
        RecyclerView.Adapter<AdapterListaSecuencias.ViewHolderListaSecuencias> {


    private List<ListModelSecuencias> listModelListSecuencias;
    private SecuenciasAdapterListener listener;

    public AdapterListaSecuencias(List<ListModelSecuencias> listModelListSecuencias,
                                  SecuenciasAdapterListener listener) {
        this.listModelListSecuencias = listModelListSecuencias;
        this.listener = listener;
    }

    @Override
    public ViewHolderListaSecuencias onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_secuencia, parent,
                false);
        ViewHolderListaSecuencias viewHolder = new ViewHolderListaSecuencias(v, listener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolderListaSecuencias holder, int position) {
        String nombreSecuencia = listModelListSecuencias.get(position).getNombreSecuencia();
        String artistaSecuencia = listModelListSecuencias.get(position).getArtistaSecuencia();

        holder.nombreSecuencia.setText(nombreSecuencia);
        holder.artistaSecuencia.setText(artistaSecuencia);

    }

    @Override
    public int getItemCount() {
        return listModelListSecuencias.size();
    }


    public interface SecuenciasAdapterListener {
        void onClic(int position);

        void onLongClic(int position);
    }

    static class ViewHolderListaSecuencias extends RecyclerView.ViewHolder {
        private TextView nombreSecuencia;
        private TextView artistaSecuencia;


        public ViewHolderListaSecuencias(View v, final AdapterListaSecuencias.SecuenciasAdapterListener listener) {
            super(v);
            nombreSecuencia = (TextView) v.findViewById(R.id.lblNombreSecuencia);
            artistaSecuencia = (TextView) v.findViewById(R.id.lblArtistaSecuencia);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClic(getAdapterPosition());
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClic(getAdapterPosition());
                    return false;
                }
            });

        }


    }

}


