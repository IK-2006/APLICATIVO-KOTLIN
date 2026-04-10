package com.example.appaula.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>
    @Query("SELECT * FROM user WHERE email = :email AND senha = :senha")
    fun getUser(email: String, senha:String): User
    @Query("SELECT max(uid) FROM user")
    fun getMaiorId(): Int
    @Insert
    fun insertOne(user: User)
    @Update
    fun updateOne(user: User)
    @Delete
    fun delete(user: User)
}