package com.example.malejandronunezrodriguezexamenxml.modelo.handlers

import com.example.malejandronunezrodriguezexamenxml.modelo.entidades.*
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class RecetaHandlerXml : DefaultHandler() {

    private val cadena = StringBuilder()
    private var ingrediente: Ingrediente? = null
    private var alimento: Alimento? = null
    private var receta: Receta? = null
    private var proteina: Proteina? = null
    private var grasa: Grasa? = null
    private var hidratos: Hidrato? = null
    private var cantidadKcal: Int = 0

    var listaIngredientes: MutableList<Ingrediente> = mutableListOf()


    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        listaIngredientes = mutableListOf()
        println("startDocument")
    }

    @Throws(SAXException::class)
    override fun startElement(
        uri: String,
        nombreLocal: String,
        nombre: String,
        attributes: Attributes
    ) {
        cadena.setLength(0)

        when (nombre) {
            "proteinas" -> {
                proteina = Proteina()
                proteina!!.cantidad100 = attributes.getValue("cantidad100g").toInt()
            }
            "grasas" -> {
                grasa = Grasa()
                grasa!!.cantidad100 = attributes.getValue("cantidad100g").toInt()
            }
            "hidratos" -> {
                hidratos = Hidrato()
                hidratos!!.cantidad100 = attributes.getValue("cantidad100g").toInt()
            }
            "alimento" -> {
                alimento = Alimento()
            }
            "ingrediente" -> {
                ingrediente = Ingrediente()
                ingrediente!!.nombre = attributes.getValue("nombre")
            }
            "receta" -> {
                receta = Receta()
                receta!!.nombre = attributes.getValue("nombre")
            }
        }
        println("startElement: $nombre")
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        cadena.append(ch, start, length)
        println("dato final: $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, nombreLocal: String, nombre: String) {
        when (nombre) {
            "cantidad" -> {
                ingrediente!!.cantidad = cadena.toString().trim().toFloat()
                //cantidadKcal = (ingrediente!!.alimento.proteinas.cantidad100 * 4) + (ingrediente!!.alimento.hidratos.cantidad100 * 4) + (ingrediente!!.alimento.grasas.cantidad100 * 9) * ingrediente!!.cantidad / 100
            }
            "receta" -> listaIngredientes.add(ingrediente!!)
        }
        println("endElement: $nombreLocal $nombre")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        println("endDocument")
    }
}