package com.chustle.lise.ui.secuencia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chustle.lise.R;
import com.chustle.lise.ui.mis_secuencias.ListModelSecuencias;

public class DialogFragmentPropiedadesSecuencia extends DialogFragment {

    PropiedadesSecuenciaListener listener;
    ListModelSecuencias secuencia;

    EditText txtNombre, txtArtista;
    Button btnAceptar, btnCancelar;

    public DialogFragmentPropiedadesSecuencia(PropiedadesSecuenciaListener propiedadesSecuenciaListener, ListModelSecuencias secuencia) {
        listener = propiedadesSecuenciaListener;
        this.secuencia = secuencia;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getResources().getString(R.string.propiedades_secuencia));

        View root = requireActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_propiedades_secuencia, null);

        txtNombre = root.findViewById(R.id.txtNombreSecuencia);
        txtArtista = root.findViewById(R.id.txtArtistaSecuencia);

        btnAceptar = root.findViewById(R.id.btnAceptar_dFPropiedadesSecuencia);
        btnCancelar = root.findViewById(R.id.btnCancelar_dFPropiedadesSecuencia);

        if (secuencia != null) {
            txtNombre.setText(secuencia.getNombreSecuencia());
            txtArtista.setText(secuencia.getArtistaSecuencia());
        }

        builder.setCancelable(false);

        builder.setView(root);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().equals("") && !txtArtista.getText().toString().equals("")) {
                    listener.aceptar(txtNombre.getText().toString(), txtArtista.getText().toString());
                    dismiss();
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.campos_vacios), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return builder.create();
    }


    public interface PropiedadesSecuenciaListener {
        void aceptar(String nombre, String artista);
    }
}
