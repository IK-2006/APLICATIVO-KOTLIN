package com.example.appaula.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// Interface que define os comandos de banco de dados para a tabela de usuários
@Dao
interface UserDao {

    // Busca todos os usuários cadastrados
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    // Tenta encontrar um usuário que coincida com o email e a senha informados (usado no login)
    @Query("SELECT * FROM user WHERE email = :email AND senha = :senha LIMIT 1")
    fun getUser(email: String, senha: String): User?

    // Busca um usuário pelo email (útil para verificar se já existe um cadastro)
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    fun getByEmail(email: String): User?

    // Insere um novo usuário no banco
    @Insert
    fun insertOne(user: User)

    // Atualiza os dados de um usuário existente
    @Update
    fun updateOne(user: User)

    // Remove um usuário do banco de dados
    @Delete
    fun delete(user: User)
}