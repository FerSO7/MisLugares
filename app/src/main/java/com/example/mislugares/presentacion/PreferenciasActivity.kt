package com.example.mislugares.presentacion

import android.app.Activity
import android.os.Bundle

class PreferenciasActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // reemplazamos el contenido de la actividad por un fragment
        fragmentManager.beginTransaction().replace(android.R.id.content,
            PreferenciasFragment()
        ).commit()

    }
}