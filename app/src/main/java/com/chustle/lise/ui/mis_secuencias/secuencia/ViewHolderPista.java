package com.chustle.lise.ui.mis_secuencias.secuencia;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.files.models.Secuencia;

public abstract class ViewHolderPista extends RecyclerView.ViewHolder {
    public Secuencia.SecuenciaChangedListener listener;
    public boolean expandido;
    public ViewHolderPista(@NonNull View itemView) {
        super(itemView);

        //Alternate the expansion of the components-------------------------------------------------
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onChange();
                if (expandido)
                    contraer();
                else
                    expandir();

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClicPista(getAdapterPosition(), getItemViewType());
                return true;
            }
        });
    }


    public abstract void contraer();
    public abstract void expandir();


}
