package com.example.appaula.domaindata

data class Produto(
    val id: Int = 0,
    val nome: String,
    val categoria: String,
    val validade: String,
    val codigo_barras: String, // Se na sua API for camelCase, mude para codigoBarras
    val quantidade: Int,
    val preco: Double
) {
    override fun toString(): String {
        return "$nome - Qtd: $quantidade - R$ %.2f".format(preco)
    }
}