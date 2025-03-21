package com.carlospolo.melichallenge.data.repository

import com.carlospolo.melichallenge.data.repository.strategy.ProductRepositoryStrategy
import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.domain.repository.ProductsRepository
import com.carlospolo.melichallenge.utils.MeliResult
import javax.inject.Inject

/**
 * Implementation of [ProductsRepository] that delegates data fetching operations
 * to a strategy-based repository approach. This allows flexibility in switching
 * between different data sources dynamically.
 *
 * @property strategyContext The strategy instance responsible for fetching
 * product data from different sources.
 */
class ProductsRepositoryImpl @Inject constructor(
    private val strategyContext: ProductRepositoryStrategy
) : ProductsRepository {

    /**
     * Retrieves a list of products based on a search query using the configured strategy.
     *
     * @param search The search keyword used to find products.
     * @return A [MeliResult] containing a list of [ProductItemModel] if successful,
     * or an error result if the request fails.
     */
    override suspend fun getItemsBySearch(search: String): MeliResult<List<ProductItemModel>> {
        return strategyContext.getItemsBySearch(search)
    }

    /**
     * Retrieves the details of a specific product using the configured strategy.
     *
     * @param productId The unique identifier of the product.
     * @return A [MeliResult] containing a [ProductDetailModel] if successful,
     * or an error result if the request fails.
     */
    override suspend fun getDetailItem(productId: String): MeliResult<ProductDetailModel> {
        return strategyContext.getDetailItem(productId)
    }
}