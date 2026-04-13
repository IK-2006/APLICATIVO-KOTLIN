package br.upf.ads.mercadoestoque.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Classe que representa a tabela de produtos no banco de dados
// Implementa Serializable para que possamos passar o objeto entre telas (Activities)
@Entity(tableName = "produto")
data class Produto(
    // ID único gerado automaticamente pelo banco para identificar cada produto
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nome: String,
    var quantidade: Int,
    var preco: Double
) : Serializable {
    
    // Define como o produto será exibido em formato de texto (ex: em Logs ou Listas)
    override fun toString(): String {
        return "$nome - Qtd: $quantidade - R$ $preco"
    }
}