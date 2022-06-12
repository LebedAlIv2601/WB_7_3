package com.example.wb_7_3.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class FavoriteCatModelLocal(

    @PrimaryKey
    val id: String,

    val url: String,

    @ColumnInfo(name = "created_at")
    val createdAt: String
)
