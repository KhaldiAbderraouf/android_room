package com.example.databasex

import androidx.room.*

@Dao
interface IntervDao {
    @Query("SELECT * FROM Interv")
    fun getAll(): List<Interv>

    @Query("SELECT * FROM Interv WHERE nom LIKE :nom")
    fun findByNom(nom: String): Interv

    @Insert
    fun insertAll(vararg todo: Interv)

    @Delete
    fun delete(todo: Interv)

    @Update
    fun update(vararg todos: Interv)
}