package com.carlospolo.melichallenge.data.repository.strategy

import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.utils.MeliResult
import javax.inject.Inject

/**
 * A strategy for fetching product data from a local cache.
 * This class will provide cached product data once the caching mechanism is implemented.
 *
 * @constructor Creates an instance of [CacheFetchStrategy].
 * Currently, this strategy is not implemented and will return an error when used.
 */
class CacheFetchStrategy @Inject constructor(
    // TODO: Incluir dependencia de caché cuando se implemente
) : ProductFetchStrategy {

    /**
     * Retrieves a list of products from the cache based on a search query.
     * Currently, this method is not implemented and returns an error.
     *
     * @param search The search keyword used to find products.
     * @return A [MeliResult] containing an error indicating that caching is not implemented yet.
     */
    override suspend fun getItemsBySearch(search: String): MeliResult<List<ProductItemModel>> {
        // TODO: Implementar obtención desde caché
        return MeliResult.Error(UnsupportedOperationException("Cache not implemented yet"))
    }

    /**
     * Retrieves the details of a specific product from the cache.
     * Currently, this method is not implemented and returns an error.
     *
     * @param productId The unique identifier of the product.
     * @return A [MeliResult] containing an error indicating that caching is not implemented yet.
     */
    override suspend fun getDetailItem(productId: String): MeliResult<ProductDetailModel> {
        // TODO: Implementar obtención desde caché
        return MeliResult.Error(UnsupportedOperationException("Cache not implemented yet"))
    }
}