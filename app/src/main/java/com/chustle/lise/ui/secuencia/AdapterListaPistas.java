package com.chustle.lise.ui.secuencia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;
import com.chustle.lise.animator.Animator;

import java.util.List;

public class AdapterListaPistas extends RecyclerView.Adapter<AdapterListaPistas.ViewHolderPistas> {

    private List<ListModelPista> listaPistas;


    public AdapterListaPistas(List<ListModelPista> listaPistas) {
        this.listaPistas = listaPistas;
    }

    @NonNull
    @Override
    public ViewHolderPistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pista_marcador, parent,
                false);
        return new ViewHolderPistas(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPistas holder, int position) {

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

