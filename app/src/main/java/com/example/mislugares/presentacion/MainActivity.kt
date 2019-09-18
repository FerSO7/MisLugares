package com.example.mislugares.presentacion

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.preference.PreferenceManager
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.EditText
import com.example.mislugares.Aplicacion
import com.example.mislugares.R
import com.example.mislugares.casos_uso.CasosUsoLugar
import java.lang.Integer.parseInt


class MainActivity : AppCompatActivity() {

    val lugares by lazy { (application as Aplicacion).lugares }
    val adaptador by lazy { (application as Aplicacion).adaptador }
    val usoLugar by lazy { CasosUsoLugar(this, lugares) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adaptador
        }
        adaptador.onClick  =  {
            val pos = recyclerView.getChildAdapterPosition(it)
            usoLugar.mostrar(pos)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, PreferenciasActivity:: class.java)
                startActivity(intent)
                true
            }
            R.id.acercaDe -> {
               val intent = Intent(this, AcercaDeActivity:: class.java)
                startActivity(intent)
                true
            }
            R.id.menu_buscar -> {
                //lanzarVistaLugar()
                true;
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
   /* fun lanzarAcercaDe(view: View){
        val i = Intent(this, AcercaDeActivity::class.java)
        startActivity(i)
    }
    fun lanzarPreferencias(view: View){
        val i = Intent(this, PreferenciasActivity:: class.java)
        startActivity(i)
    }
    fun salir(view: View)
    {
        finish()
    }
    fun mostrarPreferencias(view:View) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val s = ("notificaciones: " + pref.getBoolean("notificaciones", true)
                + ", máximo a listar: " + pref.getString("maximo", "?"))
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
    fun lanzarVistaLugar(view: View? = null) {
        val entrada = EditText(this)
        entrada.setText("0")
        AlertDialog.Builder(this)
            .setTitle("Selección de lugar")
            .setMessage("indica su id:")
            .setView(entrada)
            .setPositiveButton("Ok")  { dialog, whichButton ->
                val id = parseInt(entrada.text.toString())
                usoLugar.mostrar(id);
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }*/
}
