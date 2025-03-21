package com.carlospolo.melichallenge.domain.model

data class ProductDetailModel(
    val title: String,
    val price: String,
    val originalPrice: String?,
    val warranty: String?,
    val condition: String,
    val imageUrls: List<String>,
    val permalink: String
)
