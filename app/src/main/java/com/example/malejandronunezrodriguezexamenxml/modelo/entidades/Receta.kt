package com.example.malejandronunezrodriguezexamenxml.modelo.entidades

import org.simpleframework.xml.*

@Root(name = "receta")
data class Receta constructor(
    @field: ElementList(inline = true, entry = "ingrediente")
    var ingredientes: List<Ingrediente> = mutableListOf(),
    @field:Attribute(name = "nombre")
    var nombre: String = "",
)


@Root(name = "ingrediente")
data class Ingrediente constructor(
    @field:Element(name = "alimento")
    var alimento: Alimento = Alimento(),

    @field:Attribute(name = "nombre")
    var nombre: String = "",

    @field:Element(name = "cantidad")
    var cantidad: Float = 0.0f,
)

@Element(name = "Alimento")
data class Alimento constructor(

    @field:Element(name = "proteinas")
    var proteinas: Proteina = Proteina(),

    @field:Element(name = "grasas")
    var grasas: Grasa = Grasa(),

    @field:Element(name = "hidratos")
    var hidratos: Hidrato = Hidrato()
)

@Element(name = "proteina")
data class Proteina constructor(
    @field:Attribute(name = "cantidad100g")
    var cantidad100: Int = 0
)

@Element(name = "grasas")
data class Grasa constructor(
    @field:Attribute(name = "cantidad100g")
    var cantidad100: Int = 0
)

@Element(name = "hidratos")
data class Hidrato constructor(
    @field:Attribute(name = "cantidad100g")
    var cantidad100: Int = 0
)




