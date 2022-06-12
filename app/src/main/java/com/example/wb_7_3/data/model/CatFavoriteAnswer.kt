package com.example.wb_7_3.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatFavoriteAnswer (
    @SerialName("message")
    val message: String,

    @SerialName("id")
    val id: Int

)