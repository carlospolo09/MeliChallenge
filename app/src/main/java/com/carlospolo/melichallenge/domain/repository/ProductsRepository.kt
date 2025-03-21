package com.carlospolo.melichallenge.domain.repository

import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.utils.MeliResult

/**
 * Repository interface for managing product-related data operations.
 * Provides an abstraction over data sources such as network or database.
 */
interface ProductsRepository {

    /**
     * Retrieves a list of products based on a search query.
     *
     * @param search The search keyword used to find products.
     * @return A [MeliResult] containing a list of [ProductItemModel] if successful,
     * or an error result if the request fails.
     */
    suspend fun getItemsBySearch(search: String): MeliResult<List<ProductItemModel>>

    /**
     * Retrieves the details of a specific product.
     *
     * @param productId The unique identifier of the product.
     * @return A [MeliResult] containing a [ProductDetailModel] if successful,
     * or an error result if the request fails.
     */
    suspend fun getDetailItem(productId: String): MeliResult<ProductDetailModel>
}