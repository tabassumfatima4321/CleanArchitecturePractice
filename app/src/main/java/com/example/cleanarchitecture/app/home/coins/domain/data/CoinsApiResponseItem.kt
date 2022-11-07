package com.example.cleanarchitecture.app.home.coins.domain.data

data class CoinsApiResponseItem(
    val id: String,
    val img: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)