package com.chustle.lise.ui.entrada_datos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chustle.lise.R;

import java.util.ArrayList;

public class DialogFragmentEntradaDatos extends DialogFragment {

    EntradaDatosListener listener;
    ArrayList<EntradaDato> listaDatos;
    String titulo;

    public DialogFragmentEntradaDatos(EntradaDatosListener entradaDatosListener, String titulo,
                                      ArrayList<EntradaDato> listaDatos) {
        listener = entradaDatosListener;
        this.titulo = titulo;
        this.listaDatos = listaDatos;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(titulo);

        View root = requireActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_entrada_datos, null);

        Button btnAceptar = root.findViewById(R.id.btnAceptar_dFEntradaDatos);
        Button btnCancelar = root.findViewById(R.id.btnCancelar_dFEntradaDatos);
        RecyclerView recyclerViewEntradasDatos = root.findViewById(R.id.recyclerViewEntradaDatos_DialogFragmentEntradaDatos);

        recyclerViewEntradasDatos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewEntradasDatos.setAdapter(new AdapterEntradaDatos(listaDatos));

        builder.setCancelable(false);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean completo = false;

                //Verify that all the fields are complete
                for (EntradaDato entradaDato : listaDatos) {
                    if (entradaDato.dato.equals("")) {
                        completo = false;
                        Toast.makeText(getContext(), getString(R.string.campos_vacios), Toast.LENGTH_SHORT).show();
                        break;
                    } else
                        completo = true;

                }

                if (completo) {
                    listener.aceptar();
                    dismiss();
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(root);

        return builder.create();
    }


    public interface EntradaDatosListener {
        void aceptar();
    }
}
