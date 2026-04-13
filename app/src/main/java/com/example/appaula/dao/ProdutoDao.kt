package com.example.appaula.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.upf.ads.mercadoestoque.domain.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM produto ORDER BY nome")
    fun getAll(): List<Produto>

    @Query("SELECT * FROM produto WHERE id = :idProduto")
    fun getProduto(idProduto: Int): Produto?

    @Insert
    fun insertOne(produto: Produto)

    @Update
    fun updateOne(produto: Produto)

    @Delete
    fun delete(produto: Produto)
}