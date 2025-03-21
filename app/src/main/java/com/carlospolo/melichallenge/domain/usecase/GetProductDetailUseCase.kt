package com.carlospolo.melichallenge.domain.usecase

import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.repository.ProductsRepository
import com.carlospolo.melichallenge.utils.MeliResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Use case for retrieving the details of a specific product.
 *
 * @param repository The [ProductsRepository] used to fetch product details.
 */
class GetProductDetailUseCase(private val repository: ProductsRepository) {

    /**
     * Invokes the use case to fetch product details.
     *
     * @param productId The ID of the product to retrieve details for.
     * @return A [Flow] emitting a [MeliResult] with the product details or an error state.
     */
    suspend operator fun invoke(productId: String): Flow<MeliResult<ProductDetailModel>> = flow {
        emit(MeliResult.Loading)
        emit(repository.getDetailItem(productId))
    }
}