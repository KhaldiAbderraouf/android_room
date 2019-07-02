package com.example.databasex

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Interv(
    @PrimaryKey var num: Int,
    @ColumnInfo(name = "nom") var nom: String,
    @ColumnInfo(name = "interv") var interv: String,
    @ColumnInfo(name = "date") var date: Long
)