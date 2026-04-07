package com.example.appaula.domain

data class Produto(
    val id: Int = 0,
    val nome: String,
    val categoria: String,
    val validade: String,
    val codigo_barras: String,
    val quantidade: Int,
    val preco: Double
) {
    override fun toString(): String {
        return "$nome | Qtd: $quantidade | R$ $preco"
    }
}