package com.example.appaula.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "nome") var nome: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "senha") var senha: String
)
{
    override fun toString(): String {
        return nome
    }
}
