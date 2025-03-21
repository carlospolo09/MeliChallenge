package com.carlospolo.melichallenge.presentation.ui.productlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.presentation.ui.components.AppTopBar
import com.carlospolo.melichallenge.presentation.ui.components.FullScreenError
import com.carlospolo.melichallenge.presentation.ui.components.FullScreenLoading
import com.carlospolo.melichallenge.presentation.ui.components.ProductItem
import com.carlospolo.melichallenge.ui.theme.MercadoLibreYellow
import com.carlospolo.melichallenge.utils.MeliResult

/**
 * Composable function that displays the product list screen.
 *
 * @param viewModel The ViewModel responsible for managing the product list state.
 * @param search The search query used to fetch the product list.
 * @param onProductClick Callback triggered when a product is clicked.
 * @param onBack Callback triggered when the back button is pressed.
 */
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    search: String,
    onProductClick: (productId: String) -> Unit,
    onBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is MeliResult.Error -> {
            FullScreenError(
                onRetry = {
                    viewModel.getProductList(search)
                },
                onBack = onBack
            )
        }
        MeliResult.Loading -> {
            FullScreenLoading()
        }
        is MeliResult.Success -> {
            val products = (state as MeliResult.Success).data
            ProductList(
                products = products,
                onProductClick = onProductClick,
                onBack = onBack,
            )
        }
    }

    // Fetch the product list when the screen is first composed
    LaunchedEffect(Unit) {
        if (viewModel.uiState.value !is MeliResult.Success) {
            viewModel.getProductList(search)
        }
    }
}

/**
 * Composable function that displays a list of products.
 *
 * @param products The list of products to display.
 * @param onProductClick Callback triggered when a product is clicked.
 * @param onBack Callback triggered when the back button is pressed.
 */
@Composable
private fun ProductList(
    products: List<ProductItemModel>,
    onProductClick: (productId: String) -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MercadoLibreYellow)
    ) {
        // Top bar with back navigation
        AppTopBar(onBack = onBack)

        // Display the list of products
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    productItemModel = product,
                    onClick = onProductClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductListPreview() {
    ProductList(
        products = arrayListOf(
            ProductItemModel(
                id = "id",
                title = "Título del producto",
                originalPrice = "$100.000,00",
                price = "$80.000,00",
                thumbnail = "thumbnail",
            ),
            ProductItemModel(
                id = "id",
                title = "Título del producto",
                originalPrice = null,
                price = "$80.000,00",
                thumbnail = "thumbnail",
            ),
            ProductItemModel(
                id = "id",
                title = "Título del producto",
                originalPrice = "$100.000,00",
                price = "$80.000,00",
                thumbnail = "thumbnail",
            ),
            ProductItemModel(
                id = "id",
                title = "Título del producto",
                originalPrice = null,
                price = "$80.000,00",
                thumbnail = "thumbnail",
            )
        ),
        onProductClick = {},
        onBack = {},
    )
}