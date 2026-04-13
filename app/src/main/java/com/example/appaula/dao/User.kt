package com.example.appaula.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

// Classe que define a tabela de usuários no banco de dados
@Entity(tableName = "user")
data class User(
    // O ID é gerado automaticamente pelo banco
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val nome: String,
    val email: String,
    val senha: String
) {
    // Facilita a exibição do nome do usuário quando necessário
    override fun toString(): String {
        return nome
    }
}