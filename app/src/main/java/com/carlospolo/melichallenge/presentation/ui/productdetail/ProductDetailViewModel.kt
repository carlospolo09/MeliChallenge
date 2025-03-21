package com.carlospolo.melichallenge.presentation.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.usecase.GetProductDetailUseCase
import com.carlospolo.melichallenge.utils.MeliResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for handling the product detail screen's UI state.
 *
 * @param getProductDetailUseCase The use case to fetch product details.
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    // StateFlow that holds the UI state of the product detail screen
    private val _uiState = MutableStateFlow<MeliResult<ProductDetailModel>>(MeliResult.Loading)
    val uiState: StateFlow<MeliResult<ProductDetailModel>> = _uiState

    /**
     * Fetches the product details based on the given [productId].
     *
     * @param productId The ID of the product to retrieve details for.
     */
    fun getProductDetail(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductDetailUseCase(productId)
                .collect { result -> _uiState.value = result }
        }
    }
}