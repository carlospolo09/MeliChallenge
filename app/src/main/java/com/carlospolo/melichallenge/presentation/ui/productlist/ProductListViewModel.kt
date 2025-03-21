package com.carlospolo.melichallenge.presentation.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.domain.usecase.GetProductsUseCase
import com.carlospolo.melichallenge.utils.MeliResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for handling the product list screen's UI state.
 *
 * @param getProductsUseCase The use case to fetch the list of products.
 */
@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    // StateFlow that holds the UI state of the product list screen
    private val _uiState = MutableStateFlow<MeliResult<List<ProductItemModel>>>(MeliResult.Loading)
    val uiState: StateFlow<MeliResult<List<ProductItemModel>>> = _uiState

    /**
     * Fetches a list of products based on the given [search] query.
     * The result is collected from the use case and emitted to the UI state.
     *
     * @param search The search query used to retrieve products.
     */
    fun getProductList(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase(search)
                .collect { result -> _uiState.value = result }
        }
    }
}