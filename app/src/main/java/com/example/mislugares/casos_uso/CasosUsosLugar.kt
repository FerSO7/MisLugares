package com.example.mislugares.casos_uso

import android.app.Activity
import android.content.Intent
import com.example.mislugares.VistaLugarActivity
import com.example.mislugares.datos.RepositorioLugares





class CasosUsoLugar(val actividad: Activity,
                    val lugares: RepositorioLugares
) {
    // OPERACIONES BÁSICAS
    fun mostrar(pos: Int) {
        val i = Intent(actividad, VistaLugarActivity::class.java)
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }
}