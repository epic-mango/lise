package com.chustle.lise.ui.mis_secuencias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chustle.lise.R;

public class DialogFragmentPropiedadesSecuencia extends DialogFragment {

    PropiedadesSecuenciaListener listener;
    SecuenciaListModel secuencia;

    EditText txtNombre, txtArtista;

    public DialogFragmentPropiedadesSecuencia(PropiedadesSecuenciaListener propiedadesSecuenciaListener, SecuenciaListModel secuencia) {
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

        if (secuencia != null) {
            txtNombre.setText(secuencia.getNombreSecuencia());
            txtArtista.setText(secuencia.getArtistaSecuencia());
        }

        builder.setView(root);

        builder.setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.aceptar(txtNombre.getText().toString(), txtArtista.getText().toString());
            }
        });


        return builder.create();
    }

    public interface PropiedadesSecuenciaListener {
        public void aceptar(String nombre, String artista);
    }
}
