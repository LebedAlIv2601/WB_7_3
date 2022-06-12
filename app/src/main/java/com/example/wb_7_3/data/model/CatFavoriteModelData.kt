package com.example.wb_7_3.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatFavoriteModelData(
    @SerialName("id")
    val id: Int,

    @SerialName("image")
    val image: CatModelData,

    @SerialName("created_at")
    val createdAt: String
)
