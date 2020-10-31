package com.chustle.lise.ui.mis_secuencias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;

import java.util.List;

public class ListaSecuenciasAdapter extends RecyclerView.Adapter<ListaSecuenciasAdapter.ViewHolder> {


    private List<SecuenciaListModel> secuenciaListModelList;
    private SecuenciasAdapterListener listener;

    public ListaSecuenciasAdapter(List<SecuenciaListModel> secuenciaListModelList, SecuenciasAdapterListener listener) {
        this.secuenciaListModelList = secuenciaListModelList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element_secuencia, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, listener);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nombreSecuencia = secuenciaListModelList.get(position).getNombreSecuencia();
        String artistaSecuencia = secuenciaListModelList.get(position).getArtistaSecuencia();

        holder.nombreSecuencia.setText(nombreSecuencia);
        holder.artistaSecuencia.setText(artistaSecuencia);

    }

    @Override
    public int getItemCount() {
        return secuenciaListModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreSecuencia;
        private TextView artistaSecuencia;



        public ViewHolder(View v, final SecuenciasAdapterListener listener) {
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



    public interface SecuenciasAdapterListener{
        void onClic(int position);
        void onLongClic(int position);
    }
}