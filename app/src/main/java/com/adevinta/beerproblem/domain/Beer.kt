package com.adevinta.beerproblem.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable data class Beer(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("tagline") val tagline: String,
    @SerialName("image_url") val imageUrl: String
)
