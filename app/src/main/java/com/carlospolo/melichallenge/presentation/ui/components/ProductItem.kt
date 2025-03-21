package com.carlospolo.melichallenge.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.carlospolo.melichallenge.R
import com.carlospolo.melichallenge.domain.model.ProductItemModel
import com.carlospolo.melichallenge.ui.theme.MercadoLibreGreen

/**
 * A composable function that displays a product item.
 *
 * @param modifier The [Modifier] to apply to the container.
 * @param productItemModel The product data model containing title, price, and image.
 * @param onClick A callback triggered when the item is clicked, passing the product ID.
 */
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    productItemModel: ProductItemModel,
    onClick: (productId: String) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick.invoke(productItemModel.id) }
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = productItemModel.thumbnail,
                contentDescription = productItemModel.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = R.drawable.product_placeholder),
                error = painterResource(id = R.drawable.product_placeholder),
            )

            Spacer(modifier = Modifier.width(12.dp))

            ProductData(productItemModel)
        }
    }
}

/**
 * A composable function that displays product information including title and price.
 *
 * @param productItemModel The product data model containing title, price, and original price.
 */
@Composable
private fun ProductData(productItemModel: ProductItemModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = productItemModel.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        productItemModel.originalPrice?.let { originalPrice ->
            Text(
                text = originalPrice,
                fontSize = 14.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
        }

        Text(
            text = productItemModel.price,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MercadoLibreGreen
        )
    }
}

@Preview
@Composable
private fun ProductItemPreview() {
    ProductItem(
        productItemModel = ProductItemModel(
            id = "id",
            title = "Título del producto",
            originalPrice = "$100.000,00",
            price = "$80.000,00",
            thumbnail = "thumbnail",
        ),
        onClick = {}
    )
}

@Preview
@Composable
private fun ProductItemOriginalPriceNullPreview() {
    ProductItem(
        productItemModel = ProductItemModel(
            id = "id",
            title = "Título del producto",
            originalPrice = null,
            price = "$80.000,00",
            thumbnail = "thumbnail",
        ),
        onClick = {}
    )
}