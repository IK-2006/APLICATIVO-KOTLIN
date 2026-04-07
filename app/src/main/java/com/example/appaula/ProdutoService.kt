package com.example.appaula

import com.example.appaula.domain.Produto
import retrofit2.Call
import retrofit2.http.*

interface ProdutoService {

    @GET("produtos")
    suspend fun listar(): List<Produto>

    @POST("produtos")
    suspend fun inserir(@Body produto: Produto): Produto
    fun getProduto()
}