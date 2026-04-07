package com.example.appaula.api

import com.example.appaula.ProdutoService
import com.example.appaula.domain.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>
    @POST("users")
    suspend fun createUser(@Body user: User): User
    @PUT("users")
    suspend fun updateUser(@Body user: User): User
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
    @GET("users/{email}/{senha}")
    suspend fun getUser(email: String, senha:String): User
    @GET("users/getMaiorId")
    suspend fun getMaiorId(): Int
    fun create(java: Class<ProdutoService>)
}