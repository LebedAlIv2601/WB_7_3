package com.example.wb_7_3.data.repository

import com.example.wb_7_3.data.local.db.FavoriteCatDao
import com.example.wb_7_3.data.network.CatApiHelper
import com.example.wb_7_3.data.network.Environment.IS_DATA_CHANGED
import com.example.wb_7_3.data.toCatModelDomain
import com.example.wb_7_3.data.toDomain
import com.example.wb_7_3.data.toLocal
import com.example.wb_7_3.domain.model.CatModelDomain
import com.example.wb_7_3.domain.repository.GetFavoriteCatsRepository
import javax.inject.Inject

class GetFavoriteCatsRepositoryImpl @Inject constructor(
    private val apiHelper: CatApiHelper,
    private val dao: FavoriteCatDao):
    GetFavoriteCatsRepository{

    override suspend fun getFavoriteCats(): List<CatModelDomain> {

        val favoriteCats = dao.getFavoriteCats().map { it.toDomain() }

        return if(IS_DATA_CHANGED){
            val catsForDb = apiHelper.getFavoriteCats().map { it.toLocal() }

            for(cat in catsForDb){
                dao.insertFavoriteCat(cat)
            }

            IS_DATA_CHANGED = false

            dao.getFavoriteCats().map { it.toDomain() }
        } else {
            favoriteCats
        }

    }

}