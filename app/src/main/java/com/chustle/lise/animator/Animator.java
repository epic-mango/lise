package com.chustle.lise.animator;

import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.Toast;


public abstract class Animator {

    public static void reducir(final View contenedor) {
        contenedor.animate()
                .setDuration(1000000000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(android.animation.Animator animation) {
                        Toast.makeText(contenedor.getContext(), "Animación", Toast.LENGTH_SHORT).show();
                    }
                })
                .rotation(20f);
    }

}
