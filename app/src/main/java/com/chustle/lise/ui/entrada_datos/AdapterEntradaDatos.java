package com.chustle.lise.ui.entrada_datos;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;

import java.util.ArrayList;

public class AdapterEntradaDatos extends RecyclerView.Adapter<AdapterEntradaDatos.EntradaDatosViewHolder> {

    private ArrayList<EntradaDato> listaDatos;

    public AdapterEntradaDatos(ArrayList<EntradaDato> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public EntradaDatosViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_entrada_dato, parent, false);

        return new EntradaDatosViewHolder(root, new EntradaDatoListener() {
            //When the EditText changed text event is called, the ArrayList object is updated. Then
            //the user clicks "Accept" and that information is used

            @Override
            public void setEntradaDato(int position, String dato) {
                listaDatos.get(position).dato = dato;
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull final EntradaDatosViewHolder holder, final int position) {
        holder.lblNombreDato.setText(listaDatos.get(position).campo);
        holder.txtEntradaDato.setHint(listaDatos.get(position).ejemplo);
        holder.txtEntradaDato.setText(listaDatos.get(position).dato);

        switch (listaDatos.get(position).getTipo()) {
            case EntradaDato.TIPO_NUMERO:
                holder.txtEntradaDato.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case EntradaDato.TIPO_STRING:
                holder.txtEntradaDato.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
        }



        //Listen when text is changed and update the ArrayList
        holder.txtEntradaDato.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                holder.listener.setEntradaDato(position, holder.txtEntradaDato.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    interface EntradaDatoListener {
        void setEntradaDato(int position, String dato);
    }

    class EntradaDatosViewHolder extends RecyclerView.ViewHolder {
        TextView lblNombreDato;
        EditText txtEntradaDato;
        EntradaDatoListener listener;

        public EntradaDatosViewHolder(@NonNull View itemView, final EntradaDatoListener listener) {
            super(itemView);
            this.listener = listener;
            lblNombreDato = itemView.findViewById(R.id.lblCompasInicio_DialogFragmentAgregarMarcador);
            txtEntradaDato = itemView.findViewById(R.id.txtEntradaDato_CardEntradaDato);


        }
    }

}

