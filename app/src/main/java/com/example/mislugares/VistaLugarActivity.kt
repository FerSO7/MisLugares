package com.example.mislugares

import android.app.Activity
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.mislugares.casos_uso.CasosUsoLugar
import com.example.mislugares.modelos.Lugar

import kotlinx.android.synthetic.main.vista_lugar.*

import java.text.DateFormat
import java.util.*

val RESULTADO_EDITAR = 1
val RESULTADO_GALERIA = 2
val RESULTADO_FOTO = 3

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.vista_lugar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.accion_compartir ->{
                usoLugar.compartir(lugar)
            return true
        }

            R.id.accion_llegar ->
            {
                usoLugar.verMapa(lugar)
                return true
            }
            R.id.accion_editar -> return true
            R.id.accion_borrar -> {
                usoLugar.borrar(pos)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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
        usoLugar.visualizarFoto(lugar, foto);
    }
    fun verMapa(view: View) = usoLugar.verMapa(lugar)

    fun llamarTelefono(view: View) = usoLugar.llamarTelefono(lugar)

    fun verPgWeb(view: View) = usoLugar.verPgWeb(lugar)

    fun ponerDeGaleria(view: View)= usoLugar.ponerDeGaleria(RESULTADO_GALERIA)
}