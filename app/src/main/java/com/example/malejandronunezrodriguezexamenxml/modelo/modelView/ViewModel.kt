package com.example.malejandronunezrodriguezexamenxml.modelo.modelView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.malejandronunezrodriguezexamenxml.modelo.daos.DaoXml
import com.example.malejandronunezrodriguezexamenxml.modelo.entidades.Ingrediente


class ViewModel(application: Application) : AndroidViewModel(application) {

    var daoXml: DaoXml = DaoXml(application)

    fun addIngrediente(ingredeinte: Ingrediente) {
        daoXml.addIngrediente(ingredeinte)
    }

    fun procesarFicheroXml(): MutableList<Ingrediente> {
        return daoXml.procesarFicheroXml()
    }

    fun procesarArchivoXMLSAX(): MutableList<Ingrediente> {
        return daoXml.procesarArchivoXMLSAX()
    }

    fun procesarFicheroXmlInterno(): MutableList<Ingrediente> {
        return daoXml.procesarFicheroXmlInterno()
    }

    fun copiarArchivo() {
        daoXml.copiarArchivoDesdeAsset()
    }
}