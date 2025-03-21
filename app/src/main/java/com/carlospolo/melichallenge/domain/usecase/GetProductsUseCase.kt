package com.carlospolo.melichallenge.domain.usecase

import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.domain.repository.ProductsRepository
import com.carlospolo.melichallenge.utils.MeliResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Use case for retrieving a list of products based on a search query.
 *
 * @param repository The [ProductsRepository] used to fetch product data.
 */
class GetProductsUseCase(private val repository: ProductsRepository) {

    /**
     * Invokes the use case to fetch products that match the given search query.
     *
     * @param search The search keyword used to filter products.
     * @return A [Flow] emitting a [MeliResult] containing the list of matching products or an error state.
     */
    suspend operator fun invoke(search: String): Flow<MeliResult<List<ProductItemModel>>> = flow {
        emit(MeliResult.Loading)
        emit(repository.getItemsBySearch(search))
    }
}