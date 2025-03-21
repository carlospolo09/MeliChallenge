package com.carlospolo.melichallenge.data.repository.strategy

import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.utils.MeliResult
import javax.inject.Inject

/**
 * Strategy context class for selecting the appropriate data fetching strategy.
 * This class allows switching between different implementations of [ProductFetchStrategy],
 * enabling flexibility in data retrieval (e.g., remote or cached sources).
 *
 * @property cacheStrategy The caching strategy, which is currently a placeholder for future implementation.
 * @constructor Initializes the strategy with a default remote strategy.
 */
class ProductRepositoryStrategy @Inject constructor(
    remoteStrategy: RemoteFetchStrategy,
    private val cacheStrategy: CacheFetchStrategy
) {
    private var currentStrategy: ProductFetchStrategy = remoteStrategy

    /**
     * Updates the current data fetching strategy.
     *
     * @param strategy The new [ProductFetchStrategy] to be used for retrieving data.
     */
    fun setStrategy(strategy: ProductFetchStrategy) {
        currentStrategy = strategy
    }

    /**
     * Retrieves a list of products using the selected strategy.
     *
     * @param search The search keyword used to find products.
     * @return A [MeliResult] containing a list of [ProductItemModel] if successful,
     * or an error result if the request fails.
     */
    suspend fun getItemsBySearch(search: String): MeliResult<List<ProductItemModel>> {
        return currentStrategy.getItemsBySearch(search)
    }

    /**
     * Retrieves the details of a specific product using the selected strategy.
     *
     * @param productId The unique identifier of the product.
     * @return A [MeliResult] containing a [ProductDetailModel] if successful,
     * or an error result if the request fails.
     */
    suspend fun getDetailItem(productId: String): MeliResult<ProductDetailModel> {
        return currentStrategy.getDetailItem(productId)
    }
}