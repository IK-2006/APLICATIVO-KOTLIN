package br.upf.ads.mercadoestoque.api

import br.upf.ads.mercadoestoque.domain.Produto
import retrofit2.Response
import retrofit2.http.*

interface ProdutoService {

    @GET("produtos")
    suspend fun listarProdutos(): List<Produto>

    @POST("produtos")
    suspend fun criarProduto(@Body produto: Produto): Produto

    @PUT("produtos/{id}")
    suspend fun atualizarProduto(@Path("id") id: Int, @Body produto: Produto): Produto

    @DELETE("produtos/{id}")
    suspend fun deletarProduto(@Path("id") id: Int): Response<Unit>
}