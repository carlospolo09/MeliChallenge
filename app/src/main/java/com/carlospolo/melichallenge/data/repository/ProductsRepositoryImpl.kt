package com.carlospolo.melichallenge.data.repository

import com.carlospolo.melichallenge.BuildConfig
import com.carlospolo.melichallenge.data.network.mapper.toDomain
import com.carlospolo.melichallenge.data.network.remote.ProductsApiService
import com.carlospolo.melichallenge.data.network.utils.Constants.ERROR_LOG_DETAIL_ITEM
import com.carlospolo.melichallenge.data.network.utils.Constants.ERROR_LOG_LIST_ITEM
import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.domain.repository.ProductsRepository
import com.carlospolo.melichallenge.utils.MeliResult
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of [ProductsRepository] that interacts with [ProductsApiService]
 * to fetch product-related data from the API.
 *
 * @param productsApiService The API service used to retrieve product information.
 */
class ProductsRepositoryImpl @Inject constructor(
    private val productsApiService: ProductsApiService
) : ProductsRepository {

    /**
     * Fetches a list of products based on a search query.
     *
     * @param search The search keyword used to retrieve products.
     * @return A [MeliResult] containing a list of [ProductItemModel] if successful,
     * or an error result if the request fails.
     */
    override suspend fun getItemsBySearch(search: String): MeliResult<List<ProductItemModel>> {
        return try {
            val responseBody = productsApiService.getItemsBySearch(BuildConfig.SITE_ID, search)
            MeliResult.Success(responseBody.results.map { it.toDomain() } )
        } catch (e: Throwable) {
            Timber.e(e, ERROR_LOG_LIST_ITEM, search)
            MeliResult.Error(e)
        }
    }

    /**
     * Fetches the details of a specific product.
     *
     * @param productId The unique identifier of the product.
     * @return A [MeliResult] containing a [ProductDetailModel] if successful,
     * or an error result if the request fails.
     */
    override suspend fun getDetailItem(productId: String): MeliResult<ProductDetailModel> {
        return try {
            val responseBody = productsApiService.getDetailItem(productId)
            MeliResult.Success(responseBody.toDomain())
        } catch (e: Throwable) {
            Timber.e(e, ERROR_LOG_DETAIL_ITEM, productId)
            MeliResult.Error(e)
        }
    }
}