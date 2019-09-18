package com.example.mislugares.casos_uso

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.mislugares.RESULTADO_EDITAR
import com.example.mislugares.RESULTADO_GALERIA
import com.example.mislugares.VistaLugarActivity
import com.example.mislugares.datos.RepositorioLugares
import com.example.mislugares.modelos.GeoPunto
import com.example.mislugares.modelos.Lugar


class CasosUsoLugar(val actividad: Activity,
                    val lugares: RepositorioLugares
) {
    // OPERACIONES BÁSICAS
    fun mostrar(pos: Int) {
        val i = Intent(actividad, VistaLugarActivity::class.java)
        i.putExtra("pos", pos)
        actividad.startActivity(i)
    }

    fun borrar(id: Int) {
        lugares.borrar(id)
        actividad.finish()
    }

    // INTENCIONES
    fun compartir(lugar: Lugar) = actividad.startActivity(
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "$(lugar.nombre) - $(lugar.url)")
        })

    fun llamarTelefono(lugar: Lugar) = actividad.startActivity(
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + lugar.telefono))
    )

    fun verPgWeb(lugar: Lugar) = actividad.startActivity(
        Intent(Intent.ACTION_VIEW, Uri.parse(lugar.url))
    )

    fun verMapa(lugar: Lugar) {
        val lat = lugar.posicion.latitud
        val lon = lugar.posicion.longitud
        val uri = if (lugar.posicion != GeoPunto.SIN_POSICION)
            Uri.parse("geo:$lat,$lon")
        else Uri.parse("geo:0,0?q=" + lugar.direccion)
        actividad.startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    fun galeria(view: View) {
        val action = if (android.os.Build.VERSION.SDK_INT >= 19) { // API 19 - Kitkat
            Intent.ACTION_OPEN_DOCUMENT
        } else {
            Intent.ACTION_PICK
        }
        val i = Intent(action, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(i, RESULTADO_GALERIA)
    }


        fun onActivityResult(requestCode: Int, resultCode: Int,
                                      data: Intent?){

            if (requestCode == RESULTADO_EDITAR) {
            } else if (requestCode == RESULTADO_GALERIA) {
                if (resultCode == RESULT_OK) {
                    usoLugar.ponerFoto(pos, data?.dataString ?: "", foto)
                } else {
                    Toast.makeText(this, "Foto no cargada", Toast.LENGTH_LONG).show();
                }
            }
        }
    fun ponerFoto(pos: Int, uri: String?, imageView: ImageView) {
        val lugar = lugares.elemento(pos)
        lugar.foto = uri ?: ""
        visualizarFoto(lugar, imageView)
    }

    fun visualizarFoto(lugar: Lugar, imageView: ImageView) {
        if (!(lugar.foto == null || lugar.foto.isEmpty())) {
            imageView.setImageURI(Uri.parse(uri))
        } else {
            imageView.setImageBitmap(null)
        }
    }
}

