package br.upf.ads.mercadoestoque.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Configuração do cliente que faz a ponte entre o app e o servidor na web
object RetrofitClient {
    // Endereço base da API (o IP 10.0.2.2 é usado para o emulador acessar o computador local)
    private const val BASE_URL = "http://10.0.2.2:3000/"

    // Cria a instância do serviço de forma "preguiçosa" (apenas quando for usada pela primeira vez)
    val instance: ProdutoService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Converte o JSON do servidor em objetos Kotlin
            .build()
            .create(ProdutoService::class.java)
    }
}