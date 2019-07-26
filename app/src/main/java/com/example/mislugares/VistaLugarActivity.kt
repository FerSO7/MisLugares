package com.example.mislugares

import android.app.Activity
import android.os.Bundle
import com.example.mislugares.casos_uso.CasosUsoLugar
import com.example.mislugares.modelos.Lugar
import kotlinx.android.synthetic.main.vista_lugar.*
import java.text.DateFormat
import java.util.*

class VistaLugarActivity : Activity(){
val lugares by lazy { (application as Aplicacion).lugares }
val usoLugar by lazy { CasosUsoLugar(this, lugares) }
var pos = 0
lateinit var lugar: Lugar

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.vista_lugar)
    pos = intent.extras?.getInt("pos", 0) ?: 0
    lugar = lugares.elemento(pos)
    actualizaVistas()
}

fun actualizaVistas(){
    nombre.text = lugar.nombre
    logo_tipo.setImageResource(lugar.tipo.recurso)
    tipo.text = lugar.tipo.texto
    direccion_lugar.text = lugar.direccion
    telefono.text = Integer.toString(lugar.telefono)
    url.text = lugar.url
    comentario_lugar.text = lugar.comentarios
    fecha.text = DateFormat.getDateInstance().format(Date(lugar.fecha))
    hora.text = DateFormat.getTimeInstance().format(Date(lugar.fecha))
    valoracion.rating = lugar.valoracion
    valoracion.setOnRatingBarChangeListener {
            ratingBar, valor, fromUser -> lugar.valoracion = valor
    }
}
}