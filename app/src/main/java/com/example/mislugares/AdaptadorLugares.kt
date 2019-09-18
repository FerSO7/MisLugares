import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.mislugares.R
import com.example.mislugares.datos.RepositorioLugares
import com.example.mislugares.modelos.Lugar
import com.example.mislugares.modelos.TipoLugar
import kotlinx.android.synthetic.main.elemento_lista.view.*

class AdaptadorLugares(private val lugares: RepositorioLugares) :
    RecyclerView.Adapter<AdaptadorLugares.ViewHolder>() {

    lateinit var onClick: (View) -> Unit

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun personaliza(lugar: Lugar , onClick :(View)-> Unit) = with(itemView){
            nombre.text = lugar.nombre
            direccion.text = lugar.direccion
            foto.setImageResource(when (lugar.tipo) {
                TipoLugar.RESTAURANTE -> R.drawable.restaurante
                TipoLugar.BAR -> R.drawable.bar
                TipoLugar.COPAS -> R.drawable.copas
                TipoLugar.ESPECTACULO -> R.drawable.espectaculos
                TipoLugar.HOTEL -> R.drawable.hotel
                TipoLugar.COMPRAS -> R.drawable.compras
                TipoLugar.EDUCACION -> R.drawable.educacion
                TipoLugar.DEPORTE -> R.drawable.deporte
                TipoLugar.NATURALEZA -> R.drawable.naturaleza
                TipoLugar.GASOLINERA -> R.drawable.gasolinera
                TipoLugar.OTROS -> R.drawable.otros
            })
            foto.setScaleType(ImageView.ScaleType.FIT_END)
            valoracion.rating = lugar.valoracion
            setOnClickListener{ onClick(itemView)}
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.elemento_lista, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, posicion: Int) {
        val lugar = lugares.elemento(posicion)
        holder.personaliza(lugar,onClick)
    }

    override fun getItemCount() = lugares.tamanyo()
}