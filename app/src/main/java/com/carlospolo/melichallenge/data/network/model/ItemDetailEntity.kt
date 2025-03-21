package com.carlospolo.melichallenge.data.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemDetailEntity(
    val title: String,
    @SerialName("seller_id") val sellerId: Long,
    @SerialName("category_id") val categoryId: String,
    @SerialName("official_store_id") val officialStoreId: Int?,
    val price: Double,
    @SerialName("base_price") val basePrice: Double,
    @SerialName("original_price") val originalPrice: Double?,
    @SerialName("currency_id") val currencyId: String,
    @SerialName("initial_quantity") val initialQuantity: Int,
    @SerialName("buying_mode") val buyingMode: String,
    @SerialName("listing_type_id") val listingTypeId: String,
    val condition: String,
    val permalink: String,
    @SerialName("thumbnail_id") val thumbnailId: String,
    val thumbnail: String,
    val acceptsMercadoPago: Boolean,
    val status: String,
    @SerializedName("pictures") val pictures: List<PictureEntity>,
    @SerialName("warranty") val warranty: String?,
    @SerialName("date_created") val dateCreated: String,
    @SerialName("last_updated") val lastUpdated: String
)

@Serializable
data class PictureEntity(
    @SerializedName("secure_url") val secureUrl: String
)

