package com.example.appaula.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.upf.ads.mercadoestoque.domain.Produto

// Configuração central do banco de dados Room
// Aqui definimos quais tabelas existem (User e Produto) e a versão do banco
@Database(entities = [User::class, Produto::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    
    // Funções para acessar os objetos de acesso a dados (DAOs)
    abstract fun userDao(): UserDao
    abstract fun produtoDao(): ProdutoDao
}