package com.example.wb_7_3.data.repository

import com.example.wb_7_3.data.network.CatApiHelper
import com.example.wb_7_3.domain.model.CatModelDomain
import com.example.wb_7_3.domain.repository.GetCatRepository
import javax.inject.Inject

class GetCatRepositoryImpl @Inject constructor(private val apiHelper: CatApiHelper) :
    GetCatRepository {

    override suspend fun getCat(): CatModelDomain {
        return apiHelper.getCat()
    }
}