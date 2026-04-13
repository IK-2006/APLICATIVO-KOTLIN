package br.upf.ads.mercadoestoque.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "produto")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nome: String,
    var quantidade: Int,
    var preco: Double
) : Serializable {
    override fun toString(): String {
        return "$nome - Qtd: $quantidade - R$ $preco"
    }
}