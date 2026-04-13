package br.upf.ads.mercadoestoque.api

import br.upf.ads.mercadoestoque.domain.Produto
import retrofit2.Response
import retrofit2.http.*

// Interface que define os pontos de acesso (endpoints) da API na internet
interface ProdutoService {

    // Busca a lista completa de produtos no servidor
    @GET("produtos")
    suspend fun listarProdutos(): List<Produto>

    // Envia um novo produto para ser salvo no servidor
    @POST("produtos")
    suspend fun criarProduto(@Body produto: Produto): Produto

    // Atualiza os dados de um produto existente no servidor usando o ID
    @PUT("produtos/{id}")
    suspend fun atualizarProduto(@Path("id") id: Int, @Body produto: Produto): Produto

    // Remove um produto do servidor através do seu ID
    @DELETE("produtos/{id}")
    suspend fun deletarProduto(@Path("id") id: Int): Response<Unit>
}