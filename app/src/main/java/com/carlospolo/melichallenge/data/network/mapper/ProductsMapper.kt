package com.carlospolo.melichallenge.data.network.mapper

import com.carlospolo.melichallenge.data.network.model.Product
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.utils.toArgentinePesos

/**
 * Maps a [Product] from the API layer to a [ProductItemModel] in the domain layer.
 *
 * @return A [ProductItemModel] containing the mapped product information.
 */
fun Product.toDomain(): ProductItemModel {
    return ProductItemModel(
        id = id,
        title = title,
        originalPrice = originalPrice?.toArgentinePesos(),
        price = price.toArgentinePesos(),
        thumbnail = thumbnail,
    )
}