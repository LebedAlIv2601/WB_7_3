package com.example.wb_7_3.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wb_7_3.data.local.model.FavoriteCatModelLocal

@Database(entities = [FavoriteCatModelLocal::class], version = 1)
abstract class FavoriteCatsDB : RoomDatabase(){

    abstract fun favoriteCatDao(): FavoriteCatDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteCatsDB? = null

        fun getInstance(context: Context): FavoriteCatsDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        FavoriteCatsDB::class.java, "products_db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}