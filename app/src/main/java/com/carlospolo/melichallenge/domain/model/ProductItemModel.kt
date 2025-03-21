package com.carlospolo.melichallenge.domain.model

data class ProductItemModel(
    val id: String,
    val title: String,
    val originalPrice: String?,
    val price: String,
    val thumbnail: String,
)
