package com.carlospolo.melichallenge.presentation.ui.productdetail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.carlospolo.melichallenge.R
import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.presentation.ui.components.AppTopBar
import com.carlospolo.melichallenge.presentation.ui.components.FullScreenError
import com.carlospolo.melichallenge.presentation.ui.components.FullScreenLoading
import com.carlospolo.melichallenge.ui.theme.MercadoLibreBlue
import com.carlospolo.melichallenge.ui.theme.MercadoLibreGreen
import com.carlospolo.melichallenge.ui.theme.MercadoLibreYellow
import com.carlospolo.melichallenge.utils.MeliResult
import com.carlospolo.melichallenge.utils.conditionToSpanish

/**
 * A composable function that displays the product detail screen.
 *
 * @param viewModel The [ProductDetailViewModel] that provides the UI state.
 * @param productId The ID of the product to fetch details for.
 * @param onBack A callback triggered when the user navigates back.
 */
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    productId: String,
    onBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current

    when (state) {
        is MeliResult.Error -> {
            FullScreenError(
                onRetry = {
                    viewModel.getProductDetail(productId)
                },
                onBack = onBack
            )
        }
        MeliResult.Loading -> {
            FullScreenLoading()
        }
        is MeliResult.Success -> {
            val product = (state as MeliResult.Success).data
            ProductDetail(
                product = product,
                isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE,
                onBack = onBack
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getProductDetail(productId)
    }
}

/**
 * A composable function that displays the product details.
 *
 * @param product The [ProductDetailModel] containing product information.
 * @param isLandscape A boolean to determine if the screen is in landscape orientation.
 * @param onBack A callback triggered when the user navigates back.
 */
@Composable
private fun ProductDetail(
    product: ProductDetailModel,
    isLandscape: Boolean,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MercadoLibreYellow)
    ) {
        // Top bar
        AppTopBar(onBack = onBack)

        Box(modifier = Modifier.fillMaxSize()) {
            if (isLandscape) {
                LandscapeProductDetail(product = product)
            } else {
                PortraitProductDetail(product = product)
            }
        }
    }
}

@Composable
private fun PortraitProductDetail(
    product: ProductDetailModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Main image
            if (product.imageUrls.isNotEmpty()) {
                AsyncImage(
                    model = product.imageUrls.first(),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.White),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Product details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.product_detail_screen_lbl_condition, product.condition.conditionToSpanish()),
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.product_detail_screen_lbl_price, product.price),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MercadoLibreGreen
                )

                product.originalPrice?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            text = stringResource(id = R.string.product_detail_screen_lbl_original_price, it),
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Light
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                product.warranty?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            text = stringResource(id = R.string.product_detail_screen_lbl_warranty, it),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* TODO: Abrir enlace del producto */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MercadoLibreBlue)
                ) {
                    Text(
                        text = stringResource(id = R.string.product_detail_screen_btn_show_on_meli),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun LandscapeProductDetail(
    product: ProductDetailModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Column for image
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                if (product.imageUrls.isNotEmpty()) {
                    AsyncImage(
                        model = product.imageUrls.first(),
                        contentDescription = product.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(Color.White),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Column for product details
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = product.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.product_detail_screen_lbl_condition, product.condition.conditionToSpanish()),
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.product_detail_screen_lbl_price, product.price),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MercadoLibreGreen
                )

                product.originalPrice?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            text = stringResource(id = R.string.product_detail_screen_lbl_original_price, it),
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Light
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                product.warranty?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            text = stringResource(id = R.string.product_detail_screen_lbl_warranty, it),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* TODO: Abrir enlace del producto */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MercadoLibreBlue)
                ) {
                    Text(
                        text = stringResource(id = R.string.product_detail_screen_btn_show_on_meli),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProductDetailPortraitPreview() {
    ProductDetail(
        product = ProductDetailModel(
            title = "Smartphone Android 128GB - Azul",
            price = "1.299.900",
            originalPrice = "1.499.900",
            warranty = "12 meses de garantía oficial",
            condition = "nuevo",
            imageUrls = listOf(
                "https://http2.mlstatic.com/D_NQ_NP_2X_927954-MCO53945936678_022023-F.webp"
            ),
            permalink = "https://www.mercadolibre.com.co/item/ML123456"
        ),
        isLandscape = false,
        onBack = {}
    )
}
