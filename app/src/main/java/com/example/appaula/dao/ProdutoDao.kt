package com.example.appaula.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.upf.ads.mercadoestoque.domain.Produto

// Interface que define como o código acessa os dados da tabela de produtos
@Dao
interface ProdutoDao {

    // Retorna todos os produtos organizados por ordem alfabética de nome
    @Query("SELECT * FROM produto ORDER BY nome")
    fun getAll(): List<Produto>

    // Tenta encontrar um produto específico pelo seu ID
    @Query("SELECT * FROM produto WHERE id = :idProduto")
    fun getProduto(idProduto: Int): Produto?

    // Grava um novo produto no banco de dados
    @Insert
    fun insertOne(produto: Produto)

    // Atualiza as informações de um produto que já existe
    @Update
    fun updateOne(produto: Produto)

    // Apaga o registro de um produto do sistema
    @Delete
    fun delete(produto: Produto)
}