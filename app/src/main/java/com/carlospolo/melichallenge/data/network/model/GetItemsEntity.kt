package com.carlospolo.melichallenge.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetItemsEntity(
    @SerialName("site_id") val siteId: String,
    val query: String,
    val paging: Paging,
    val results: List<Product>
)

@Serializable
data class Paging(
    val total: Int,
    val offset: Int,
    val limit: Int,
    @SerialName("primary_results") val primaryResults: Int
)

@Serializable
data class Product(
    val id: String,
    @SerialName("site_id") val siteId: String,
    val title: String,
    val price: Double,
    @SerialName("currency_id") val currencyId: String,
    @SerialName("available_quantity") val availableQuantity: Int,
    @SerialName("buying_mode") val buyingMode: String,
    @SerialName("listing_type_id") val listingTypeId: String,
    @SerialName("stop_time") val stopTime: String,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    @SerialName("accepts_mercadopago") val acceptsMercadoPago: Boolean,
    val installments: Installments?,
    val shipping: Shipping,
    @SerialName("original_price") val originalPrice: Double?,
    @SerialName("category_id") val categoryId: String,
    @SerialName("official_store_id") val officialStoreId: Int?,
    @SerialName("catalog_product_id") val catalogProductId: String,
    @SerialName("catalog_listing") val catalogListing: Boolean,
    val seller: Seller? = null,
    val state: Location? = null,
    val city: Location? = null
)

@Serializable
data class Installments(
    val quantity: Int,
    val amount: Double,
    val rate: Double,
    @SerialName("currency_id") val currencyId: String
)

@Serializable
data class Shipping(
    @SerialName("free_shipping") val freeShipping: Boolean,
    val mode: String,
    val tags: List<String>,
    @SerialName("logistic_type") val logisticType: String,
    @SerialName("store_pick_up") val storePickUp: Boolean
)

@Serializable
data class Seller(
    val id: Long,
    @SerialName("power_seller_status") val powerSellerStatus: String?,
    @SerialName("car_dealer") val carDealer: Boolean,
    @SerialName("real_estate_agency") val realEstateAgency: Boolean,
    val tags: List<String>
)

@Serializable
data class Location(
    val id: String?,
    val name: String
)
