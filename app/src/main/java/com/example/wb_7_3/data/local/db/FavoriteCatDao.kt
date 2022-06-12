package com.example.wb_7_3.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wb_7_3.data.local.model.FavoriteCatModelLocal

@Dao
interface FavoriteCatDao {

    @Query("SELECT * FROM cats ORDER BY created_at")
    fun getFavoriteCats(): List<FavoriteCatModelLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteCat(favoriteCat: FavoriteCatModelLocal)
}