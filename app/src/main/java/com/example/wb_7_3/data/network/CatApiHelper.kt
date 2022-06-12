package com.example.wb_7_3.data.network

import android.util.Log
import com.example.wb_7_3.data.model.CatFavoriteAnswer
import com.example.wb_7_3.data.model.CatFavoriteModelData
import com.example.wb_7_3.data.model.CatFavoriteRequest
import com.example.wb_7_3.data.model.CatModelData
import com.example.wb_7_3.data.network.Environment.API_KEY
import com.example.wb_7_3.data.network.Environment.API_KEY_LABEL
import com.example.wb_7_3.data.network.Environment.BASE_API
import com.example.wb_7_3.data.network.Environment.GET_RANDOM_CAT_QUERY
import com.example.wb_7_3.data.network.Environment.FAVOURITE_CAT_QUERY
import com.example.wb_7_3.data.network.Environment.SUB_ID
import com.example.wb_7_3.data.toCatModelDomain
import com.example.wb_7_3.data.toDomain
import com.example.wb_7_3.domain.model.CatModelDomain
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class CatApiHelper @Inject constructor(private val client: HttpClient){

    suspend fun getCat(): CatModelDomain{
        val cat: List<CatModelData> = client.get(BASE_API + GET_RANDOM_CAT_QUERY){
            header(API_KEY_LABEL, API_KEY)

        }

        return cat.first().toDomain()
    }

    suspend fun postCat(cat: CatModelDomain): Boolean{

        Log.e("ID", cat.id)
        val answer: CatFavoriteAnswer = client.post(BASE_API + FAVOURITE_CAT_QUERY){
            header(API_KEY_LABEL, API_KEY)

            contentType(ContentType.Application.Json)
            body = CatFavoriteRequest(cat.id, SUB_ID)
        }

        return answer.message == "SUCCESS"
    }

    suspend fun getFavoriteCats(): List<CatFavoriteModelData>{
        val cats: List<CatFavoriteModelData> = client.get(BASE_API + FAVOURITE_CAT_QUERY){
            header(API_KEY_LABEL, API_KEY)

            url{
                parameters.append("sub_id", SUB_ID)
            }

        }

        return cats
    }

}