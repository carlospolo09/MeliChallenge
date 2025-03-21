package com.carlospolo.melichallenge.data.network.mapper

import com.carlospolo.melichallenge.data.network.model.ItemDetailEntity
import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.utils.toArgentinePesos

/**
 * Maps an [ItemDetailEntity] from the API layer to a [ProductDetailModel] in the domain layer.
 *
 * @return A [ProductDetailModel] containing the mapped product details.
 */
fun ItemDetailEntity.toDomain(): ProductDetailModel {
    return ProductDetailModel(
        title = title,
        price = price.toArgentinePesos(),
        originalPrice = originalPrice?.toArgentinePesos(),
        warranty = warranty,
        condition = condition,
        imageUrls = pictures.map { it.secureUrl },
        permalink = permalink
    )
}