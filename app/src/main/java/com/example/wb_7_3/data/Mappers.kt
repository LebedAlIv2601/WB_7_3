package com.example.wb_7_3.data

import com.example.wb_7_3.data.local.model.FavoriteCatModelLocal
import com.example.wb_7_3.data.model.CatFavoriteModelData
import com.example.wb_7_3.data.model.CatModelData
import com.example.wb_7_3.domain.model.CatModelDomain

fun CatModelData.toDomain(): CatModelDomain{
    return CatModelDomain(
        id = id,
        url = url
    )
}

fun CatFavoriteModelData.toCatModelDomain(): CatModelDomain{
    return CatModelDomain(
        id = image.id,
        url = image.url
    )
}


fun CatFavoriteModelData.toLocal(): FavoriteCatModelLocal{
    return FavoriteCatModelLocal(
        image.id, image.url, createdAt
    )
}

fun FavoriteCatModelLocal.toDomain(): CatModelDomain{
    return CatModelDomain(
        id, url
    )
}