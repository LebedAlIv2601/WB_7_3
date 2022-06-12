package com.example.wb_7_3.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatFavoriteRequest (
    @SerialName("image_id")
    val imageId: String,
    @SerialName("sub_id")
    val subId: String
    )