package com.example.malejandronunezrodriguezexamenxml.modelo.interfaces

import com.example.malejandronunezrodriguezexamenxml.modelo.entidades.Ingrediente

interface InterfaceDaoReceta {

    fun addIngrediente(ingrediente: Ingrediente)

    fun procesarFicheroXml(): MutableList<Ingrediente>

    fun procesarArchivoXMLSAX(): MutableList<Ingrediente>

    fun procesarFicheroXmlInterno(): MutableList<Ingrediente>

    fun copiarArchivoDesdeAsset()
}