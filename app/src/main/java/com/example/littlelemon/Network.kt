package com.example.littlelemon

import kotlinx.serialization.SerialInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MenuItemNetwork(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("price") val price: Int,
    @SerialName("image") val image: String,
    @SerialName("category") val category: String
    )

@Serializable
class MenuNetworkData(
    @SerialName("menu") val menu: List<MenuItemNetwork>
)