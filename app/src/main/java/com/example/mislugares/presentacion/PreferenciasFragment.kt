package com.example.mislugares.presentacion

import android.os.Bundle
import android.preference.PreferenceFragment
import com.example.mislugares.R

@Suppress("DEPRECATION")
class PreferenciasFragment : PreferenceFragment()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferencias)
    }
}