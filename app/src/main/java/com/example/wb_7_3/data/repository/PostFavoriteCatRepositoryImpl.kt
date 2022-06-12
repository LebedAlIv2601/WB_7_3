package com.example.wb_7_3.data.repository

import com.example.wb_7_3.data.local.db.FavoriteCatDao
import com.example.wb_7_3.data.network.CatApiHelper
import com.example.wb_7_3.data.network.Environment.IS_DATA_CHANGED
import com.example.wb_7_3.data.toLocal
import com.example.wb_7_3.domain.model.CatModelDomain
import com.example.wb_7_3.domain.repository.PostFavoriteCatRepository
import javax.inject.Inject

class PostFavoriteCatRepositoryImpl @Inject constructor(
    private val apiHelper: CatApiHelper,
    private val dao: FavoriteCatDao
) : PostFavoriteCatRepository {

    override suspend fun postCat(cat: CatModelDomain): Boolean {

        IS_DATA_CHANGED = true

        return apiHelper.postCat(cat)
    }
}