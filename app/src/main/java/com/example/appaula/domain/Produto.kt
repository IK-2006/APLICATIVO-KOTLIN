package br.upf.ads.mercadoestoque.domain

import java.io.Serializable

data class Produto(
    var id: Int?,
    var nome: String,
    var quantidade: Int,
    var preco: Double
) : Serializable