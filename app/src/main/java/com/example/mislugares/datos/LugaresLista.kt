package com.example.mislugares.datos

import com.example.mislugares.modelos.GeoPunto
import com.example.mislugares.modelos.Lugar
import com.example.mislugares.modelos.TipoLugar

class LugaresLista : RepositorioLugares {
    val listaLugares= mutableListOf<Lugar>()
    init {
        añadeEjempo()
    }


    override fun elemento(id: Int): Lugar {
        return listaLugares[id]
    }

    override fun añade(lugar: Lugar) {
        listaLugares.add(lugar)
    }

    override fun nuevo(): Int {
    //  val lugar = Lugar("Nuevo lugar")
      // listaLugares.add(lugar)
        return listaLugares.size - 1
    }

    override fun borrar(id: Int) {
        listaLugares.removeAt(id)
    }

    override fun tamaño(): Int {
        return listaLugares.size
    }

    override fun actualiza(id: Int, lugar: Lugar) {
        listaLugares[id] = lugar
    }
    fun añadeEjempo(){
        val lugar = Lugar("Escuela pete", TipoLugar.EDUCACION,"agusto 50", GeoPunto.SIN_POSICION, null,442245,
            "www.pete.com","Pesima Educacion",System.currentTimeMillis(),1f)
        listaLugares.add(lugar)

    }
}