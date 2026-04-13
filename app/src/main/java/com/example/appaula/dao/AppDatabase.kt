package com.example.appaula.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.upf.ads.mercadoestoque.domain.Produto

@Database(entities = [User::class, Produto::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun produtoDao(): ProdutoDao
}