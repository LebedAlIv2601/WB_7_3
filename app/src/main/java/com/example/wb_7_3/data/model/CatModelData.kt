package com.example.wb_7_3.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CatModelData(
    @SerialName("id")
    val id: String,

    @SerialName("url")
    val url: String
)
